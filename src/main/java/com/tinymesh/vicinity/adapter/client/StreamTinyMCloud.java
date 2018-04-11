package com.tinymesh.vicinity.adapter.client;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ResolvableType;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.nio.charset.Charset;

import static org.springframework.core.ResolvableType.forClassWithGenerics;
import static org.springframework.web.reactive.function.BodyExtractors.toFlux;

@Service
public class StreamTinyMCloud {
    private RestTemplate restTemplate;

    private String email = "test@tiny-mesh.com";
    String pass = "***REMOVED***";
    String credentials = email + ":" + pass;
    byte[] encodedAuthHeaderValue = Base64.encodeBase64(credentials.getBytes(Charset.forName("US-ASCII")));
    String authHeader = "Basic " + new String(encodedAuthHeaderValue);


    public StreamTinyMCloud(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Bean
    public Flux<String> commandLineRunner() {
        return args -> {

            ResolvableType resolvableType = forClassWithGenerics(ServerSentEvent.class, JsonNode.class);

            URI streamdataURI = new URI("https://http.cloud.tiny-mesh.com/v2/messages/T?date.from=NOW//-5MINUTE&data.encoding=hex&continuous=true&stream=true");

            WebClient client = WebClient.create();

            Flux<ServerSentEvent<JsonNode>> events =
                    client.get()
                            .uri(streamdataURI)
                            .header(authHeader)
                            .accept(MediaType.TEXT_EVENT_STREAM)
                            .exchange()
                            .flatMapMany(response -> response.body(toFlux(resolvableType)));

            events.subscribe(System.out :: println, Throwable::printStackTrace);
        };
    }
}


            /*

            Mono<String> events = client.get()
                    .uri(streamdataURI)
                    .header(authHeader)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .then(response -> response.bodyToMono(String.class));

                   */

        /*

        WebClient client = WebClient.create("https://http.cloud.tiny-mesh.com/v2");

        Mono<String> mono = client.get()
                .uri("/messages/T")
                .accept(MediaType.APPLICATION_JSON)
                .exchange(request)
                .

                */





/*
    public void streamTinyM() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authHeader);
        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<String> response = restTemplate.exchange("https://http.cloud.tiny-mesh.com/v2/device/T/", GET, entity, String.class);
    }

    */
