package com.tinymesh.vicinity.adapter.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.*;
import java.time.Duration;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TinyMStreamClientTest {


    private WebClient webClient;
    private String respBody;

    @Autowired
    private TinyMStreamClient tinyMStreamClient;

    private BufferedReader in;

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
            if (in != null) {
                in.close();
            }
            in = null;
        this.server.shutdown();
        }



    @Test
    public void messageHeaders() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = "type";
        prepareResponse(response -> response
                .setHeader("Content-Type", "application/json"));
        in = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/messageJSONTestData.json")));

        Flux<String> result = tinyMStreamClient.streamMessages(email,pass);

         result.subscribe();


        StepVerifier.create(result)
                .expectSubscription()
                .thenAwait(Duration.ofSeconds(6))
                .thenCancel()
                .verify();

/*


       expectRequest(request -> {
                assertNotNull(result);
                assertEquals("application/json", request.getHeader(HttpHeaders.CONTENT_TYPE));

       });

       */
    }

    @Test
    public void testUpdateDeviceState(){
        in = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/messageJSONTestData.json")));

        Optional<String> optionalRespBody = in.lines().reduce(String::concat);
        optionalRespBody.ifPresent(s -> respBody = s);

        ResponseEntity<String> testEntity = new ResponseEntity<>(respBody, HttpStatus.OK);

        tinyMStreamClient.streamMessages(email,pass).subscribe(deviceProps -> {
            tinyMStreamClient.updateDeviceState(deviceProps);
        });
    }

    @Test
    public void writeMessagesToFile() throws IOException {
        InputStream initialStream = FileUtils.openInputStream
                (new File("/messageJSONTestData.json"));

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
