package com.semantic.api.repository;

import com.semantic.api.domain.FeedItem;
import com.semantic.api.domain.ResultList;
import com.semantic.api.specification.PeopleFeedSpecification;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PeopleRepository extends DBPediaRepository {
    @Autowired
    private TupleQueryBuilder queryBuilder;

    @Autowired
    private PersonBuilder personBuilder;

    public ResultList<FeedItem> find(PeopleFeedSpecification specification) {
        RepositoryConnection connection = getConnection();
        try {
            TupleQuery tupleQuery = queryBuilder.build(connection, specification);
            TupleQueryResult result = tupleQuery.evaluate();
            ResultList<FeedItem> persons = new ResultList<FeedItem>();
            while (result.hasNext()) {
                persons.add(personBuilder.build(result.next()));
            }
            return persons;
        } catch (Exception ex) {
            throw new QueryExecutionException("Failed to execute query");
        }
    }
}
