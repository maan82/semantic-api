package com.semantic.api.controller;

import org.springframework.stereotype.Component;

@Component
public class Paging {
    public Integer getPage(Integer page) {
        return page == null ? 1 : page;
    }

    public Integer getPageSize(Integer pageSize) {
        return pageSize == null ? 10 : pageSize;
    }
}
