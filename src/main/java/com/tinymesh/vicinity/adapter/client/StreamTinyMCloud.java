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

    @Value("${tinymesh.client.stream_messages_uri}")
    private String streamMessagesUri;


    private WebClient webClient;

    public StreamTinyMCloud(WebClient webClient){
        this.webClient = webClient;
    }

    /**
     *  Streaming messages from Tiny Mesh cloud using Webflux.
     *  streamMessagesUri is declared in application.properties at resources.
     *  On streamMessagesUri it is declared how long it should stream data from Tiny Mesh Cloud.
     *
     * @param email
     * @param pass
     * @return
     */
    public Flux<String> streamMessages(String email, String pass) {
        return webClient.get()
                .uri(streamMessagesUri)
                .header("Authorization", "Basic " + Base64Utils
                        .encodeToString((email + ":" + pass).getBytes(Charset.forName("US-ASCII"))))
                .retrieve()
                .bodyToFlux(String.class);
    }

    /**
     * Printing all messages that is streamed.
     */
    public void printStreamedMessages() {
        streamMessages(email,pass).subscribe(System.out :: println, Throwable::printStackTrace);
    }
}