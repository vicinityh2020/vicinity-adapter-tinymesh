package com.tinymesh.vicinity.adapter.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TinyMStreamClientTest {


    private WebClient webClient;

    @Autowired
    private TinyMStreamClient tinyMStreamClient;

    @Value("${tinymesh.client.email}")
    private String email;

    @Value("${tinymesh.client.pass}")
    private String pass;

    @Value("${tinymesh.client.base_url}")
    private String baseUrl;

    @Value("${tinymesh.client.stream_messages_uri}")
    private String streamMessagesUri;

    private MockWebServer server;



    @Before
    public void setup() {
        this.server = new MockWebServer();
        this.webClient = WebClient.create(this.server.url("https://http.cloud.tiny-mesh.com").toString());
    }

    @After
    public void shutdown() throws Exception {
        this.server.shutdown();
    }


    @Test
    public void messageHeaders() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = "type";
        prepareResponse(response -> response
                .setHeader("Content-Type", "application/json"));

        Flux<String> result = tinyMStreamClient.streamMessages(email,pass);

/*        StepVerifier.create(result)
                .expectSubscription()
                .expectComplete();*/

        StepVerifier.create(result)
                .expectSubscription()
                .thenRequest(3)
                .thenAwait(Duration.ofSeconds(3))
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
