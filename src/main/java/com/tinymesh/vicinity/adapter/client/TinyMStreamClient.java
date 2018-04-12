package com.tinymesh.vicinity.adapter.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.nio.charset.Charset;
import java.util.Collections;

@Service
public class TinyMStreamClient {
    @Value("${tinymesh.client.email}")
    private String email;

    @Value("${tinymesh.client.pass}")
    private String pass;


    private WebClient webClient;

    public TinyMStreamClient(WebClient webClient){
        this.webClient = webClient;
    }

    public Flux<String> streamMessages(String email, String pass) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        final String endpoint = "/v2/messages/T";

        map.put("date.from", Collections.singletonList("NOW//-5MINUTE"));
        map.put("data.encoding", Collections.singletonList("hex"));
        map.put("continuous", Collections.singletonList("true"));
        map.put("stream", Collections.singletonList("true"));

        UriComponents uri = UriComponentsBuilder.fromUriString(endpoint).queryParams(map).buildAndExpand();

        return webClient.get()
                .uri(uri.toString())
                .header("Authorization", "Basic " + Base64Utils
                        .encodeToString((email + ":" + pass).getBytes(Charset.forName("US-ASCII"))))
                .retrieve()
                .bodyToFlux(String.class);
    }

    public void printStreamedMessages() {
        streamMessages(email,pass).subscribe(System.out :: println, Throwable::printStackTrace);
    }
}