package com.semantic.api.specification;

import com.semantic.api.domain.Filter;
import com.semantic.api.domain.Mixin;

import java.util.List;

public interface FeedSpecification {
    String getNextPageLink();
    String getPreviousPageLink();
    Integer getPageSize();
    Integer getPage();
    List<Filter> getRemainingFilters();
    List<Mixin> getRemainingMixins();
}
