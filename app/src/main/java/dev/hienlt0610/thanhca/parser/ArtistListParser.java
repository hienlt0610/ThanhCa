package dev.hienlt0610.thanhca.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

import dev.hienlt0610.thanhca.model.Artist;
import dev.hienlt0610.thanhca.model.BaseListModel;
import dev.hienlt0610.thanhca.model.BaseModel;
import dev.hienlt0610.thanhca.utils.ParserUtils;

/**
 * Created by hienl on 7/30/2017.
 */

public class ArtistListParser implements Parser {
    @Override
    public BaseModel parse(String html) {
        Document document = Jsoup.parse(html);
        BaseListModel<Artist> baseListArtist = new BaseListModel<>();
        Elements artistArea = document.select("td.title_r").parents().parents();
        if (artistArea == null) {
            return baseListArtist;
        }
        Elements eListArtist = artistArea.select("a[href^=#Artist]");
        List<Artist> artists = baseListArtist.getList();
        for (Element element : eListArtist) {
            Artist artist = new Artist();
            int id = ParserUtils.parseValue(element.attr("href"));
            artist.setId(id);
            artist.setName(element.text());
            artists.add(artist);
        }
        return baseListArtist;
    }
}
