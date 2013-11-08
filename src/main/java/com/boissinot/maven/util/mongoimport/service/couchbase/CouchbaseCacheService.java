package com.boissinot.maven.util.mongoimport.service.couchbase;

import com.boissinot.maven.util.mongoimport.service.MavenIndexerRetriever;
import com.boissinot.maven.util.mongoimport.service.couchbase.integration.CouchbaseIntegrationService;
import org.apache.maven.index.context.IndexingContext;

/**
 * @author Gregory Boissinot
 */
public class CouchbaseCacheService {

    private final String[] repoURLs;
    private final MavenIndexerRetriever mavenIndexerRetriever;
    private final CouchbaseIntegrationService couchbaseIntegrationService;

    public CouchbaseCacheService(MavenIndexerRetriever mavenIndexerRetriever, String[] repoURLs, CouchbaseIntegrationService couchbaseIntegrationService) {
        this.mavenIndexerRetriever = mavenIndexerRetriever;
        this.repoURLs = repoURLs;
        this.couchbaseIntegrationService = couchbaseIntegrationService;
    }

    public void cacheArtifacts() throws Exception {
        for (String repoURL : repoURLs) {
            cacheArtifacts(repoURL);
        }
    }

    private void cacheArtifacts(String repoURL) throws Exception {
        IndexingContext repoMavenContext = mavenIndexerRetriever.getMavenIndex(repoURL);

        System.out.println("-----------------------");
        System.out.println(" INSERT INTO CACHE WITH COUCHBASE");
        couchbaseIntegrationService.putArtifacts(repoMavenContext);
        System.out.println("-----------------------");
    }
}
