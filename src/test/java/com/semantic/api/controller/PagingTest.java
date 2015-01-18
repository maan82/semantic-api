package com.semantic.api.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class PagingTest {
    @Test
    public void testGetPageShouldReturnDefaultWhenPageIsNull() {
        Paging paging = new Paging();
        assertEquals(new Integer(1), paging.getPage(null));
    }

    @Test
    public void testGetPageShouldReturnPageWhenPageIsNotNull() {
        Paging paging = new Paging();
        assertEquals(new Integer(10), paging.getPage(10));
    }

    @Test
    public void testGetPageSizeShouldReturnDefaultWhenPageSizeIsNull() {
        Paging paging = new Paging();
        assertEquals(new Integer(10), paging.getPageSize(null));
    }

    @Test
    public void testGetPageSizeShouldReturnPageWhenPageSizeIsNotNull() {
        Paging paging = new Paging();
        assertEquals(new Integer(100), paging.getPageSize(100));
    }

}