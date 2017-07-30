package dev.hienlt0610.thanhca.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Created by hienl on 7/30/2017.
 */

public class ParserUtils {
    /**
     * Parse id from url param
     *
     * @return id
     */
    public static int parseValue(String urlParams) {
        if (StringUtils.isEmpty(urlParams)) {
            return 0;
        }
        String[] split = StringUtils.split(urlParams, ',');
        if (split.length < 2) {
            return 0;
        }
        int id = NumberUtils.toInt(split[1]);
        return id;
    }

}
