package com.tinymesh.vicinity.adapter.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Preliminary testing structure of event - just identified by its id
 */
@ApiModel(description = "Preliminary testing structure of event - just identified by its id")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-12T13:15:43.539Z")

public class ObjectEvent   {
  @JsonProperty("eid")
  private String eid = null;

  public ObjectEvent eid(String eid) {
    this.eid = eid;
    return this;
  }

   /**
   * Get eid
   * @return eid
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getEid() {
    return eid;
  }

  public void setEid(String eid) {
    this.eid = eid;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjectEvent objectEvent = (ObjectEvent) o;
    return Objects.equals(this.eid, objectEvent.eid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjectEvent {\n");
    
    sb.append("    eid: ").append(toIndentedString(eid)).append("\n");
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

