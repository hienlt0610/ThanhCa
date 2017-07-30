package dev.hienlt0610.thanhca.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hienl on 7/30/2017.
 */

public class BaseListModel<T> extends BaseModel {
    private List<T> mList;

    public List<T> getList() {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        return mList;
    }

    public void setList(List<T> list) {
        mList = list;
    }
}
