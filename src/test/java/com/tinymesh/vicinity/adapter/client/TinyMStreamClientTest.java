package com.tinymesh.vicinity.adapter.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.nio.charset.Charset;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TinyMStreamClientTest {


    private WebClient webClient;

    @InjectMocks
    private TinyMStreamClient tinyMStreamClient;

    private String email = "test@tiny-mesh.com";

    private String pass = "***REMOVED***";

    private MockWebServer server;



    @Before
    public void setup() {
        ReflectionTestUtils.setField(tinyMStreamClient, "baseURL", "https://http.cloud.tiny-mesh.com");
        ReflectionTestUtils.setField(tinyMStreamClient, "pass", "***REMOVED***");
        ReflectionTestUtils.setField(tinyMStreamClient, "email", "test@tiny-mesh.com");
        this.server = new MockWebServer();
        this.webClient = WebClient.create(this.server.url("https://http.cloud.tiny-mesh.com").toString());
    }

    @After
    public void shutdown() throws Exception {
        this.server.shutdown();
    }

    @Test
    public void messageHeaderOnce() throws Exception {
        prepareResponse(response -> response
                .setHeader("Content-Type", "application/json"));

        Mono<String> result = this.webClient.get()
                .uri("/v2/messages/T")
                .header("Authorization", "Basic " + Base64Utils
                        .encodeToString((email + ":" + pass).getBytes(Charset.forName("US-ASCII"))))
                .retrieve()
                .bodyToMono(String.class);

        StepVerifier.create(result)
                .expectSubscription();

/*        expectRequest(request -> {
            assertEquals("application/json", request.getHeader(HttpHeaders.CONTENT_TYPE));*/



    }


    @Test
    public void messageHeaders() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = "type";
        prepareResponse(response -> response
                .setHeader("Content-Type", "application/json"));

        Flux<String> result = this.webClient.get()
        .uri("/v2/messages/T")
                .header("Authorization", "Basic " + Base64Utils
                        .encodeToString((email + ":" + pass).getBytes(Charset.forName("US-ASCII"))))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMapMany(response -> response.bodyToFlux(String.class));

        StepVerifier.create(result)
                .expectSubscription()
                .expectComplete();

/*        expectRequest(request -> {
                assertEquals("application/json", request.getHeader(HttpHeaders.CONTENT_TYPE));
        });*/

    }

    private void prepareResponse(Consumer<MockResponse> consumer) {
        MockResponse response = new MockResponse();
        consumer.accept(response);
        this.server.enqueue(response);
    }

    private void expectRequest(Consumer<RecordedRequest> consumer) throws InterruptedException {
        consumer.accept(this.server.takeRequest());
    }

    private void expectRequestCount(int count) {
        assertEquals(count, this.server.getRequestCount());
    }

}
