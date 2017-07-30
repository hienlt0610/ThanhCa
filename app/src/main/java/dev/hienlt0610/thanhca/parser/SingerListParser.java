package dev.hienlt0610.thanhca.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

import dev.hienlt0610.thanhca.model.BaseListModel;
import dev.hienlt0610.thanhca.model.BaseModel;
import dev.hienlt0610.thanhca.model.Singer;
import dev.hienlt0610.thanhca.utils.ParserUtils;

/**
 * Created by hienl on 7/30/2017.
 */

public class SingerListParser implements Parser {
    @Override
    public BaseModel parse(String html) {
        Document document = Jsoup.parse(html);
        BaseListModel<Singer> baseListArtist = new BaseListModel<>();
        Elements artistArea = document.select("td.title_r").parents().parents();
        if (artistArea == null) {
            return baseListArtist;
        }
        Elements eListSinger = artistArea.select("a[href^=#Singer]");
        List<Singer> singers = baseListArtist.getList();
        for (Element element : eListSinger) {
            Singer singer = new Singer();
            int id = ParserUtils.parseValue(element.attr("href"));
            singer.setId(id);
            singer.setName(element.text());
            singers.add(singer);
        }
        return baseListArtist;
    }
}
