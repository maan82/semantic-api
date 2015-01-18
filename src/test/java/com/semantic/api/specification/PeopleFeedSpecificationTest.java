package com.semantic.api.specification;

import com.semantic.api.domain.Filter;
import com.semantic.api.domain.Mixin;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PeopleFeedSpecificationTest {

    @Test
    public void testGetNextPageLinkShouldSetParameters() {
        Set<String> mixins = new HashSet<String>();
        mixins.add("age");
        mixins.add("birth_place");
        PeopleFeedSpecification specification = new PeopleFeedSpecification("/base", "name", mixins, 2, 10);
        assertEquals("/base?name=name&mixin=age&mixin=birth_place&page=3&page_size=10", specification.getNextPageLink());
    }

    @Test
    public void testGetNextPageLinkShouldNotSetParametersWhenNotInRequest() {
        PeopleFeedSpecification specification = new PeopleFeedSpecification("/base", null, new HashSet<String>(), null, null);
        assertEquals("/base", specification.getNextPageLink());
    }

    @Test
    public void testGetNextPageLinkShouldEncode() {
        PeopleFeedSpecification specification = new PeopleFeedSpecification("/base", "full+name ", new HashSet<String>(), 2, 10);
        assertEquals("/base?name=full%2Bname+&page=3&page_size=10", specification.getNextPageLink());
    }

    @Test
    public void testGetNextPageLinkShouldThrowRunTimeExceptionWhenURIBuilderThrowsException() throws Exception {
        final URIBuilder uriBuilder = mock(URIBuilder.class);
        when(uriBuilder.build()).thenThrow(mock(URISyntaxException.class));
        PeopleFeedSpecification specification = new PeopleFeedSpecification("/base", "full+name ", new HashSet<String>(), 2, 10){
            @Override
            URIBuilder create() {
                return uriBuilder;
            }
        };

        try {
            specification.getNextPageLink();
            fail();
        } catch (RuntimeException ex) {
            assertEquals(specification.URI_BUILD_ERROR, ex.getMessage());
        }
    }

    @Test
    public void testGetPreviousPageLinkShouldSetParameters() {
        Set<String> mixins = new HashSet<String>();
        mixins.add("age");
        mixins.add("birth_place");
        PeopleFeedSpecification specification = new PeopleFeedSpecification("/base", "name", mixins, 5, 10);
        assertEquals("/base?name=name&mixin=age&mixin=birth_place&page=4&page_size=10", specification.getPreviousPageLink());
    }

    @Test
    public void testGetPreviousPageLinkShouldNotSetParametersWhenNotInRequest() {
        PeopleFeedSpecification specification = new PeopleFeedSpecification("/base", null, new HashSet<String>(), null, null);
        assertEquals("/base", specification.getPreviousPageLink());
    }

    @Test
    public void testGetPreviousPageLinkShouldEncode() {
        PeopleFeedSpecification specification = new PeopleFeedSpecification("/base", "full+name ", new HashSet<String>(), 2, 10);
        assertEquals("/base?name=full%2Bname+&page=1&page_size=10", specification.getPreviousPageLink());
    }

    @Test
    public void testGetPreviousPageLinkShouldThrowRunTimeExceptionWhenURIBuilderThrowsException() throws Exception {
        final URIBuilder uriBuilder = mock(URIBuilder.class);
        when(uriBuilder.build()).thenThrow(mock(URISyntaxException.class));
        PeopleFeedSpecification specification = new PeopleFeedSpecification("/base", "full+name ", new HashSet<String>(), 2, 10){
            @Override
            URIBuilder create() {
                return uriBuilder;
            }
        };

        try {
            specification.getPreviousPageLink();
            fail();
        } catch (RuntimeException ex) {
            assertEquals(specification.URI_BUILD_ERROR, ex.getMessage());
        }
    }

    @Test
    public void testGetRemainingFiltersShouldAddName() {
        PeopleFeedSpecification specification = new PeopleFeedSpecification("/base", null, new HashSet<String>(), 2, 10);

        Filter expected = new Filter("name", "false", "string", "Filter for subset of people that have name", null);
        assertEquals(expected, specification.getRemainingFilters().get(0));
    }

    @Test
    public void testGetRemainingFiltersShouldAddPage() {
        PeopleFeedSpecification specification = new PeopleFeedSpecification("/base", "name", new HashSet<String>(), null, 10);

        Filter expected = new Filter("page", "false", "integer", "which page of results to return", null);
        assertEquals(expected, specification.getRemainingFilters().get(0));
    }

    @Test
    public void testGetRemainingFiltersShouldAddPageNumber() {
        PeopleFeedSpecification specification = new PeopleFeedSpecification("/base", "name", new HashSet<String>(), 2, null);

        Filter expected = new Filter("page_size", "false", "integer", "number of results in each page", null);
        assertEquals(expected, specification.getRemainingFilters().get(0));
    }

    @Test
    public void testGetRemainingMixinsShouldAddAge() {
        HashSet<String> mixins = new HashSet<String>();
        PeopleFeedSpecification specification = new PeopleFeedSpecification("/base", null, mixins, null, null);

        Mixin expected = new Mixin("age", "mixin to return age of the person", "/base?mixin=age");
        assertEquals(expected, specification.getRemainingMixins().get(0));
    }

    @Test
    public void testGetRemainingFiltersShouldAddBirthPlace() {
        PeopleFeedSpecification specification = new PeopleFeedSpecification("/base", null, new HashSet<String>(), null, null);

        Mixin expected = new Mixin("birth_place", "mixin to return place of birth", "/base?mixin=birth_place");
        assertEquals(expected, specification.getRemainingMixins().get(1));
    }

}