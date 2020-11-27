package ru.perfolenta.dviz.dto;

public class OuDto {
    private String name;
    private String label;
    private String uri;
    private String imgUrl;

    public OuDto() {
    }

    public OuDto(String name, String label, String uri) {
        this.name = name;
        this.label = label;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
