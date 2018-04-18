package com.tinymesh.vicinity.adapter.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PropertyValue
 */
public class PropertyValue   {
    @JsonProperty("value")
    private Object value = null;

    @JsonProperty("timestamp")
    private OffsetDateTime timestamp = null;

    public PropertyValue value(Object value) {
        this.value = value;
        return this;
    }

    /**
     * Can be anything: string, number, array, object, etc.
     * @return value
     **/

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public PropertyValue timestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Value timestamp
     * @return timestamp
     **/
    @NotNull
    @Valid
    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PropertyValue propertyValue = (PropertyValue) o;
        return Objects.equals(this.value, propertyValue.value) &&
                Objects.equals(this.timestamp, propertyValue.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, timestamp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PropertyValue {\n");

        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
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

