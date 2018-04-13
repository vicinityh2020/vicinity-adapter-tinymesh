package com.tinymesh.vicinity.adapter.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

/**
 * ExecActionPayload
 */
public class ExecActionPayload   {
    @JsonProperty("input")
    @Valid
    private List<Pair> input = null;

    public ExecActionPayload input(List<Pair> input) {
        this.input = input;
        return this;
    }

    public ExecActionPayload addInputItem(Pair inputItem) {
        if (this.input == null) {
            this.input = new ArrayList<Pair>();
        }
        this.input.add(inputItem);
        return this;
    }

    /**
     * Get input
     * @return input
     **/
    @Valid

    public List<Pair> getInput() {
        return input;
    }

    public void setInput(List<Pair> input) {
        this.input = input;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExecActionPayload execActionPayload = (ExecActionPayload) o;
        return Objects.equals(this.input, execActionPayload.input);
    }

    @Override
    public int hashCode() {
        return Objects.hash(input);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExecActionPayload {\n");

        sb.append("    input: ").append(toIndentedString(input)).append("\n");
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

