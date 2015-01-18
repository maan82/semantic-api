package com.semantic.api.factory;

import com.semantic.api.domain.*;
import com.semantic.api.specification.FeedSpecification;
import org.springframework.stereotype.Component;

@Component
public class PagedFeedFactory {
    public PagedFeed create(ResultList<FeedItem> personList, FeedSpecification feedSpecification) {
        String nextPageLink =
            personList.size() > feedSpecification.getPageSize() ? feedSpecification.getNextPageLink() : null;
        String previousPageLink =
            feedSpecification.getPage() > 1 ? feedSpecification.getPreviousPageLink() : null;
        Pagination pagination =
            nextPageLink == null && previousPageLink == null ? null : new Pagination(previousPageLink, nextPageLink);

        return new PagedFeed(pagination, personList, feedSpecification.getRemainingFilters(), feedSpecification.getRemainingMixins());
    }
}
