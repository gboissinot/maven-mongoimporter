package com.boissinot.maven.util.mongoimport.domain.maven;

/**
 * @author Gregory Boissinot
 */
public enum MavenArtifactStatus {

    BUILT("BUILT"),
    DELIVERED_INTEGRATION("DELIVERED_INTEGRATION"),
    DELIVERED_RELEASE("DELIVERED_RELEASE"),
    CLOSED("CLOSED");

    private MavenArtifactStatus(String status) {
        this.status = status;
    }

    private final String status;

    public String getStatus() {
        return status;
    }
}
