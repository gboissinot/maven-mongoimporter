package com.boissinot.maven.util.mongoimport.service.couchbase;

import com.boissinot.maven.util.mongoimport.domain.couchbase.CouchbaseArtifactDocument;
import com.boissinot.maven.util.mongoimport.domain.maven.MavenArtifactDocument;
import com.boissinot.maven.util.mongoimport.service.maven.MavenDocumentBuilderService;
import org.apache.maven.index.ArtifactInfo;

/**
 * @author Gregory Boissinot
 */
public class CouchbaseDocumentBuilderService {

    private MavenDocumentBuilderService mavenDocumentBuilderService;

    public CouchbaseDocumentBuilderService(MavenDocumentBuilderService mavenDocumentBuilderService) {
        this.mavenDocumentBuilderService = mavenDocumentBuilderService;
    }

    public CouchbaseArtifactDocument buildArtifactObj(ArtifactInfo artifactInfo) {
        MavenArtifactDocument mavenArtifactDocument = mavenDocumentBuilderService.buildArtifactObj(artifactInfo);

        CouchbaseArtifactDocument couchbaseArtifactDocument = new CouchbaseArtifactDocument();
        couchbaseArtifactDocument.setId(mavenArtifactDocument.hashCode());
        mavenArtifactDocument.setOrganisation(mavenArtifactDocument.getOrganisation());
        mavenArtifactDocument.setName(mavenArtifactDocument.getName());
        mavenArtifactDocument.setVersion(mavenArtifactDocument.getVersion());
        mavenArtifactDocument.setType(mavenArtifactDocument.getType());
        mavenArtifactDocument.setStatus(mavenArtifactDocument.getStatus());
        mavenArtifactDocument.setFileExtension(mavenArtifactDocument.getFileExtension());

        return couchbaseArtifactDocument;
    }
}
