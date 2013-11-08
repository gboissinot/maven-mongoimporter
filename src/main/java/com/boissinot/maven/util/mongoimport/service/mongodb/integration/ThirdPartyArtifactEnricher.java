package com.boissinot.maven.util.mongoimport.service.mongodb.integration;

import com.boissinot.maven.util.mongoimport.domain.mongodb.MongoDBArtifactDocument;
import org.springframework.integration.annotation.Transformer;

/**
 * @author Gregory Boissinot
 */
public class ThirdPartyArtifactEnricher {

    @Transformer
    @SuppressWarnings("unused")
    public MongoDBArtifactDocument enrich(MongoDBArtifactDocument artifactObj, String repoURL) {
        if (repoURL.contains("thirdparty")) {
            artifactObj.setThirdParty(true);
        }
        return artifactObj;
    }
}
