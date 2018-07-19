package com.tinymesh.vicinity.adapter.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;


// anatotion makes null fields invisible
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.NONE)
public class EmptyPOJO {
}
