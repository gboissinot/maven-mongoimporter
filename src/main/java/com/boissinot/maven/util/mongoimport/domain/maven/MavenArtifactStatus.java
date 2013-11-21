package com.boissinot.maven.util.mongoimport.domain.maven;

/**
 * @author Gregory Boissinot
 */
public enum MavenArtifactStatus {

    BUILT("BUILT"),
    TESTED("TESTED"),
    RELEASED("RELEASED"),
    CLOSED("CLOSED");

    private MavenArtifactStatus(String status) {
        this.status = status;
    }

    private final String status;

    public String getStatus() {
        return status;
    }
}
