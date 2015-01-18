package com.semantic.api.controller;

import com.semantic.api.domain.FeedItem;
import com.semantic.api.domain.PagedFeed;
import com.semantic.api.domain.ResultList;
import com.semantic.api.repository.PeopleRepository;
import com.semantic.api.factory.PagedFeedFactory;
import com.semantic.api.factory.PeopleFeedSpecificationFactory;
import com.semantic.api.specification.PeopleFeedSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


@Controller
@RequestMapping(value = PeopleController.BASE_PATH)
public class PeopleController {
    public static final String BASE_PATH = "/people";

    @Autowired
    private PeopleFeedSpecificationFactory peopleFeedSpecificationFactory;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PagedFeedFactory pagedFeedFactory;

    @Autowired
    private Paging paging;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody PagedFeed getPeople(
        @RequestParam(value = "name", required = false) String personName,
        @RequestParam(value = "mixin", required = false) String[] mixin,
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "page_size", required = false) Integer pageSize
    ) {
        PeopleFeedSpecification peopleFeedSpecification =
            peopleFeedSpecificationFactory
                .create(BASE_PATH, personName, mixin, paging.getPage(page), paging.getPageSize(pageSize));
        ResultList<FeedItem> persons = peopleRepository.find(peopleFeedSpecification);
        return pagedFeedFactory.create(persons, peopleFeedSpecification);
    }

}

