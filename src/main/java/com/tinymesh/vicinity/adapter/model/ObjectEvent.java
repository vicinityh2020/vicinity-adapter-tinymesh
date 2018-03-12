package com.tinymesh.vicinity.adapter.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import javax.validation.Valid;
import javax.validation.constraints.*;

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
    @NotNull
    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }


    @Override
    public boolean equals(java.lang.Object o) {
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
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}