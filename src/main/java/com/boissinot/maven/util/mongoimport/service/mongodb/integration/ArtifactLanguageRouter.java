package com.boissinot.maven.util.mongoimport.service.mongodb.integration;

import org.springframework.integration.annotation.Router;

/**
 * @author Gregory Boissinot
 */
public class ArtifactLanguageRouter {

    @Router
    public String nextRoute(String repoUrl) {

        if (repoUrl.contains("java") ) {
            return "java";
        }

        if (repoUrl.contains("native")) {
            return "c";
        }

        if (repoUrl.contains("releases")){
            return  "java";
        }

        return "unknown";
    }
}
