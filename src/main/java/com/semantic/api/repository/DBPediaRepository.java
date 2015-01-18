package com.semantic.api.repository;

import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sparql.SPARQLRepository;

public class DBPediaRepository {

    public RepositoryConnection getConnection() {
        Repository sparqlRepository = new SPARQLRepository("http://dbpedia.org/sparql");
        try {
            sparqlRepository.initialize();
            return sparqlRepository.getConnection();
        } catch (RepositoryException e) {
            throw new DBPediaConnectionError("Connection setup to DBpedia failed");
        }
    }
}
