package com.boissinot.maven.util.mongoimport.domain.couchbase;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

/**
 * @author Gregory Boissinot
 */
public class CouchbaseArtifactDocument {

    @Id
    private int id;
    private String organisation;
    private String name;
    private String version;
    private String status;
    private String type;
    @Transient
    private String fileExtension;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    @Override
    public String toString() {
        return "CouchbaseArtifactDocument{" +
                "organisation='" + organisation + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
