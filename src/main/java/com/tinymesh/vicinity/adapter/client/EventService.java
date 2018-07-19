
package com.tinymesh.vicinity.adapter.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.tinymesh.vicinity.adapter.entity.Device;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@Service
public class EventService {
    private RestTemplate restTemplate;
    private ObjectMapper mapper;

    @Value("${tinymesh.adapter.id}")
    private String adapterID;

    @Value("${tinymesh.agent.base_url}")
    private String agentBaseURL;

    public EventService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.mapper = objectMapper;
    }

    public void publishEvent(Device device){
        String infraID = device.getUuid().toString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("infrastructure-id", infraID);
        headers.set("adapter-id", adapterID);

        Boolean state = device.isState();
        LocalDateTime time = device.getDateTime().withNano(0);
        ObjectNode requestBody = mapper.createObjectNode();
        requestBody.put("value", state);
        requestBody.put("timestamp", time.toString());

        HttpEntity<ObjectNode> requestEntity = new HttpEntity<>(requestBody, headers);
        System.out.println(((JsonNode) requestBody).toString());
        String url = UriComponentsBuilder
                .fromHttpUrl(agentBaseURL)
                .path("/events/door_activity_"+device.getUuid())
                .toUriString();

        ResponseEntity<JsonNode> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, JsonNode.class);
        }catch (RestClientException e){
            System.out.println("Error! couldnt send event");
            return;
        }
        JsonNode respBody = response.getBody();
        System.out.println(respBody.toString());
    }
}
