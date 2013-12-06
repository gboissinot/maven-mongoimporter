package com.boissinot.maven.util.mongoimport.domain.mongodb;

/**
 * @author Gregory Boissinot
 */
public class MongoDBArtifactDocumentForJava {

    private boolean isSourcesExists;

    private boolean isJavaDocExists;

    public boolean isSourcesExists() {
        return isSourcesExists;
    }

    public void setSourcesExists(boolean sourcesExists) {
        isSourcesExists = sourcesExists;
    }

    public boolean isJavaDocExists() {
        return isJavaDocExists;
    }

    public void setJavaDocExists(boolean javaDocExists) {
        isJavaDocExists = javaDocExists;
    }
}
