package com.boissinot.maven.util.mongoimport.service.mongodb.integration;

import com.boissinot.maven.util.mongoimport.domain.mongodb.MongoDBArtifactDocument;

/**
 * @author Gregory Boissinot
 */
public class ArtifactErrorLogger {

    public void log(MongoDBArtifactDocument artifactObj) {
        System.out.println("Error inserting " + artifactObj);
    }
}
