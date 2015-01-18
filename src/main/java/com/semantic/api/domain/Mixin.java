package com.semantic.api.domain;

import java.net.URI;

public class Mixin {
    private String name;
    private String title;
    private String href;

    public Mixin(String name, String title, String href) {
        this.name = name;
        this.title = title;
        this.href = href;
    }

    public String getName() {
        return name;
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

        Mixin mixin = (Mixin) o;

        if (href != null ? !href.equals(mixin.href) : mixin.href != null) return false;
        if (name != null ? !name.equals(mixin.name) : mixin.name != null) return false;
        if (title != null ? !title.equals(mixin.title) : mixin.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (href != null ? href.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mixin{" +
            "name='" + name + '\'' +
            ", title='" + title + '\'' +
            ", href='" + href + '\'' +
            '}';
    }
}
