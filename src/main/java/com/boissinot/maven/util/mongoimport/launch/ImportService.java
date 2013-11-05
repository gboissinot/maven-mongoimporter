package com.boissinot.maven.util.mongoimport.launch;

import com.boissinot.maven.util.mongoimport.domain.ArtifactObj;
import com.boissinot.maven.util.mongoimport.service.ArtifactObjBuilderService;
import com.boissinot.maven.util.mongoimport.service.MavenIndexerRetriever;
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

    private final MessageChannel inputChannel;
    private final ArtifactObjBuilderService builderService;
    private final MavenIndexerRetriever mavenIndexerRetriever;
    private final String[] repoURLs;

    public ImportService(MessageChannel inputChannel, ArtifactObjBuilderService builderService, MavenIndexerRetriever mavenIndexerRetriever, String[] repoURLs) {
        this.inputChannel = inputChannel;
        this.builderService = builderService;
        this.mavenIndexerRetriever = mavenIndexerRetriever;
        this.repoURLs = repoURLs;
    }

    public void importArtifacts() throws Exception {
        for (String repoURL : repoURLs) {
            importArtifacts(repoURL);
        }
    }

    private void importArtifacts(String repoURL) throws Exception {
        IndexingContext repoMavenContext = mavenIndexerRetriever.getMavenIndex(repoURL);
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
                    System.out.println("Inserting..." + artifactObj);
                    final Message<ArtifactObj> artifactObjMessage = MessageBuilder.withPayload(artifactObj).build();
                    inputChannel.send(artifactObjMessage);
                }
            }
        }
    }

}
