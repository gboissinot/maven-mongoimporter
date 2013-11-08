package com.boissinot.maven.util.mongoimport.launch;

import com.boissinot.maven.util.mongoimport.service.MavenIndexerRetriever;
import com.boissinot.maven.util.mongoimport.service.couchbase.CouchbaseService;
import com.boissinot.maven.util.mongoimport.service.mongodb.MongoDBService;
import org.apache.maven.index.context.IndexingContext;

/**
 * @author Gregory Boissinot
 */
public class ImportService {

    private final String[] repoURLs;
    private final MavenIndexerRetriever mavenIndexerRetriever;
    private final CouchbaseService couchbaseService;
    private final MongoDBService mongoDBService;

    public ImportService(MavenIndexerRetriever mavenIndexerRetriever, String[] repoURLs, CouchbaseService couchbaseService, MongoDBService mongoDBService) {
        this.mavenIndexerRetriever = mavenIndexerRetriever;
        this.repoURLs = repoURLs;
        this.couchbaseService = couchbaseService;
        this.mongoDBService = mongoDBService;
    }

    public void importArtifacts() throws Exception {
        for (String repoURL : repoURLs) {
            importArtifacts(repoURL);
        }
    }

    private void importArtifacts(String repoURL) throws Exception {
        IndexingContext repoMavenContext = mavenIndexerRetriever.getMavenIndex(repoURL);

        System.out.println("-----------------------");
        System.out.println(" INSERT INTO CACHE WITH COUCHBASE");
        couchbaseService.insert(repoMavenContext);
        System.out.println("-----------------------");
        System.out.println();
        System.out.println();

        System.out.println("-----------------------");
        System.out.println(" PERSIST WITH MONGODB");
        mongoDBService.insert(repoMavenContext, repoURL);
        System.out.println("-----------------------");
    }
}
