package com.boissinot.maven.util.mongoimport.service.mongodb.integration;

import com.boissinot.maven.util.mongoimport.domain.maven.MavenCNameElement;
import com.boissinot.maven.util.mongoimport.domain.mongodb.MongoDBArtifactDocument;
import com.boissinot.maven.util.mongoimport.domain.mongodb.MongoDBArtifactDocumentForC;
import com.boissinot.maven.util.mongoimport.service.maven.artifact.c.ArtifactNameExtractor;
import org.springframework.integration.annotation.Transformer;

/**
 * @author Gregory Boissinot
 */
public class CLanguageArtifactTransformer {

    private ArtifactNameExtractor artifactNameExtractor;

    public CLanguageArtifactTransformer(ArtifactNameExtractor artifactNameExtractor) {
        this.artifactNameExtractor = artifactNameExtractor;
    }

    @Transformer
    @SuppressWarnings("unused")
    public MongoDBArtifactDocument addCMetadata(MongoDBArtifactDocument artifactObj, String repoURL) {

        if (repoURL.contains("native")) {
            String artifactOriginalName = artifactObj.getName();
            if (!artifactOriginalName.startsWith("super-")) {
                final MavenCNameElement cNameElement = artifactNameExtractor.extractMetadataFromName(artifactOriginalName);
                if (cNameElement != null) {
                    MongoDBArtifactDocumentForC artifactDocumentForC
                            = new MongoDBArtifactDocumentForC(
                            cNameElement.getArchi(),
                            cNameElement.getPlatform(),
                            cNameElement.getCompiler(),
                            cNameElement.getTypeDep(),
                            cNameElement.getMod());
                    artifactObj.setMetadataCLanguage(artifactDocumentForC);
                    artifactObj.setName(cNameElement.getName());
                }
            }
        }

        return artifactObj;
    }

}