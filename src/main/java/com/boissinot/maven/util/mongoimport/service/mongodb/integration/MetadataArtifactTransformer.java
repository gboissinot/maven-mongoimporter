package com.boissinot.maven.util.mongoimport.service.mongodb.integration;

import com.boissinot.maven.util.mongoimport.domain.mongodb.MongoDBArtifactDocument;
import com.couchbase.client.protocol.views.ComplexKey;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.ViewResponse;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.integration.annotation.Transformer;

/**
 * @author Gregory Boissinot
 */
public class MetadataArtifactTransformer {

    private CouchbaseTemplate couchbaseTemplate;

    public MetadataArtifactTransformer(CouchbaseTemplate couchbaseTemplate) {
        this.couchbaseTemplate = couchbaseTemplate;
    }

    @Transformer
    @SuppressWarnings("unused")
    public MongoDBArtifactDocument addMetadataFromCahe(MongoDBArtifactDocument artifactObj) {
        Query query = new Query();

        //query.setKey(ComplexKey.of("1"));
        query.setKey(ComplexKey.of(
                artifactObj.getOrganisation(),
                artifactObj.getName(),
                artifactObj.getVersion(),
                artifactObj.getStatus(),
                "sources"));
        //query.setIncludeDocs(true);
        //query.setDebug(true);

        //System.out.println("QUERY:" + query);

        ViewResponse viewResponse = couchbaseTemplate.queryView("artifact", "ArtifactView", query);
        if (viewResponse.size() != 0) {
            artifactObj.setSourcesExists(true);
        }

        return artifactObj;
    }
}
