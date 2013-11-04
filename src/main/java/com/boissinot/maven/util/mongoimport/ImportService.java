package com.boissinot.maven.util.mongoimport;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.maven.index.ArtifactInfo;
import org.apache.maven.index.context.IndexUtils;
import org.apache.maven.index.context.IndexingContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

/**
 * @author Gregory Boissinot
 */
public class ImportService {

    private MessageChannel inputChannel;

    private ArtifactObjBuilderService builderService;

    private MavenIndexerRetriever mavenIndexerRetriever;

    public ImportService(MessageChannel inputChannel, ArtifactObjBuilderService builderService, MavenIndexerRetriever mavenIndexerRetriever) {
        this.inputChannel = inputChannel;
        this.builderService = builderService;
        this.mavenIndexerRetriever = mavenIndexerRetriever;
    }

    public void importArtifacts() throws Exception {
        IndexingContext repoMavenContext = mavenIndexerRetriever.getMavenIndex();
        final IndexSearcher searcher = repoMavenContext.acquireIndexSearcher();
        final IndexReader ir = searcher.getIndexReader();
        for (int i = 0; i < ir.maxDoc(); i++) {
            if (!ir.isDeleted(i)) {
                final Document doc = ir.document(i);
                String metadata = doc.get("u");
                if (metadata != null) {
                    final ArtifactInfo ai = IndexUtils.constructArtifactInfo(doc, repoMavenContext);
                    final ArtifactObj artifactObj = builderService.buildArtifactObj(ai);
                    //TODO Use Spring AOP
                    System.out.println("Inserting...");
                    final Message<ArtifactObj> artifactObjMessage = MessageBuilder.withPayload(artifactObj).build();
                    inputChannel.send(artifactObjMessage);
                }
            }
        }
    }

}
