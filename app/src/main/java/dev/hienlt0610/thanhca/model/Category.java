package dev.hienlt0610.thanhca.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hienl on 7/30/2017.
 */

public class Category {
    private int id;
    private String title;
    private List<Category> subCates;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Category> getSubCategories() {
        if (subCates == null) {
            subCates = new ArrayList<>();
        }
        return subCates;
    }

    public void setSubCategories(List<Category> subCates) {
        this.subCates = subCates;
    }
}
