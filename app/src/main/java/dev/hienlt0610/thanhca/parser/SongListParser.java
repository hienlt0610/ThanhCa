package dev.hienlt0610.thanhca.parser;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import dev.hienlt0610.thanhca.model.Artist;
import dev.hienlt0610.thanhca.model.BaseModel;
import dev.hienlt0610.thanhca.model.Category;
import dev.hienlt0610.thanhca.model.Media;
import dev.hienlt0610.thanhca.model.Singer;
import dev.hienlt0610.thanhca.model.SongList;
import dev.hienlt0610.thanhca.utils.ParserUtils;

/**
 * Created by hienl on 7/30/2017.
 */

public class SongListParser implements Parser {
    @Override
    public BaseModel parse(String html) {
        Document document = Jsoup.parse(html);
        SongList songList = new SongList();
        List<Media> medias = new ArrayList<>();
        songList.setMedias(medias);
        Elements rows = document.select("tr[class^=m_list]");
        if (rows.size() == 0) {
            return songList;
        }
        Element eTotalSong = rows.last().getElementsByTag("b").first();
        if (eTotalSong != null) {
            songList.setTotalSong(NumberUtils.toInt(eTotalSong.text()));
        }
        Element eTotalPage = document.select("a.pagelink").last();
        if (eTotalPage != null) {
            int totalPage = ParserUtils.parseValue(eTotalPage.attr("href"));
            songList.setTotalPage(totalPage);
        }
        for (int i = 0; i < rows.size() - 1; i++) {
            Media media = new Media();
            Element row = rows.get(i);
            Element eTitle = row.select("a[href^=#Play]").first();
            Element eSinger = row.select("a[href^=#Singer]").first();
            Element eArtist = row.select("a[href^=#Artist]").first();
            Element eCategory = row.select("a[href^=#List]").first();
            if (eTitle != null) {
                media.setId(ParserUtils.parseValue(eTitle.attr("href")));
                media.setTitle(eTitle.text());
            }
            if (eSinger != null) {
                Singer singer = new Singer();
                singer.setId(ParserUtils.parseValue(eTitle.attr("href")));
                singer.setName(eSinger.text());
                media.setSinger(singer);
            }
            if (eArtist != null) {
                Artist artist = new Artist();
                artist.setId(ParserUtils.parseValue(eTitle.attr("href")));
                artist.setName(eArtist.text());
                media.setArtist(artist);
            }
            if (eCategory != null) {
                Category category = new Category();
                category.setId(ParserUtils.parseValue(eTitle.attr("href")));
                category.setTitle(eCategory.text());
                media.setCategory(category);
            }
            Element eDownload = row.select("td:eq(2)").first();
            if (eDownload != null) {
                media.setDownloadNum(NumberUtils.toInt(eDownload.text()));
            }
            Elements eListen = row.select("td:eq(3)");
            if (eListen != null) {
                media.setListenNum(NumberUtils.toInt(eListen.text()));
            }
            Element eImgType = row.select("img[src$=media/type/music.gif]").first();
            media.setTypeMusic(false);
            if (eImgType != null) {
                media.setTypeMusic(true);
            }
            medias.add(media);
        }
        return songList;
    }
}
