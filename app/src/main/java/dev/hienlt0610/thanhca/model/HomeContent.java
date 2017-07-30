package dev.hienlt0610.thanhca.model;

/**
 * Created by hienl on 7/30/2017.
 */

public class HomeContent extends BaseModel {
    private BaseListModel<Category> mCategories;
    private BaseListModel<Album> mNewAlbums;
    private BaseListModel<Artist> mArtists;
    private BaseListModel<Singer> mSingers;
    private Statistic mStatistic;

    public Statistic getStatistic() {
        return mStatistic;
    }

    public void setStatistic(Statistic statistic) {
        mStatistic = statistic;
    }

    public BaseListModel<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(BaseListModel<Category> categories) {
        mCategories = categories;
    }

    public BaseListModel<Album> getNewAlbums() {
        return mNewAlbums;
    }

    public void setNewAlbums(BaseListModel<Album> newAlbums) {
        mNewAlbums = newAlbums;
    }

    public BaseListModel<Artist> getArtists() {
        return mArtists;
    }

    public void setArtists(BaseListModel<Artist> artists) {
        mArtists = artists;
    }

    public BaseListModel<Singer> getSingers() {
        return mSingers;
    }

    public void setSingers(BaseListModel<Singer> singers) {
        mSingers = singers;
    }
}
