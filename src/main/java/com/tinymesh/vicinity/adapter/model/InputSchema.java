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
 * InputSchema
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-12T13:15:43.539Z")

public class InputSchema   {
  @JsonProperty("units")
  private String units = null;

  @JsonProperty("datatype")
  private String datatype = null;

  public InputSchema units(String units) {
    this.units = units;
    return this;
  }

   /**
   * Get units
   * @return units
  **/
  @ApiModelProperty(example = "Adimensional", value = "")


  public String getUnits() {
    return units;
  }

  public void setUnits(String units) {
    this.units = units;
  }

  public InputSchema datatype(String datatype) {
    this.datatype = datatype;
    return this;
  }

   /**
   * Get datatype
   * @return datatype
  **/
  @ApiModelProperty(example = "boolean", required = true, value = "")
  @NotNull


  public String getDatatype() {
    return datatype;
  }

  public void setDatatype(String datatype) {
    this.datatype = datatype;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InputSchema inputSchema = (InputSchema) o;
    return Objects.equals(this.units, inputSchema.units) &&
        Objects.equals(this.datatype, inputSchema.datatype);
  }

  @Override
  public int hashCode() {
    return Objects.hash(units, datatype);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InputSchema {\n");
    
    sb.append("    units: ").append(toIndentedString(units)).append("\n");
    sb.append("    datatype: ").append(toIndentedString(datatype)).append("\n");
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

