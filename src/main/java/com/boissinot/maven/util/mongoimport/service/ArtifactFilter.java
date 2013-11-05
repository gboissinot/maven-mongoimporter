package com.boissinot.maven.util.mongoimport.service;

import com.boissinot.maven.util.mongoimport.domain.ArtifactObj;
import org.springframework.integration.annotation.Filter;

/**
 * @author Gregory Boissinot
 */
public class ArtifactFilter {

    @Filter
    @SuppressWarnings("unused")
    public boolean isValidArtifact(ArtifactObj artifactObj) {
        return true;
    }
}
