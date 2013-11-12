package com.boissinot.maven.util.mongoimport.launch;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;

import java.io.File;

/**
 * @author Gregory Boissinot
 */
public class Dumper {

    public static void main(String[] args) throws Exception {
        //File dir = new File("/Users/gregory/Dev/maven-mongoimporter/target/1901187944/central-index");
        File dir = new File("/Users/gregory/Downloads/nexus-maven-repository-index");
        final FSDirectory fsDirectory = FSDirectory.open(dir);
        final IndexReader reader = IndexReader.open(fsDirectory);
        IndexSearcher searcher = new IndexSearcher(reader);
        for (int i = 0; i < searcher.maxDoc(); i++) {
            Document doc = searcher.doc(i);
            String metadata = doc.get("u");
            if (metadata != null) {
                System.out.println(metadata);
            }
        }
    }
}
