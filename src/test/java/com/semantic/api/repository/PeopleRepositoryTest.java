package com.semantic.api.repository;

import com.semantic.api.domain.FeedItem;
import com.semantic.api.domain.Person;
import com.semantic.api.domain.ResultList;
import com.semantic.api.specification.PeopleFeedSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.openrdf.model.ValueFactory;
import org.openrdf.query.*;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PeopleRepositoryTest {
    final private RepositoryConnection connection = mock(RepositoryConnection.class);

    @Mock
    private TupleQueryBuilder queryBuilder;

    @Mock
    private PersonBuilder personBuilder;

    @InjectMocks
    private PeopleRepository peopleRepository = new PeopleRepository() {
        @Override
        public RepositoryConnection getConnection() {
            return connection;
        }
    };

    @Test
    public void testFindShouldConstructAndExecuteQuery() throws Exception {
        final PeopleFeedSpecification specification = mock(PeopleFeedSpecification.class);
        final TupleQuery tupleQuery = mock(TupleQuery.class);
        final TupleQueryResult queryResult = mock(TupleQueryResult.class);

        when(queryBuilder.build(connection, specification)).thenReturn(tupleQuery);
        when(tupleQuery.evaluate()).thenReturn(queryResult);
        when(queryResult.hasNext()).thenReturn(false);

        ResultList<FeedItem> feedItems = peopleRepository.find(specification);

        verify(tupleQuery).evaluate();
        assertTrue(feedItems.isEmpty());
    }

    @Test
    public void testFindShouldConstructPersonObject() throws Exception {
        final PeopleFeedSpecification specification = mock(PeopleFeedSpecification.class);
        final TupleQuery tupleQuery = mock(TupleQuery.class);
        final TupleQueryResult queryResult = mock(TupleQueryResult.class);
        final BindingSet bindingSet = mock(BindingSet.class);
        final Person person = mock(Person.class);
        final ResultList<Person> expected = new ResultList<Person>();
        expected.add(person);

        when(queryBuilder.build(connection, specification)).thenReturn(tupleQuery);
        when(tupleQuery.evaluate()).thenReturn(queryResult);
        when(queryResult.hasNext()).thenReturn(true, false);
        when(queryResult.next()).thenReturn(bindingSet);
        when(personBuilder.build(bindingSet)).thenReturn(person);

        ResultList<FeedItem> actual = peopleRepository.find(specification);

        assertEquals(expected, actual);
   }

    @Test
    public void testFindShouldThrowQueryExecutionException() throws Exception {
        final PeopleFeedSpecification specification = mock(PeopleFeedSpecification.class);
        final MalformedQueryException expected = mock(MalformedQueryException.class);

        when(queryBuilder.build(connection, specification)).thenThrow(expected);

        try {
            peopleRepository.find(specification);
            fail();
        } catch (QueryExecutionException ex) {
            assertEquals("Failed to execute query", ex.getMessage());
        }
    }

}