package com.boissinot.maven.util.mongoimport.repository.couchbase;

import com.boissinot.maven.util.mongoimport.domain.couchbase.CouchbaseArtifactDocument;
import org.springframework.data.couchbase.core.CouchbaseOperations;

/**
 * @author Gregory Boissinot
 */
public class CouchbaseRepository {

    private CouchbaseOperations couchbaseTemplate;

    public CouchbaseRepository(CouchbaseOperations couchbaseTemplate) {
        this.couchbaseTemplate = couchbaseTemplate;
    }

    public void insert(CouchbaseArtifactDocument artifactObj) {
        couchbaseTemplate.insert(artifactObj);
    }
}
