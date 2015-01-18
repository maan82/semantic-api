package com.semantic.api.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PagedFeed {

    private Pagination pagination;
    private ResultList<FeedItem> itemList;
    private List<Filter> filters;
    private List<Mixin> mixins;

    public PagedFeed(Pagination pagination, ResultList<FeedItem> itemList, List<Filter> filters, List<Mixin> mixins) {
        this.pagination = pagination;
        this.itemList = itemList;
        this.filters = filters;
        this.mixins = mixins;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public ResultList<FeedItem> getItemList() {
        return itemList;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public List<Mixin> getMixins() {
        return mixins;
    }
}
