package com.semantic.api.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Pagination {
    private String previousPage;
    private String nextPage;

    public Pagination(String previousPage, String nextPage) {
        this.previousPage = previousPage;
        this.nextPage = nextPage;
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public String getNextPage() {
        return nextPage;
    }
}
