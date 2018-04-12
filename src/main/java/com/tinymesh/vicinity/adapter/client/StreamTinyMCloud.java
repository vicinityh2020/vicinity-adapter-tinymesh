package com.tinymesh.vicinity.adapter.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.nio.charset.Charset;

@Service
public class StreamTinyMCloud {
    @Value("${tinymesh.client.email}")
    private String email;

    @Value("${tinymesh.client.pass}")
    private String pass;


    private WebClient webClient;

    public StreamTinyMCloud(WebClient webClient){
        this.webClient = webClient;
    }

    public Flux<String> streamMessages(String email, String pass) {
        return webClient.get()
                .uri("/v2/messages/T?date.from=NOW//-5MINUTE&data.encoding=hex&continuous=true&stream=true")
                .header("Authorization", "Basic " + Base64Utils
                        .encodeToString((email + ":" + pass).getBytes(Charset.forName("US-ASCII"))))
                .retrieve()
                .bodyToFlux(String.class);
    }

    public void printStreamedMessages() {
        streamMessages(email,pass).subscribe(System.out :: println, Throwable::printStackTrace);
    }
}