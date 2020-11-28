package ru.perfolenta.dviz.dto.showcase;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrgDto {

    @JsonProperty("name") private String name;
    @JsonProperty("comment") private String comment;
    @JsonProperty("id") private String id;
    @JsonProperty("hashtag") private String hashtag;

    public OrgDto() {
    }

    public OrgDto(String name, String comment, String id, String hashtag) {
        this.name = name;
        this.comment = comment;
        this.id = id;
        this.hashtag = hashtag;
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

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
