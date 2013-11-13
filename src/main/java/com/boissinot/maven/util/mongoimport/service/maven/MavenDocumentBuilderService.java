package com.boissinot.maven.util.mongoimport.service.maven;

import com.boissinot.maven.util.mongoimport.domain.maven.MavenArtifactDocument;
import org.apache.maven.index.ArtifactInfo;

/**
 * @author Gregory Boissinot
 */
public class MavenDocumentBuilderService {

    public MavenArtifactDocument buildArtifactObj(ArtifactInfo artifactInfo) {
        MavenArtifactDocument mavenArtifactDocument = new MavenArtifactDocument();
        mavenArtifactDocument.setOrganisation(artifactInfo.groupId);
        mavenArtifactDocument.setName(artifactInfo.artifactId);
        mavenArtifactDocument.setVersion(artifactInfo.version);
        final String classifier = artifactInfo.classifier;
        if (classifier == null) {
            mavenArtifactDocument.setType("binary");
        } else {
            mavenArtifactDocument.setType(classifier);
        }
        mavenArtifactDocument.setStatus("RELEASE");
        mavenArtifactDocument.setFileExtension(artifactInfo.fextension);

        return mavenArtifactDocument;
    }
}
