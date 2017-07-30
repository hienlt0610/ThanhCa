package dev.hienlt0610.thanhca.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import dev.hienlt0610.thanhca.model.BaseModel;

/**
 * Created by hienl on 7/30/2017.
 */

public class StatisticParser implements Parser {
    @Override
    public BaseModel parse(String html) {
        Document document = Jsoup.parse(html);
        Element first = document.select("body > div:nth-child(1) > div").first();
        return null;
    }
}
