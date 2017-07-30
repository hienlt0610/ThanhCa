package dev.hienlt0610.thanhca.parser;

import dev.hienlt0610.thanhca.model.Artist;
import dev.hienlt0610.thanhca.model.BaseListModel;
import dev.hienlt0610.thanhca.model.BaseModel;
import dev.hienlt0610.thanhca.model.Category;
import dev.hienlt0610.thanhca.model.HomeContent;
import dev.hienlt0610.thanhca.model.Singer;
import dev.hienlt0610.thanhca.model.Statistic;

/**
 * Created by hienl on 7/30/2017.
 */

public class HomeContentParser implements Parser {
    @Override
    public BaseModel parse(String html) {
        HomeContent homeContent = new HomeContent();
        homeContent.setCategories((BaseListModel<Category>) new CategoryListParser().parse(html));
        homeContent.setArtists((BaseListModel<Artist>) new ArtistListParser().parse(html));
//        homeContent.setNewAlbums((BaseListModel<Album>) new NewAlbumParser().parse(html));
        homeContent.setSingers((BaseListModel<Singer>) new SingerListParser().parse(html));
        homeContent.setStatistic((Statistic) new StatisticParser().parse(html));
        return homeContent;
    }
}
