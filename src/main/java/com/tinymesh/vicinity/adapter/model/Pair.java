package com.tinymesh.vicinity.adapter.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Pair
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-12T13:15:43.539Z")

public class Pair   {
    @JsonProperty("parameterName")
    private String parameterName = null;

    @JsonProperty("parameterValue")
    private String parameterValue = null;

    public Pair parameterName(String parameterName) {
        this.parameterName = parameterName;
        return this;
    }

    /**
     * Get parameterName
     * @return parameterName
     **/
    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Pair parameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
        return this;
    }

    /**
     * Get parameterValue
     * @return parameterValue
     **/
    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair) o;
        return Objects.equals(this.parameterName, pair.parameterName) &&
                Objects.equals(this.parameterValue, pair.parameterValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameterName, parameterValue);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Pair {\n");

        sb.append("    parameterName: ").append(toIndentedString(parameterName)).append("\n");
        sb.append("    parameterValue: ").append(toIndentedString(parameterValue)).append("\n");
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

