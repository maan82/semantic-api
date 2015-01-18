package com.semantic.api.repository;

public class DBPediaConnectionError extends RuntimeException {
    public DBPediaConnectionError(String message) {
        super(message);
    }
}
