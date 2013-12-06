package com.boissinot.maven.util.mongoimport.service.mongodb;

import com.boissinot.maven.util.mongoimport.domain.maven.MavenArtifactDocument;
import com.boissinot.maven.util.mongoimport.domain.mongodb.MongoDBArtifactDocument;
import com.boissinot.maven.util.mongoimport.service.maven.MavenDocumentBuilderService;
import org.apache.maven.index.ArtifactInfo;

import java.util.Date;

/**
 * @author Gregory Boissinot
 */
public class MongoDBDocumentBuilderService {

    private final MavenDocumentBuilderService mavenDocumentBuilderService;

    public MongoDBDocumentBuilderService(MavenDocumentBuilderService mavenDocumentBuilderService) {
        this.mavenDocumentBuilderService = mavenDocumentBuilderService;
    }

    public MongoDBArtifactDocument buildArtifactObj(ArtifactInfo artifactInfo) {

        MavenArtifactDocument mavenArtifactDocument = mavenDocumentBuilderService.buildArtifactObj(artifactInfo);

        MongoDBArtifactDocument mongoDBArtifactObj = new MongoDBArtifactDocument();
        mongoDBArtifactObj.setOrganisation(mavenArtifactDocument.getOrganisation());
        mongoDBArtifactObj.setName(mavenArtifactDocument.getName());
        mongoDBArtifactObj.setVersion(mavenArtifactDocument.getVersion());
        mongoDBArtifactObj.setSha1(mavenArtifactDocument.getSha1());
        mongoDBArtifactObj.setMd5(mavenArtifactDocument.getMd5());
        mongoDBArtifactObj.setType(mavenArtifactDocument.getType());
        mongoDBArtifactObj.setDescription(mavenArtifactDocument.getDescription());
        mongoDBArtifactObj.setCreationDate(mavenArtifactDocument.getCreationDate());
        mongoDBArtifactObj.setPublicationDate(new Date());
        mongoDBArtifactObj.setFileSize(mavenArtifactDocument.getFileSize());
        mongoDBArtifactObj.setFileExtension(mavenArtifactDocument.getFileExtension());
        mongoDBArtifactObj.setStatus(mavenArtifactDocument.getStatus());
        mongoDBArtifactObj.setForce(false);

        return mongoDBArtifactObj;
    }
}
