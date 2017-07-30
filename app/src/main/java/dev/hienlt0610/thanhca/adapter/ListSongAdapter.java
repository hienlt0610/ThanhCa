package dev.hienlt0610.thanhca.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.hienlt0610.thanhca.R;
import dev.hienlt0610.thanhca.model.Media;

/**
 * Created by hienl on 7/30/2017.
 */

public class ListSongAdapter extends RecyclerArrayAdapter<Media> {
    public ListSongAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_song, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends BaseViewHolder<Media> {
        @BindView(R.id.tv_song_title)
        TextView mTvTitle;
        @BindView(R.id.tv_song_artist)
        TextView mTvArtist;
        @BindView(R.id.tv_song_category)
        TextView mTvCategory;
        @BindView(R.id.tv_song_singer)
        TextView mTvSinger;
        @BindView(R.id.imv_item_media_type)
        ImageView mImvType;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(Media data) {
            super.setData(data);
            mTvTitle.setText(data.getTitle());
            if (data.getArtist() != null) {
                mTvArtist.setText(data.getArtist().getName());
            }
            if (data.getCategory() != null) {
                mTvCategory.setText(data.getCategory().getTitle());
            }
            if (data.getSinger() != null) {
                mTvSinger.setText(data.getSinger().getName());
            }
            mImvType.setImageResource((data.isMusic()) ? R.drawable.ic_music : R.drawable.ic_video);
        }
    }
}
