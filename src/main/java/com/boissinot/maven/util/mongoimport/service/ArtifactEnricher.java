package com.boissinot.maven.util.mongoimport.service;

import com.boissinot.maven.util.mongoimport.domain.ArtifactObj;
import org.springframework.integration.annotation.Transformer;

/**
 * @author Gregory Boissinot
 */
public class ArtifactEnricher {

    @Transformer
    @SuppressWarnings("unused")
    public ArtifactObj enrich(ArtifactObj artifactObj, String repoURL) {
        if (repoURL.contains("thirdparty")) {
            artifactObj.setThirdParty(true);
        }
        return artifactObj;
    }
}
