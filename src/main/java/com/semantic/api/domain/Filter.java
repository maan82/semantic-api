package com.semantic.api.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.net.URI;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Filter {
    private String name;
    private String required;
    private String type;
    private String title;
    private String href;

    public Filter(String name, String required, String type, String title, String href) {
        this.name = name;
        this.required = required;
        this.type = type;
        this.title = title;
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public String getRequired() {
        return required;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getHref() {
        return href;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filter filter = (Filter) o;

        if (href != null ? !href.equals(filter.href) : filter.href != null) return false;
        if (name != null ? !name.equals(filter.name) : filter.name != null) return false;
        if (required != null ? !required.equals(filter.required) : filter.required != null) return false;
        if (title != null ? !title.equals(filter.title) : filter.title != null) return false;
        if (type != null ? !type.equals(filter.type) : filter.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (required != null ? required.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (href != null ? href.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Filter{" +
            "name='" + name + '\'' +
            ", required='" + required + '\'' +
            ", type='" + type + '\'' +
            ", title='" + title + '\'' +
            ", href='" + href + '\'' +
            '}';
    }
}
