package com.ywsoftware.oa.modules.sys.domain;

import java.io.Serializable;
import java.util.List;

public class PaginatedItems<TEntity> implements Serializable {

    private List<TEntity> items;
    private long total;

    public PaginatedItems(List<TEntity> items, long total) {
        if (items == null) {
            throw new IllegalArgumentException("items cannot be null.");
        }

        this.items = items;
        this.total = total;
    }

    public List<TEntity> getItems() {
        return items;
    }

    public long getTotal() {
        return total;
    }
}
