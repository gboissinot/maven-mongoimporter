package com.boissinot.maven.util.mongoimport.domain.maven;

/**
 * @author Gregory Boissinot
 */
public enum MavenArtifactType {

    BINARY("binary"),
    SOURCES("sources"),
    JAVADOC("javadoc");

    private String type;

    MavenArtifactType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
