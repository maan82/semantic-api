package com.semantic.api.repository;

import com.semantic.api.specification.PeopleFeedSpecification;
import org.junit.Test;
import org.openrdf.model.Literal;
import org.openrdf.model.ValueFactory;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;

import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TupleQueryBuilderTest {
    private TupleQueryBuilder queryBuilder = new TupleQueryBuilder();

    @Test
    public void testBuildWhenEmptySpec() throws Exception {
        final RepositoryConnection connection = mock(RepositoryConnection.class);
        final PeopleFeedSpecification specification = mock(PeopleFeedSpecification.class);
        final TupleQuery tupleQuery = mock(TupleQuery.class);
        final String query = "PREFIX foaf:  <http://xmlns.com/foaf/0.1/> " +
            "PREFIX d: <http://dbpedia.org/ontology/> " +
            "select * { ?person foaf:name ?name } LIMIT 11 OFFSET 0";


        when(connection.prepareTupleQuery(QueryLanguage.SPARQL, query)).thenReturn(tupleQuery);
        when(specification.getPage()).thenReturn(1);
        when(specification.getPageSize()).thenReturn(10);

        TupleQuery actual = queryBuilder.build(connection, specification);

        assertEquals(tupleQuery, actual);
    }

    @Test
    public void testBuildWhenNameInSpec() throws Exception {
        final RepositoryConnection connection = mock(RepositoryConnection.class);
        final PeopleFeedSpecification specification = mock(PeopleFeedSpecification.class);
        final Repository repository = mock(Repository.class);
        final TupleQuery tupleQuery = mock(TupleQuery.class);
        final ValueFactory valueFactory = mock(ValueFactory.class);
        final Literal literal = mock(Literal.class);
        final String query = "PREFIX foaf:  <http://xmlns.com/foaf/0.1/> " +
            "PREFIX d: <http://dbpedia.org/ontology/> " +
            "select * { ?person foaf:name ?name } LIMIT 6 OFFSET 0";
        final String personName = "TEST NAME";

        when(specification.getName()).thenReturn(personName);
        when(connection.prepareTupleQuery(QueryLanguage.SPARQL, query)).thenReturn(tupleQuery);
        when(connection.getRepository()).thenReturn(repository);
        when(repository.getValueFactory()).thenReturn(valueFactory);
        when(valueFactory.createLiteral(personName, "en")).thenReturn(literal);
        when(specification.getPage()).thenReturn(1);
        when(specification.getPageSize()).thenReturn(5);

        TupleQuery actual = queryBuilder.build(connection, specification);

        assertEquals(tupleQuery, actual);
        verify(tupleQuery).setBinding("name", literal);
    }

    @Test
    public void testBuildWhenEmptyPageInSpec() throws Exception {
        final RepositoryConnection connection = mock(RepositoryConnection.class);
        final PeopleFeedSpecification specification = mock(PeopleFeedSpecification.class);
        final TupleQuery tupleQuery = mock(TupleQuery.class);
        final String query = "PREFIX foaf:  <http://xmlns.com/foaf/0.1/> " +
            "PREFIX d: <http://dbpedia.org/ontology/> " +
            "select * { ?person foaf:name ?name } LIMIT 11 OFFSET 80";

        when(connection.prepareTupleQuery(QueryLanguage.SPARQL, query)).thenReturn(tupleQuery);
        when(specification.getPage()).thenReturn(9);
        when(specification.getPageSize()).thenReturn(10);

        TupleQuery actual = queryBuilder.build(connection, specification);

        assertEquals(tupleQuery, actual);
    }

    @Test
    public void testBuildWhenMixinAgeInSpec() throws Exception {
        final RepositoryConnection connection = mock(RepositoryConnection.class);
        final PeopleFeedSpecification specification = mock(PeopleFeedSpecification.class);
        final TupleQuery tupleQuery = mock(TupleQuery.class);
        final HashSet<String> mixins = new HashSet<String>();
        mixins.add("age");
        final String query = "PREFIX foaf:  <http://xmlns.com/foaf/0.1/> " +
            "PREFIX d: <http://dbpedia.org/ontology/> " +
            "select * { ?person foaf:name ?name ;d:birthDate ?birthDate } LIMIT 11 OFFSET 80";

        when(connection.prepareTupleQuery(QueryLanguage.SPARQL, query)).thenReturn(tupleQuery);
        when(specification.getPage()).thenReturn(9);
        when(specification.getPageSize()).thenReturn(10);
        when(specification.getMixins()).thenReturn(mixins);

        TupleQuery actual = queryBuilder.build(connection, specification);

        assertEquals(tupleQuery, actual);
    }

    @Test
    public void testBuildWhenMixinBirthPlaceInSpec() throws Exception {
        final RepositoryConnection connection = mock(RepositoryConnection.class);
        final PeopleFeedSpecification specification = mock(PeopleFeedSpecification.class);
        final TupleQuery tupleQuery = mock(TupleQuery.class);
        final HashSet<String> mixins = new HashSet<String>();
        mixins.add("birth_place");
        final String query = "PREFIX foaf:  <http://xmlns.com/foaf/0.1/> " +
            "PREFIX d: <http://dbpedia.org/ontology/> " +
            "select * { ?person foaf:name ?name ;d:birthPlace ?birthPlace } LIMIT 11 OFFSET 80";

        when(connection.prepareTupleQuery(QueryLanguage.SPARQL, query)).thenReturn(tupleQuery);
        when(specification.getPage()).thenReturn(9);
        when(specification.getPageSize()).thenReturn(10);
        when(specification.getMixins()).thenReturn(mixins);

        TupleQuery actual = queryBuilder.build(connection, specification);

        assertEquals(tupleQuery, actual);
    }

}