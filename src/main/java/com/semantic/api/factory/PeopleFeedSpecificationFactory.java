package com.semantic.api.factory;

import com.semantic.api.specification.PeopleFeedSpecification;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class PeopleFeedSpecificationFactory {

    public PeopleFeedSpecification create(String basePath, String personName, String[] mixin,Integer page, Integer pageSize) {
        Set<String> mixinsSet = new HashSet<String>();
        if (mixin != null) {
            mixinsSet.addAll(Arrays.asList(mixin));
        }
        return new PeopleFeedSpecification(basePath, personName, mixinsSet, page, pageSize);
    }
}
