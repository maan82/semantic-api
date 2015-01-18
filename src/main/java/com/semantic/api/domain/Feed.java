package com.semantic.api.domain;

public class Feed {
    private final long id;
    private final String name;
    private final String link;

    public Feed(long id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }
}
