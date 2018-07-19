package com.tinymesh.vicinity.adapter.model;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import sun.awt.image.ImageWatched;

import javax.validation.Valid;
import javax.validation.constraints.*;

@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.NONE)
public class ObjectProperty   {
    @JsonProperty("pid")
    private String pid = null;

    @JsonProperty("monitors")
    private String monitors = null;

    @JsonProperty("output")
    private OutputSchema output = null;
//
    @JsonProperty("writable")
    private Boolean writable = null;

    @JsonProperty("read_link")
    @Valid
    private LinkInfo readLink = null;

//    @JsonProperty("write_link")
//    @Valid
//    private LinkInfo writeLinks = null;

    public ObjectProperty pid(String pid) {
        this.pid = pid;
        return this;
    }

    /**
     * Get pid
     * @return pid
     **/
    @NotNull


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public ObjectProperty monitors(String monitors) {
        this.monitors = monitors;
        return this;
    }

    /**
     * Instance of property from VICINITY core ontology, see http://iot.linkeddata.es/def/core/index-en.html, figure 3 hierarchy of properties and exemplary instances, with \"core:\" prefix removed
     * @return monitors
     **/
    @NotNull
    public String getMonitors() {
        return monitors;
    }

    public void setMonitors(String monitors) {
        this.monitors = monitors;
    }

    public ObjectProperty output(OutputSchema output) {
//        this.output = output;
        return this;
    }

    /**
     * Get output
     * @return output
     **/
//    @NotNull
//    @Valid
    public OutputSchema getOutput() {
        return output;
    }

    public void setOutput(OutputSchema output) {
        this.output = output;
    }

//    public ObjectProperty writable(Boolean writable) {
//        this.writable = writable;
//        return this;
//    }

    /**
     * Get writable
     * @return writable
//     **/
    public Boolean isWritable() {
        return writable;
    }

    public void setWritable(Boolean writable) {
        this.writable = writable;
    }

    public ObjectProperty readLinks(LinkInfo readLinks) {
        this.readLink = readLinks;
        return this;
    }

    public ObjectProperty addReadLinksItem(LinkInfo readLinksItem) {
        if (this.readLink == null) {
            this.readLink = new LinkInfo();
            this.readLink.output = new EmptyPOJO();
        }
        this.readLink = readLinksItem;
        this.readLink.output = new EmptyPOJO();
        return this;
    }

    /**
     * must be presented for properties to be read
     * @return readLink
     **/
    @Valid
    public LinkInfo getReadLink() {
        return readLink;
    }

    public void setReadLink(LinkInfo readLink) {
        this.readLink = readLink;
    }

//    public ObjectProperty writeLinks(LinkInfo writeLinks) {
//        this.writeLinks = writeLinks;
//        return this;
//    }

//    public ObjectProperty addWriteLinksItem(LinkInfo writeLinksItem) {
//        if (this.writeLinks == null) {
//            this.writeLinks = new ArrayList<LinkInfo>();
//        }
//        this.writeLinks.add(writeLinksItem);
//        return this;
//    }

    /**
     * must be presented for properties to be set
     * @return writeLinks
     **/
//    public List<LinkInfo> getWriteLinks() {
//        return writeLinks;
//    }
//
//    public void setWriteLinks(LinkInfo writeLinks) {
//        this.writeLinks = writeLinks;
//    }
//

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObjectProperty objectProperty = (ObjectProperty) o;
        return Objects.equals(this.pid, objectProperty.pid) &&
                Objects.equals(this.monitors, objectProperty.monitors) &&
//                Objects.equals(this.output, objectProperty.output) &&
//                Objects.equals(this.writable, objectProperty.writable) &&
                Objects.equals(this.readLink, objectProperty.readLink);
//                Objects.equals(this.writeLinks, objectProperty.writeLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, monitors,  readLink );/*output, writable,writeLinks*/
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ObjectProperty {\n");

        sb.append("    pid: ").append(toIndentedString(pid)).append("\n");
        sb.append("    monitors: ").append(toIndentedString(monitors)).append("\n");
//        sb.append("    output: ").append(toIndentedString(output)).append("\n");
//        sb.append("    writable: ").append(toIndentedString(writable)).append("\n");
        sb.append("    readLink: ").append(toIndentedString(readLink)).append("\n");
//        sb.append("    writeLinks: ").append(toIndentedString(writeLinks)).append("\n");
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

