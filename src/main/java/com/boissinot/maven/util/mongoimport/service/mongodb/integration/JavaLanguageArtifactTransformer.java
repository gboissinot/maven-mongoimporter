package com.boissinot.maven.util.mongoimport.service.mongodb.integration;

import com.boissinot.maven.util.mongoimport.domain.mongodb.MongoDBArtifactDocument;
import com.boissinot.maven.util.mongoimport.domain.mongodb.MongoDBArtifactDocumentForJava;
import com.couchbase.client.protocol.views.ComplexKey;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.ViewResponse;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.integration.annotation.Transformer;

/**
 * @author Gregory Boissinot
 */
public class JavaLanguageArtifactTransformer {

    private final CouchbaseTemplate couchbaseTemplate;

    public JavaLanguageArtifactTransformer(CouchbaseTemplate couchbaseTemplate) {
        this.couchbaseTemplate = couchbaseTemplate;
    }

    @Transformer
    @SuppressWarnings("unused")
    public MongoDBArtifactDocument addMetadataFromCahe(MongoDBArtifactDocument artifactObj) {

        MongoDBArtifactDocumentForJava documentForJava = new MongoDBArtifactDocumentForJava();

        //queryIsSources
        Query queryIsSources = new Query();
        queryIsSources.setKey(ComplexKey.of(
                artifactObj.getOrganisation(),
                artifactObj.getName(),
                artifactObj.getVersion(),
                artifactObj.getStatus(),
                "sources"));
        ViewResponse viewResponseIsSources = couchbaseTemplate.queryView("artifact", "ArtifactView", queryIsSources);
        if (viewResponseIsSources.size() != 0) {
            documentForJava.setSourcesExists(true);
        }

        //queryIsJavaDocs
        Query queryIsJavaDocs = new Query();
        queryIsJavaDocs.setKey(ComplexKey.of(
                artifactObj.getOrganisation(),
                artifactObj.getName(),
                artifactObj.getVersion(),
                artifactObj.getStatus(),
                "javadoc"));
        ViewResponse viewResponseIsJavadocs = couchbaseTemplate.queryView("artifact", "ArtifactView", queryIsJavaDocs);
        if (viewResponseIsJavadocs.size() != 0) {
            documentForJava.setJavaDocExists(true);
        }

        if (documentForJava.isJavaDocExists() || documentForJava.isSourcesExists()) {
            artifactObj.setMetadataJavaLanguage(documentForJava);
        }

        return artifactObj;
    }
}
