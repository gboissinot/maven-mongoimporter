package com.boissinot.maven.util.mongoimport.launch;

import com.boissinot.maven.util.mongoimport.service.couchbase.CouchbaseCacheService;
import com.boissinot.maven.util.mongoimport.service.mongodb.MongoImportService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Gregory Boissinot
 */
public class AppMain {

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.getEnvironment().setActiveProfiles("remote");
        applicationContext.register(JavaConfig.class);
        applicationContext.refresh();

        if (args.length == 0) {
            System.out.println("Specify command with 'cache' or 'persist'");
            return;
        }

        String command = args[0];
        if ("cache".equals(command)) {
            CouchbaseCacheService couchbaseCacheService = applicationContext.getBean(CouchbaseCacheService.class);
            couchbaseCacheService.cacheArtifacts();
        } else {
            MongoImportService mongoImportService = applicationContext.getBean(MongoImportService.class);
            mongoImportService.importArtifacts();
        }

    }

}
