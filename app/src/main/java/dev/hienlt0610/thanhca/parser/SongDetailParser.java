package dev.hienlt0610.thanhca.parser;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import dev.hienlt0610.thanhca.model.Album;
import dev.hienlt0610.thanhca.model.Artist;
import dev.hienlt0610.thanhca.model.BaseModel;
import dev.hienlt0610.thanhca.model.Category;
import dev.hienlt0610.thanhca.model.Media;
import dev.hienlt0610.thanhca.model.Singer;
import dev.hienlt0610.thanhca.utils.ParserUtils;

/**
 * Created by hienl on 7/30/2017.
 */

public class SongDetailParser implements Parser {
    @Override
    public BaseModel parse(String html) {
        Document document = Jsoup.parse(html);
        if (document.getElementsByTag("table").size() == 0) {
            return null;
        }
        Element eInfo = document.select("#playing > tr:nth-child(1) > td > table.border > tbody > tr > td > table").first();
        if (eInfo == null) {
            return null;
        }
        Media media = new Media();
        media.setSinger(new Singer());
        //get singer picture url
        Element eImage = eInfo.select("tbody > tr:nth-child(1) > td > img").first();
        if (eImage != null) {
            media.getSinger().setSingerImage(eImage.attr("src"));
        }
        //Get title
        Element eTitle = eInfo.select("tbody > tr:nth-child(2) > td.fr_2").first();
        if (eTitle != null) {
            media.setTitle(eTitle.text());
        }
        //Get singer
        Element eSinger = eInfo.select("tbody > tr:nth-child(3) > td.fr_2 > a").first();
        if (eSinger != null) {
            int singerId = ParserUtils.parseValue(eSinger.attr("href"));
            media.getSinger().setId(singerId);
            media.getSinger().setName(eSinger.text());
        }
        //Get artist
        Element eArtist = eInfo.select("tbody > tr:nth-child(3) > td.fr_2 > a").first();
        if (eArtist != null) {
            Artist artist = new Artist();
            int artistId = ParserUtils.parseValue(eArtist.attr("href"));
            artist.setId(artistId);
            artist.setName(eSinger.text());
            media.setArtist(artist);
        }
        //Get album
        Element eAlbum = eInfo.select("tbody > tr:nth-child(5) > td.fr_2 > a").first();
        if (eAlbum != null) {
            Album album = new Album();
            int albumId = ParserUtils.parseValue(eAlbum.attr("href"));
            album.setId(albumId);
            album.setName(eAlbum.text());
            media.setAlbum(album);
        }
        //Get category
        Element eCategory = eInfo.select("tbody > tr:nth-child(6) > td.fr_2 > a").first();
        if (eCategory != null) {
            Category category = new Category();
            int cateId = ParserUtils.parseValue(eCategory.attr("href"));
            category.setId(cateId);
            category.setTitle(eCategory.text());
            media.setCategory(category);
        }
        //Get poster
        Element ePoster = eInfo.select("tbody > tr:nth-child(7) > td.fr_2").first();
        if (ePoster != null) {
            media.setPoster(ePoster.text());
        }
        Element ePostDate = eInfo.select("tbody > tr:nth-child(8) > td.fr_2").first();
        if (ePostDate != null) {
            media.setPoster(ePostDate.text());
        }
        Element eDownNum = eInfo.select("tbody > tr:nth-child(9) > td.fr_2").first();
        if (eDownNum != null) {
            media.setDownloadNum(NumberUtils.toInt(eDownNum.text()));
        }
        Element eListenNum = eInfo.select("tbody > tr:nth-child(10) > td.fr_2").first();
        if (eListenNum != null) {
            media.setListenNum(NumberUtils.toInt(eListenNum.text()));
        }
        Element ePdfUrl = eInfo.select("tbody > tr:nth-child(12) > td > a:nth-child(5)").first();
        if (ePdfUrl != null) {
            media.setPdfUrl(ePdfUrl.attr("href"));
        }
        Element eSongUrl = document.getElementById("song_url");
        if (eSongUrl != null) {
            media.setDownloadUrl(eSongUrl.val());
        }
        return media;
    }
}
