package dev.hienlt0610.thanhca.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hienl on 7/30/2017.
 */

public class SongList extends BaseModel {
    private List<Media> mMedias;
    private int totalSong;
    private int totalPage;

    public List<Media> getMedias() {
        if (mMedias == null) {
            mMedias = new ArrayList<>();
        }
        return mMedias;
    }

    public void setMedias(List<Media> medias) {
        mMedias = medias;
    }

    public int getTotalSong() {
        return totalSong;
    }

    public void setTotalSong(int totalSong) {
        this.totalSong = totalSong;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
