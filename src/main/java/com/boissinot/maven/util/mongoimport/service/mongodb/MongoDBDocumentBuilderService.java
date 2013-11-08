package com.boissinot.maven.util.mongoimport.service.mongodb;

import com.boissinot.maven.util.mongoimport.domain.mongodb.MongoDBArtifactDocument;
import org.apache.maven.index.ArtifactInfo;

import java.util.Date;

/**
 * @author Gregory Boissinot
 */
public class MongoDBDocumentBuilderService {

    public MongoDBArtifactDocument buildArtifactObj(ArtifactInfo artifactInfo) {
        MongoDBArtifactDocument mongoDBArtifactObj = new MongoDBArtifactDocument();
        mongoDBArtifactObj.setOrganisation(artifactInfo.groupId);
        mongoDBArtifactObj.setName(artifactInfo.artifactId);
        mongoDBArtifactObj.setVersion(artifactInfo.version);
        mongoDBArtifactObj.setSha1(artifactInfo.sha1);
        mongoDBArtifactObj.setMd5(artifactInfo.md5);
        final String classifier = artifactInfo.classifier;
        if (classifier == null) {
            mongoDBArtifactObj.setType("binary");
        } else {
            mongoDBArtifactObj.setType(classifier);
        }
        mongoDBArtifactObj.setDescription(artifactInfo.description);
        mongoDBArtifactObj.setCreationDate(new Date(artifactInfo.lastModified));
        mongoDBArtifactObj.setPublicationDate(new Date());
        mongoDBArtifactObj.setFileSize(artifactInfo.size);
        mongoDBArtifactObj.setFileExtension(artifactInfo.fextension);
        mongoDBArtifactObj.setStatus("RELEASE");

        return mongoDBArtifactObj;
    }
}
