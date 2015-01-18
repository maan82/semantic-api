package com.semantic.api.repository;

import com.semantic.api.specification.PeopleFeedSpecification;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Literal;
import org.openrdf.model.ValueFactory;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TupleQueryBuilder {
    public TupleQuery build(RepositoryConnection connection, PeopleFeedSpecification specification) throws MalformedQueryException, RepositoryException {
        String queryString = "PREFIX foaf:  <http://xmlns.com/foaf/0.1/> " +
            "PREFIX d: <http://dbpedia.org/ontology/> " +
            "select * { ?person foaf:name ?name " +
            addMixinQuery(specification.getMixins())+
            "} "+getPaging(specification.getPage(), specification.getPageSize());
        TupleQuery tupleQuery = connection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
        String personName = specification.getName();
        if (StringUtils.isNotEmpty(personName)) {
            ValueFactory factory = connection.getRepository().getValueFactory();
            Literal literal = factory.createLiteral(personName, "en");
            tupleQuery.setBinding("name", literal);
        }
        return tupleQuery;
    }

    private String addMixinQuery(Set<String> mixins) {
        StringBuilder stringBuilder = new StringBuilder("");
        if(mixins.contains("age")) {
            stringBuilder.append(";d:birthDate ?birthDate ");
        }
        if(mixins.contains("birth_place")) {
            stringBuilder.append(";d:birthPlace ?birthPlace ");
        }
        return stringBuilder.toString();
    }

    private String getPaging(Integer page, Integer pageSize) {
        return "LIMIT "+ (pageSize+1) +" OFFSET "+ (page - 1) * pageSize;
    }
}
