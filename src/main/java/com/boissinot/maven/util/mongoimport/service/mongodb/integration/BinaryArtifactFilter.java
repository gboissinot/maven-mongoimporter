package com.boissinot.maven.util.mongoimport.service.mongodb.integration;

import com.boissinot.maven.util.mongoimport.domain.mongodb.MongoDBArtifactDocument;
import org.springframework.integration.annotation.Filter;

/**
 * @author Gregory Boissinot
 */
public class BinaryArtifactFilter {


    @Filter
    @SuppressWarnings("unused")
    public boolean filteringBinaryArtifacts(MongoDBArtifactDocument artifactObj) {
        return ("binary".equals(artifactObj.getType()));
    }
}
