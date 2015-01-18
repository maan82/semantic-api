package com.semantic.api.factory;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class PeopleFeedSpecificationFactoryTest {
    private PeopleFeedSpecificationFactory factory = new PeopleFeedSpecificationFactory();

    @Test
    public void testCreateShouldSetBasePath() {
        String actual = factory.create("base", null, null, null, null).getBasePath();
        assertEquals("base", actual);
    }

    @Test
    public void testCreateShouldSetName() {
        String actual = factory.create(null, "name", null, null, null).getName();
        assertEquals("name", actual);
    }

    @Test
    public void testCreateShouldSetPage() {
        Integer actual = factory.create(null, null, null, 2, null).getPage();
        assertEquals(new Integer(2), actual);
    }

    @Test
    public void testCreateShouldSetPageNumber() {
        Integer actual = factory.create(null, null, null, null, 10).getPageSize();
        assertEquals(new Integer(10), actual);
    }

    @Test
    public void testCreateShouldSetMixinWhenEmptyArray() {
        String[] mixin = new String[]{};
        Set<String> actual = factory.create(null, null, mixin, null, null).getMixins();
        assertTrue(actual.isEmpty());
    }

    @Test
    public void testCreateShouldSetMixin() {
        String[] mixin = new String[]{"age"};
        Set<String> actual = factory.create(null, null, mixin, null, null).getMixins();
        assertTrue(actual.contains("age"));
    }

}