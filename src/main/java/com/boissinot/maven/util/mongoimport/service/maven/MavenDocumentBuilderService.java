package com.boissinot.maven.util.mongoimport.service.maven;

import com.boissinot.maven.util.mongoimport.domain.maven.MavenArtifactDocument;
import com.boissinot.maven.util.mongoimport.domain.maven.MavenArtifactType;
import org.apache.maven.index.ArtifactInfo;

import java.util.Date;

/**
 * @author Gregory Boissinot
 */
public class MavenDocumentBuilderService {

    private static final String ARTIFACT_RELEASE_STATUS = "RELEASE";

    public MavenArtifactDocument buildArtifactObj(ArtifactInfo artifactInfo) {
        MavenArtifactDocument mavenArtifactDocument = new MavenArtifactDocument();
        mavenArtifactDocument.setOrganisation(artifactInfo.groupId);
        mavenArtifactDocument.setName(artifactInfo.artifactId);
        mavenArtifactDocument.setVersion(artifactInfo.version);
        final String classifier = artifactInfo.classifier;
        if (classifier == null) {
            mavenArtifactDocument.setType(MavenArtifactType.BINARY.getType());
        } else {
            mavenArtifactDocument.setType(classifier);
        }
        mavenArtifactDocument.setStatus(ARTIFACT_RELEASE_STATUS);
        mavenArtifactDocument.setFileExtension(artifactInfo.fextension);
        mavenArtifactDocument.setFileSize(artifactInfo.size);
        mavenArtifactDocument.setCreationDate(new Date(artifactInfo.lastModified));

        return mavenArtifactDocument;
    }
}
