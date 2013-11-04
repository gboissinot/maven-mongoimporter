package com.boissinot.maven.util.mongoimport;

/**
 * @author Gregory Boissinot
 */
public class MongoImportException extends RuntimeException {

    public MongoImportException() {
    }

    public MongoImportException(String s) {
        super(s);
    }

    public MongoImportException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MongoImportException(Throwable throwable) {
        super(throwable);
    }
}
