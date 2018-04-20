package com.tinymesh.vicinity.adapter.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class LinkInfo   {
    @JsonProperty("href")
    private String href = null;

    @JsonProperty("mediaType")
    private String mediaType = null;

    public LinkInfo href(String href) {
        this.href = href;
        return this;
    }

    /**
     * relative context path starting with / to specific adapter endpoint, where service is available, usually /objects/{oid}/properties/{pid} or /objects/{oid}/actions/{aid}, u can use any link if needed, you can use variables {oid}, {pid}, {aid}, they are replaced automatically according to object/action/property description
     * @return href
     **/
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public LinkInfo mediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    /**
     * Represent the label used to identify the content type
     * @return mediaType
     **/
    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LinkInfo linkInfo = (LinkInfo) o;
        return Objects.equals(this.href, linkInfo.href) &&
                Objects.equals(this.mediaType, linkInfo.mediaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(href, mediaType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LinkInfo {\n");

        sb.append("    href: ").append(toIndentedString(href)).append("\n");
        sb.append("    mediaType: ").append(toIndentedString(mediaType)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

