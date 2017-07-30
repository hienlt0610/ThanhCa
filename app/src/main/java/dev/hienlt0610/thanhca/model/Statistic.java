package dev.hienlt0610.thanhca.model;

/**
 * Created by hienl on 7/30/2017.
 */

public class Statistic extends BaseModel {
    private int totalArtists;
    private int totalSingers;
    private int totalSongs;
    private int totalAlbums;
    private int totalListens;
    private int totalDownload;
    private int totalUser;
    private User newUser;

    public int getTotalArtists() {
        return totalArtists;
    }

    public void setTotalArtists(int totalArtists) {
        this.totalArtists = totalArtists;
    }

    public int getTotalSingers() {
        return totalSingers;
    }

    public void setTotalSingers(int totalSingers) {
        this.totalSingers = totalSingers;
    }

    public int getTotalSongs() {
        return totalSongs;
    }

    public void setTotalSongs(int totalSongs) {
        this.totalSongs = totalSongs;
    }

    public int getTotalAlbums() {
        return totalAlbums;
    }

    public void setTotalAlbums(int totalAlbums) {
        this.totalAlbums = totalAlbums;
    }

    public int getTotalListens() {
        return totalListens;
    }

    public void setTotalListens(int totalListens) {
        this.totalListens = totalListens;
    }

    public int getTotalDownload() {
        return totalDownload;
    }

    public void setTotalDownload(int totalDownload) {
        this.totalDownload = totalDownload;
    }

    public int getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(int totalUser) {
        this.totalUser = totalUser;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }
}
