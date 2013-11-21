package com.boissinot.maven.util.mongoimport.service.couchbase.integration;

import com.boissinot.maven.util.mongoimport.domain.couchbase.CouchbaseArtifactDocument;
import org.springframework.integration.annotation.Filter;

/**
 * @author Gregory Boissinot
 */
public class POMArtifactFilter {

    @Filter
    @SuppressWarnings("unused")
    public boolean isNotPOMArtifact(CouchbaseArtifactDocument artifactObj) {
        return (!"pom".equals(artifactObj.getFileExtension()));
    }
}
