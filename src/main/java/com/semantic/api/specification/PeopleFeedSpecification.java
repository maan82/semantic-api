package com.semantic.api.specification;

import com.semantic.api.domain.Filter;
import com.semantic.api.domain.Mixin;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PeopleFeedSpecification implements FeedSpecification {
    public static final String URI_BUILD_ERROR = "URI build error";
    public static final String BIRTH_PLACE = "birth_place";
    public static final String AGE = "age";
    private String basePath;
    private String name;
    private Integer page;
    private Integer pageSize;
    private Set<String> mixins;

    public PeopleFeedSpecification(String basePath, String name, Set<String> mixins, Integer page, Integer pageSize) {
        this.basePath = basePath;
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
        this.mixins = mixins;
    }

    public String getBasePath() {
        return basePath;
    }

    public String getName() {
        return name;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Set<String> getMixins() {
        return mixins;
    }

    @Override
    public String getNextPageLink() {
        return buildUri(page != null ? new Integer(page + 1): page).toString();
    }

    @Override
    public String getPreviousPageLink() {
        return buildUri(page != null ? new Integer(page - 1): page).toString();
    }

    public List<Filter> getRemainingFilters() {
        List<Filter> filters = new ArrayList<Filter>();
        if (getName() == null) {
           filters.add(new Filter("name", "false", "string", "Filter for subset of people that have name", null));
        }
        if (getPage() == null) {
            filters.add(new Filter("page", "false", "integer", "which page of results to return", null));
        }
        if (getPageSize() == null) {
            filters.add(new Filter("page_size", "false", "integer", "number of results in each page", null));
        }
        return filters;
    }

    public List<Mixin> getRemainingMixins() {
        List<Mixin> mixinList = new ArrayList<Mixin>();
        try {
            if (!getMixins().contains(AGE)) {
                URIBuilder builder = new URIBuilder(buildUri(getPage()));
                builder.addParameter("mixin", AGE);
                mixinList.add(new Mixin(AGE, "mixin to return age of the person", builder.build().toString()));
            }
            if (!getMixins().contains(BIRTH_PLACE)) {
                URIBuilder builder = new URIBuilder(buildUri(getPage()));
                builder.addParameter("mixin", BIRTH_PLACE);
                mixinList.add(new Mixin(BIRTH_PLACE, "mixin to return place of birth", builder.build().toString()));
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(URI_BUILD_ERROR);
        }
        return mixinList;
    }

    URIBuilder create() {
        return new URIBuilder();
    }

    private URI buildUri(Integer pageNumber) {
        URIBuilder builder = create();
        builder.setPath(basePath);
        addParameter(builder, "name", name);
        for (String mixin:new TreeSet<String>(mixins)) {
            addParameter(builder, "mixin", mixin);
        }
        addParameter(builder, "page", pageNumber);
        addParameter(builder, "page_size", pageSize);
        try {
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(URI_BUILD_ERROR);
        }
    }

    private void addParameter(URIBuilder uriBuilder, String name, Object value) {
        if (value != null) {
            uriBuilder.addParameter(name, value.toString());
        }
    }
}
