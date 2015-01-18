package com.semantic.api.domain;

import java.util.List;

public class FrontPage {
    private List<Feed> feeds;

    public FrontPage(List<Feed> feeds) {
        this.feeds = feeds;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }
}
