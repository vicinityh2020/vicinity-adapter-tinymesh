package com.tinymesh.vicinity.adapter.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;

public class OutputSchema   {
    @JsonProperty("units")
    private String units = null;

    @JsonProperty("datatype")
    private String datatype = null;

    public OutputSchema units(String units) {
        this.units = units;
        return this;
    }

    /**
     * Get units
     * @return units
     **/
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public OutputSchema datatype(String datatype) {
        this.datatype = datatype;
        return this;
    }

    /**
     * Get datatype
     * @return datatype
     **/
    @NotNull
    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OutputSchema outputSchema = (OutputSchema) o;
        return Objects.equals(this.units, outputSchema.units) &&
                Objects.equals(this.datatype, outputSchema.datatype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(units, datatype);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OutputSchema {\n");

        sb.append("    units: ").append(toIndentedString(units)).append("\n");
        sb.append("    datatype: ").append(toIndentedString(datatype)).append("\n");
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

