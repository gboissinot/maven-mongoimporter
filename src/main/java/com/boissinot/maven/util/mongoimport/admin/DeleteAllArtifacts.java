package com.boissinot.maven.util.mongoimport.admin;

import com.couchbase.client.ClusterManager;
import com.couchbase.client.CouchbaseClient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrateur
 * Date: 07/11/13
 * Time: 13:39
 * To change this template use File | Settings | File Templates.
 */
public class DeleteAllArtifacts {

    public static void main(String[] args) throws Exception {
        List<URI> uris = new ArrayList<URI>();
        uris.add(URI.create("http://localhost:8091/pools"));
        CouchbaseClient client = new CouchbaseClient(uris, "default", "");
        //Boolean response = client.flush().get();
        //System.out.println(response);
        ClusterManager manager = new ClusterManager(uris, "Administrator", "admin123");
//        FlushResponse flushResponse = manager.flushBucket("default");
//        System.out.println(flushResponse);
        manager.deleteBucket("default");

    }
}
