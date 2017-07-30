package dev.hienlt0610.thanhca.parser;

import dev.hienlt0610.thanhca.model.BaseModel;

/**
 * Created by hienl on 7/29/2017.
 */

public interface Parser {
    BaseModel parse(String html);
}
