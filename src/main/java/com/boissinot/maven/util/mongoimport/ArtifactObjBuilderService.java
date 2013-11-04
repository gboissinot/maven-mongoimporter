package com.boissinot.maven.util.mongoimport;

import org.apache.maven.index.ArtifactInfo;

import java.util.Date;

/**
 * @author Gregory Boissinot
 */
public class ArtifactObjBuilderService {

    public ArtifactObj buildArtifactObj(ArtifactInfo artifactInfo) {
        ArtifactObj artifactObj = new ArtifactObj();
        artifactObj.setOrganisation(artifactInfo.groupId);
        artifactObj.setName(artifactInfo.artifactId);
        artifactObj.setVersion(artifactInfo.version);
        artifactObj.setSha1(artifactInfo.sha1);
        artifactObj.setMd5(artifactInfo.md5);
        final String classifier = artifactInfo.classifier;
        if (classifier == null) {
            artifactObj.setType("binary");
        } else {
            artifactObj.setType(classifier);
        }
        artifactObj.setDescription(artifactInfo.description);
        artifactObj.setCreationDate(new Date(artifactInfo.lastModified));
        artifactObj.setPublicationDate(new Date());
        artifactObj.setFileSize(artifactInfo.size);
        artifactObj.setFileExtension(artifactInfo.fextension);
        artifactObj.setStatus("RELEASE");

        return artifactObj;
    }
}
