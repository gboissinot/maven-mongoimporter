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
    @Order(1)
    private String organisation;

    @Required
    @Order(2)
    private String name;

    @Order(3)
    private String version;

    @Required
    @Order(4)
    private String status;

    @Order(5)
    private String type;

    @Field("is3rdParty")
    @Order(6)
    private boolean isThirdParty;

    @Required
    @Field("creatdate")
    @Order(7)
    private Date creationDate;

    @Required
    @Field("pubdate")
    @Order(8)
    private Date publicationDate;

    @Order(9)
    private String sha1;

    @Field("desc")
    @Order(11)
    private String description;

    @Field("fextension")
    @Order(12)
    private String fileExtension;

    @Field("fsize")
    @Order(13)
    private long fileSize;

    @Order(10)
    private String md5;


    public String getOrganisation() {
        return organisation;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getSha1() {
        return sha1;
    }

    public String getMd5() {
        return md5;
    }

    public String getDescription() {
        return description;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public long getFileSize() {
        return fileSize;
    }

    public boolean isThirdParty() {
        return isThirdParty;
    }

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

    public void setThirdParty(boolean thirdParty) {
        isThirdParty = thirdParty;
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
