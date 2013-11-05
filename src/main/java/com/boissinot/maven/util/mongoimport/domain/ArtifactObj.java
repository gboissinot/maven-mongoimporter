package com.boissinot.maven.util.mongoimport.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author Gregory Boissinot
 */
@Document(collection = "artifacts")
public class ArtifactObj {

    @Required
    @Field("org")
    private String organisation;

    @Required
    private String name;

    private String version;

    @Required
    private String status;

    private String type;

    @Required
    @Field("creatdate")
    private Date creationDate;

    @Required
    @Field("pubdate")
    private Date publicationDate;

    private String sha1;

    private String md5;

    @Field("desc")
    private String description;

    @Field("fextension")
    private String fileExtension;

    @Field("fsize")
    private long fileSize;

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "ArtifactObj{" +
                "organisation='" + organisation + '\'' +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
