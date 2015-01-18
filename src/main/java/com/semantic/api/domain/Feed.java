package com.semantic.api.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Feed {
    private final long id;
    private final String name;
    private final String title;
    private final String href;

    public Feed(long id, String name, String title, String href) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getHref() {
        return href;
    }
}
