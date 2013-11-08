package com.boissinot.maven.util.mongoimport.service.couchbase;

import com.boissinot.maven.util.mongoimport.domain.couchbase.CouchbaseArtifactDocument;
import org.apache.maven.index.ArtifactInfo;

/**
 * @author Gregory Boissinot
 */
public class CouchbaseDocumentBuilderService {

    public CouchbaseArtifactDocument buildArtifactObj(ArtifactInfo artifactInfo, int numIndex) {
        CouchbaseArtifactDocument couchbaseArtifactObj = new CouchbaseArtifactDocument();
        couchbaseArtifactObj.setId(numIndex);
        couchbaseArtifactObj.setOrganisation(artifactInfo.groupId);
        couchbaseArtifactObj.setName(artifactInfo.artifactId);
        couchbaseArtifactObj.setVersion(artifactInfo.version);
        final String classifier = artifactInfo.classifier;
        if (classifier == null) {
            couchbaseArtifactObj.setType("binary");
        } else {
            couchbaseArtifactObj.setType(classifier);
        }
        couchbaseArtifactObj.setStatus("RELEASE");
        couchbaseArtifactObj.setFileExtension(artifactInfo.fextension);

        return couchbaseArtifactObj;
    }
}
