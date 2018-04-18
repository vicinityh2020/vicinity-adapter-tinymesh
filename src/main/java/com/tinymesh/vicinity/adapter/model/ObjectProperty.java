package com.tinymesh.vicinity.adapter.model;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class ObjectProperty   {
    @JsonProperty("pid")
    private String pid = null;

    @JsonProperty("monitors")
    private String monitors = null;

    @JsonProperty("output")
    private OutputSchema output = null;

    @JsonProperty("writable")
    private Boolean writable = null;

    @JsonProperty("read_links")
    @Valid
    private List<LinkInfo> readLinks = null;

    @JsonProperty("write_links")
    @Valid
    private List<LinkInfo> writeLinks = null;

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
        this.output = output;
        return this;
    }

    /**
     * Get output
     * @return output
     **/
    @NotNull
    @Valid
    public OutputSchema getOutput() {
        return output;
    }

    public void setOutput(OutputSchema output) {
        this.output = output;
    }

    public ObjectProperty writable(Boolean writable) {
        this.writable = writable;
        return this;
    }

    /**
     * Get writable
     * @return writable
     **/
    public Boolean isWritable() {
        return writable;
    }

    public void setWritable(Boolean writable) {
        this.writable = writable;
    }

    public ObjectProperty readLinks(List<LinkInfo> readLinks) {
        this.readLinks = readLinks;
        return this;
    }

    public ObjectProperty addReadLinksItem(LinkInfo readLinksItem) {
        if (this.readLinks == null) {
            this.readLinks = new ArrayList<LinkInfo>();
        }
        this.readLinks.add(readLinksItem);
        return this;
    }

    /**
     * must be presented for properties to be read
     * @return readLinks
     **/
    @Valid
    public List<LinkInfo> getReadLinks() {
        return readLinks;
    }

    public void setReadLinks(List<LinkInfo> readLinks) {
        this.readLinks = readLinks;
    }

    public ObjectProperty writeLinks(List<LinkInfo> writeLinks) {
        this.writeLinks = writeLinks;
        return this;
    }

    public ObjectProperty addWriteLinksItem(LinkInfo writeLinksItem) {
        if (this.writeLinks == null) {
            this.writeLinks = new ArrayList<LinkInfo>();
        }
        this.writeLinks.add(writeLinksItem);
        return this;
    }

    /**
     * must be presented for properties to be set
     * @return writeLinks
     **/
    public List<LinkInfo> getWriteLinks() {
        return writeLinks;
    }

    public void setWriteLinks(List<LinkInfo> writeLinks) {
        this.writeLinks = writeLinks;
    }


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
                Objects.equals(this.output, objectProperty.output) &&
                Objects.equals(this.writable, objectProperty.writable) &&
                Objects.equals(this.readLinks, objectProperty.readLinks) &&
                Objects.equals(this.writeLinks, objectProperty.writeLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, monitors, output, writable, readLinks, writeLinks);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ObjectProperty {\n");

        sb.append("    pid: ").append(toIndentedString(pid)).append("\n");
        sb.append("    monitors: ").append(toIndentedString(monitors)).append("\n");
        sb.append("    output: ").append(toIndentedString(output)).append("\n");
        sb.append("    writable: ").append(toIndentedString(writable)).append("\n");
        sb.append("    readLinks: ").append(toIndentedString(readLinks)).append("\n");
        sb.append("    writeLinks: ").append(toIndentedString(writeLinks)).append("\n");
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

