package com.semantic.api.factory;

import com.semantic.api.domain.*;
import com.semantic.api.specification.FeedSpecification;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PagedFeedFactoryTest {
    private PagedFeedFactory pagedFeedFactory = new PagedFeedFactory();

    @Test
    public void testCreateShouldAddFeedItemsInPagedFeed() {
        ResultList<FeedItem> personList = new ResultList<FeedItem>();

        PagedFeed actual = pagedFeedFactory.create(personList, mock(FeedSpecification.class));

        assertThat(actual.getItemList(), is(personList));
    }

    @Test
    public void testCreateShouldSetNextPage() {
        ResultList resultList = mock(ResultList.class);
        FeedSpecification feedSpecification = mock(FeedSpecification.class);
        String expected = "next link";

        when(feedSpecification.getPageSize()).thenReturn(1);
        when(resultList.size()).thenReturn(2);
        when(feedSpecification.getNextPageLink()).thenReturn(expected);

        String actual = pagedFeedFactory.create(resultList, feedSpecification).getPagination().getNextPage();

        assertEquals(expected, actual);

    }

    @Test
    public void testCreateShouldNotSetNextPage() {
        ResultList resultList = mock(ResultList.class);
        FeedSpecification feedSpecification = mock(FeedSpecification.class);

        when(feedSpecification.getPageSize()).thenReturn(10);
        when(resultList.size()).thenReturn(10);
        when(feedSpecification.getPage()).thenReturn(3);
        when(feedSpecification.getPreviousPageLink()).thenReturn("prev");

        String actual = pagedFeedFactory.create(resultList, feedSpecification).getPagination().getNextPage();

        assertNull(actual);
    }

    @Test
    public void testCreateShouldSetPreviousPage() {
        ResultList resultList = mock(ResultList.class);
        FeedSpecification feedSpecification = mock(FeedSpecification.class);
        String expected = "previous link";

        when(feedSpecification.getPage()).thenReturn(2);
        when(feedSpecification.getPreviousPageLink()).thenReturn(expected);

        String actual = pagedFeedFactory.create(resultList, feedSpecification).getPagination().getPreviousPage();

        assertEquals(expected, actual);
    }

    @Test
    public void testCreateShouldNotSetPreviousPage() {
        ResultList resultList = mock(ResultList.class);
        FeedSpecification feedSpecification = mock(FeedSpecification.class);

        when(feedSpecification.getPage()).thenReturn(1);
        when(feedSpecification.getPageSize()).thenReturn(1);
        when(resultList.size()).thenReturn(2);
        when(feedSpecification.getNextPageLink()).thenReturn("next");

        String actual = pagedFeedFactory.create(resultList, feedSpecification).getPagination().getPreviousPage();

        assertNull(actual);
    }

    @Test
    public void testCreateShouldSetPaginationToNullWhenPreviousNextNull() {
        ResultList resultList = mock(ResultList.class);
        FeedSpecification feedSpecification = mock(FeedSpecification.class);

        when(feedSpecification.getPage()).thenReturn(1);
        when(feedSpecification.getPageSize()).thenReturn(10);
        when(resultList.size()).thenReturn(5);

        Pagination actual = pagedFeedFactory.create(resultList, feedSpecification).getPagination();

        assertNull(actual);
    }

}
