package com.semantic.api.controller;

import com.semantic.api.domain.FeedItem;
import com.semantic.api.domain.PagedFeed;
import com.semantic.api.domain.ResultList;
import com.semantic.api.factory.PagedFeedFactory;
import com.semantic.api.factory.PeopleFeedSpecificationFactory;
import com.semantic.api.repository.PeopleRepository;
import com.semantic.api.specification.PeopleFeedSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.semantic.api.controller.PeopleController.BASE_PATH;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PeopleControllerTest {

    @Mock
    private PeopleFeedSpecificationFactory peopleFeedSpecificationFactory;

    @Mock
    private PeopleRepository peopleRepository;

    @Mock
    private PagedFeedFactory pagedFeedFactory;

    @Mock
    private Paging paging;

    @InjectMocks
    private PeopleController peopleController = new PeopleController();

    @Test
    public void testGetPeopleShouldCreatePeopleFeedWhenSpecWithAllParameters() {
        final PeopleFeedSpecification specification = mock(PeopleFeedSpecification.class);
        final PagedFeed expected = mock(PagedFeed.class);
        final ResultList<FeedItem> personList = new ResultList<FeedItem>();

        final String personName = "test name";
        final Integer page = 1;
        final Integer pageSize = 10;
        final String[] mixins = new String[]{"age"};

        when(paging.getPage(1)).thenReturn(1);
        when(paging.getPageSize(10)).thenReturn(10);
        when(peopleFeedSpecificationFactory
            .create(BASE_PATH, personName, mixins, page, pageSize))
            .thenReturn(specification);
        when(peopleRepository.find(specification)).thenReturn(personList);
        when(pagedFeedFactory.create(personList, specification)).thenReturn(expected);

        PagedFeed actual = peopleController.getPeople(personName, mixins, 1, 10);

        assertThat(actual, is(expected));
    }

    @Test
    public void testGetPeopleShouldSetPaging() {
        final PeopleFeedSpecification specification = mock(PeopleFeedSpecification.class);
        final PagedFeed expected = mock(PagedFeed.class);
        final ResultList<FeedItem> personList = new ResultList<FeedItem>();

        final String personName = "test name";
        final String[] mixins = new String[]{"age"};

        when(paging.getPage(null)).thenReturn(5);
        when(paging.getPageSize(null)).thenReturn(20);
        when(peopleFeedSpecificationFactory
            .create(BASE_PATH, personName, mixins, 5, 20))
            .thenReturn(specification);
        when(peopleRepository.find(specification)).thenReturn(personList);
        when(pagedFeedFactory.create(personList, specification)).thenReturn(expected);

        PagedFeed actual = peopleController.getPeople(personName, mixins, null, null);

        assertThat(actual, is(expected));
        verify(paging).getPage(null);
        verify(paging).getPageSize(null);
    }

}