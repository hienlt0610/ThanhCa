package dev.hienlt0610.thanhca.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mikepenz.materialdrawer.DrawerBuilder;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import dev.hienlt0610.thanhca.R;
import dev.hienlt0610.thanhca.adapter.ListSongAdapter;
import dev.hienlt0610.thanhca.common.BaseActivity;
import dev.hienlt0610.thanhca.common.BaseListener;
import dev.hienlt0610.thanhca.common.Constant;
import dev.hienlt0610.thanhca.model.SongList;
import dev.hienlt0610.thanhca.repository.SongRepository;

public class MainActivity extends BaseActivity implements RecyclerArrayAdapter.OnMoreListener,
        SwipeRefreshLayout.OnRefreshListener, FloatingSearchView.OnMenuItemClickListener, FloatingSearchView.OnSearchListener, FloatingSearchView.OnQueryChangeListener {

    @BindView(R.id.rv_list_song)
    EasyRecyclerView mRvListSong;
    @BindView(R.id.floating_search_view)
    FloatingSearchView mSearchView;
    @BindView(R.id.card_result)
    CardView mCardResult;
    @BindView(R.id.tv_search_result)
    TextView mTvSearchResult;

    private ListSongAdapter mListSongAdapter;
    private int mCurrentPage = 1;
    private int mSearchType = Constant.BY_SONG;
    private String mSearchKeyword;
    private SongRepository mSongRepository;

    @Override
    protected int getResLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRepository();
        initDrawer();
        initSearchView();
        initListView();
        getListSong();
    }

    private void initRepository() {
        mSongRepository = new SongRepository();
    }

    private void initSearchView() {
        setSearchHint(mSearchType);
        mSearchView.setOnMenuItemClickListener(this);
        mSearchView.setOnQueryChangeListener(this);
        mSearchView.setOnSearchListener(this);
    }

    private void initDrawer() {
        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getToolbar())
                .build();
    }

    private void initListView() {
        mRvListSong.setLayoutManager(new LinearLayoutManager(this));
        mListSongAdapter = new ListSongAdapter(this);
        mListSongAdapter.setMore(R.layout.item_loading, this);
        mRvListSong.setProgressView(R.layout.view_loading);
        mRvListSong.setEmptyView(R.layout.view_empty);
        mRvListSong.setRefreshListener(this);
        mRvListSong.setAdapter(mListSongAdapter);
    }

    private void getListSong() {
        if (mCurrentPage <= 1) {
            mListSongAdapter.clear();
            mRvListSong.showProgress();
        }
        mSongRepository.getList(mCurrentPage, new BaseListener<SongList>() {
            @Override
            public void onSuccess(SongList data) {
                mListSongAdapter.addAll(data.getMedias());
            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
        //Hide total result
        mCardResult.setVisibility(View.GONE);
    }

    private void searchSong() {
        if (mCurrentPage <= 1) {
            mRvListSong.showProgress();
        }
        mSongRepository.searchSong(mCurrentPage, mSearchKeyword, mSearchType, new BaseListener<SongList>() {
            @Override
            public void onSuccess(SongList data) {
                if (mCurrentPage <= 1) {
                    mListSongAdapter.clear();
                    mCardResult.setVisibility(View.VISIBLE);
                    String strTotal = String.valueOf(data.getTotalSong());
                    String searchResult = "Tìm thấy " + strTotal + " bài hát";
                    Spannable spannable = new SpannableString(searchResult);
                    StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
                    int index = searchResult.indexOf(strTotal);
                    spannable.setSpan(boldSpan, index, index + strTotal.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mTvSearchResult.setText(spannable);
                }
                mListSongAdapter.addAll(data.getMedias());
            }

            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMoreShow() {
        mCurrentPage++;
        if (StringUtils.isEmpty(mSearchKeyword)) {
            getListSong();
        } else {
            searchSong();
        }
    }

    @Override
    public void onMoreClick() {
    }

    @Override
    public void onRefresh() {
        mRvListSong.setRefreshing(false);
        mCurrentPage = 1;
        if (StringUtils.isEmpty(mSearchKeyword)) {
            getListSong();
        } else {
            searchSong();
        }
    }

    private void setSearchHint(int searchType) {
        String strSearchHintText = null;
        switch (mSearchType) {
            case Constant.BY_SONG:
                strSearchHintText = "Tìm theo tên bài hát";
                break;
            case Constant.BY_SINGER:
                strSearchHintText = "Tìm theo tên ca sỹ";
                break;
            case Constant.BY_ARTIST:
                strSearchHintText = "Tìm theo tên nhạc sỹ";
                break;
            case Constant.BY_ALBUM:
                strSearchHintText = "Tìm theo album";
                break;
        }
        mSearchView.setSearchHint(strSearchHintText);
    }

    /**
     * Call when menu item clicked
     *
     * @param item Menu item
     */
    @Override
    public void onActionMenuItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_search_song:
                mSearchType = Constant.BY_SONG;
                break;
            case R.id.menu_action_search_singer:
                mSearchType = Constant.BY_SINGER;
                break;
            case R.id.menu_action_search_artist:
                mSearchType = Constant.BY_ARTIST;
                break;
            case R.id.menu_action_search_album:
                mSearchType = Constant.BY_ALBUM;
                break;
        }
        setSearchHint(mSearchType);
    }

    @Override
    public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

    }

    @Override
    public void onSearchAction(String currentQuery) {
        mSearchKeyword = currentQuery;
        mCurrentPage = 1;
        if (StringUtils.isEmpty(mSearchKeyword)) {
            getListSong();
        } else {
            searchSong();
        }
    }

    @Override
    public void onSearchTextChanged(String oldQuery, String newQuery) {
        mSearchKeyword = newQuery;
    }
}
