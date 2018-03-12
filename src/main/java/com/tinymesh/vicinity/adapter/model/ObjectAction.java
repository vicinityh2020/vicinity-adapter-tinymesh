package com.tinymesh.vicinity.adapter.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.tinymesh.vicinity.adapter.model.InputSchema;
import com.tinymesh.vicinity.adapter.model.LinkInfo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ObjectAction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-12T13:15:43.539Z")

public class ObjectAction   {
  @JsonProperty("aid")
  private String aid = null;

  @JsonProperty("affects")
  private String affects = null;

  @JsonProperty("read_links")
  @Valid
  private List<LinkInfo> readLinks = null;

  @JsonProperty("write_links")
  @Valid
  private List<LinkInfo> writeLinks = null;

  @JsonProperty("input")
  private InputSchema input = null;

  public ObjectAction aid(String aid) {
    this.aid = aid;
    return this;
  }

   /**
   * Get aid
   * @return aid
  **/
  @ApiModelProperty(example = "switch", required = true, value = "")
  @NotNull


  public String getAid() {
    return aid;
  }

  public void setAid(String aid) {
    this.aid = aid;
  }

  public ObjectAction affects(String affects) {
    this.affects = affects;
    return this;
  }

   /**
   * Instance of property from VICINITY core ontology, see http://iot.linkeddata.es/def/core/index-en.html, figure 3 hierarchy of properties and exemplary instances, with \"core:\" prefix removed
   * @return affects
  **/
  @ApiModelProperty(example = "OnOffStatus", required = true, value = "Instance of property from VICINITY core ontology, see http://iot.linkeddata.es/def/core/index-en.html, figure 3 hierarchy of properties and exemplary instances, with \"core:\" prefix removed")
  @NotNull


  public String getAffects() {
    return affects;
  }

  public void setAffects(String affects) {
    this.affects = affects;
  }

  public ObjectAction readLinks(List<LinkInfo> readLinks) {
    this.readLinks = readLinks;
    return this;
  }

  public ObjectAction addReadLinksItem(LinkInfo readLinksItem) {
    if (this.readLinks == null) {
      this.readLinks = new ArrayList<LinkInfo>();
    }
    this.readLinks.add(readLinksItem);
    return this;
  }

   /**
   * must be presented for actions to be read (e.g. action status)
   * @return readLinks
  **/
  @ApiModelProperty(value = "must be presented for actions to be read (e.g. action status)")

  @Valid

  public List<LinkInfo> getReadLinks() {
    return readLinks;
  }

  public void setReadLinks(List<LinkInfo> readLinks) {
    this.readLinks = readLinks;
  }

  public ObjectAction writeLinks(List<LinkInfo> writeLinks) {
    this.writeLinks = writeLinks;
    return this;
  }

  public ObjectAction addWriteLinksItem(LinkInfo writeLinksItem) {
    if (this.writeLinks == null) {
      this.writeLinks = new ArrayList<LinkInfo>();
    }
    this.writeLinks.add(writeLinksItem);
    return this;
  }

   /**
   * must be presented for action to be executed
   * @return writeLinks
  **/
  @ApiModelProperty(value = "must be presented for action to be executed")

  @Valid

  public List<LinkInfo> getWriteLinks() {
    return writeLinks;
  }

  public void setWriteLinks(List<LinkInfo> writeLinks) {
    this.writeLinks = writeLinks;
  }

  public ObjectAction input(InputSchema input) {
    this.input = input;
    return this;
  }

   /**
   * Get input
   * @return input
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public InputSchema getInput() {
    return input;
  }

  public void setInput(InputSchema input) {
    this.input = input;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjectAction objectAction = (ObjectAction) o;
    return Objects.equals(this.aid, objectAction.aid) &&
        Objects.equals(this.affects, objectAction.affects) &&
        Objects.equals(this.readLinks, objectAction.readLinks) &&
        Objects.equals(this.writeLinks, objectAction.writeLinks) &&
        Objects.equals(this.input, objectAction.input);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aid, affects, readLinks, writeLinks, input);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjectAction {\n");
    
    sb.append("    aid: ").append(toIndentedString(aid)).append("\n");
    sb.append("    affects: ").append(toIndentedString(affects)).append("\n");
    sb.append("    readLinks: ").append(toIndentedString(readLinks)).append("\n");
    sb.append("    writeLinks: ").append(toIndentedString(writeLinks)).append("\n");
    sb.append("    input: ").append(toIndentedString(input)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

