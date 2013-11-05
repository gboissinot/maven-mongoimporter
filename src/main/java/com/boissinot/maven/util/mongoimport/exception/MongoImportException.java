package com.boissinot.maven.util.mongoimport.exception;

/**
 * @author Gregory Boissinot
 */
public class MongoImportException extends RuntimeException {

    public MongoImportException(Throwable throwable) {
        super(throwable);
    }
}
