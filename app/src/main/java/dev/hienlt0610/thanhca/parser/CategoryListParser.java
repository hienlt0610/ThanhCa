package dev.hienlt0610.thanhca.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

import dev.hienlt0610.thanhca.model.BaseListModel;
import dev.hienlt0610.thanhca.model.BaseModel;
import dev.hienlt0610.thanhca.model.Category;
import dev.hienlt0610.thanhca.utils.ParserUtils;

/**
 * Created by hienl on 7/30/2017.
 */

public class CategoryListParser implements Parser {
    @Override
    public BaseModel parse(String html) {
        Document document = Jsoup.parse(html);
        BaseListModel<Category> baseListArtist = new BaseListModel<>();
        Elements categoryArea = document.select("td.title_l").parents().parents();
        if (categoryArea == null) {
            return baseListArtist;
        }
        Elements eListCate = categoryArea.select("a[href^=#List]");
        List<Category> categories = baseListArtist.getList();
        Category parentCate = null;
        for (Element element : eListCate) {
            Category category = new Category();
            int cateId = ParserUtils.parseValue(element.attr("href"));
            category.setId(cateId);
            category.setTitle(element.text());
            //Category parent
            if (element.getElementsByTag("b").size() != 0) {
                parentCate = category;
                categories.add(parentCate);
            } else {
                parentCate.getSubCategories().add(category);
            }
        }
        return baseListArtist;
    }
}
