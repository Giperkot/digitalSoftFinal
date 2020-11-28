package ru.perfolenta.dviz.dto.showcase;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeDto {

    @JsonProperty("accessLevel") private String accessLevel;
    @JsonProperty("name") private String name;
    @JsonProperty("comment") private String comment;
    @JsonProperty("id") private String id;
    @JsonProperty("type") private String type;
    @JsonProperty("hashtag") private String hashtag;

    public AttributeDto() {
    }

    public AttributeDto(String accessLevel, String name, String comment, String id, String type, String hashtag) {
        this.accessLevel = accessLevel;
        this.name = name;
        this.comment = comment;
        this.id = id;
        this.type = type;
        this.hashtag = hashtag;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
