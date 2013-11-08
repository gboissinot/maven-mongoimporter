package com.boissinot.maven.util.mongoimport.service.couchbase;

import com.boissinot.maven.util.mongoimport.domain.couchbase.CouchbaseArtifactDocument;
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
public class CouchbaseService {

    private final MessageChannel inputChannel;
    private final CouchbaseDocumentBuilderService builderService;

    public CouchbaseService(MessageChannel inputChannel, CouchbaseDocumentBuilderService builderService) {
        this.inputChannel = inputChannel;
        this.builderService = builderService;
    }

    public void insert(IndexingContext repoMavenContext) throws Exception {
        final IndexSearcher searcher = repoMavenContext.acquireIndexSearcher();
        final IndexReader ir = searcher.getIndexReader();
        for (int i = 0; i < ir.maxDoc(); i++) {
            if (!ir.isDeleted(i)) {
                final Document doc = ir.document(i);
                String metadata = doc.get("u");
                if (metadata != null) {
                    final ArtifactInfo ai = IndexUtils.constructArtifactInfo(doc, repoMavenContext);
                    final CouchbaseArtifactDocument artifactObj = builderService.buildArtifactObj(ai, i);
                    System.out.println("Inserting in Couchbase ... " + artifactObj);
                    final Message<CouchbaseArtifactDocument> artifactObjMessage =
                            MessageBuilder
                                    .withPayload(artifactObj)
                                    .build();
                    inputChannel.send(artifactObjMessage);
                }
            }
        }
    }
}
