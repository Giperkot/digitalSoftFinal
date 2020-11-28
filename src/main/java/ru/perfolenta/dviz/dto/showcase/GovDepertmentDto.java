package ru.perfolenta.dviz.dto.showcase;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GovDepertmentDto {

    @JsonProperty("dwhuri") private String dwhuri;
    @JsonProperty("name") private String name;
    @JsonProperty("comment") private String comment;
    @JsonProperty("id") private String id;
    @JsonProperty("uri") private String uri;
    @JsonProperty("hashtag") private String hashtag;

    public GovDepertmentDto() {
    }

    public GovDepertmentDto(String dwhuri, String name, String comment, String id, String uri, String hashtag) {
        this.dwhuri = dwhuri;
        this.name = name;
        this.comment = comment;
        this.id = id;
        this.uri = uri;
        this.hashtag = hashtag;
    }

    public String getDwhuri() {
        return dwhuri;
    }

    public void setDwhuri(String dwhuri) {
        this.dwhuri = dwhuri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
