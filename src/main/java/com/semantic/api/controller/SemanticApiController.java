package com.semantic.api.controller;

import com.semantic.api.domain.Feed;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/")
public class SemanticApiController {
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody
    List<Feed> getFeeds() {
        ArrayList<Feed> greetings = new ArrayList<Feed>();
        greetings.add(new Feed(counter.incrementAndGet(), "People", "/people"));

        return greetings;
    }

}
