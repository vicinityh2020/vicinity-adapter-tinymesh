package com.tinymesh.vicinity.adapter.client;

import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensor;
import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensorJSON;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;
import reactor.test.StepVerifier;

import java.nio.charset.Charset;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.junit.Assert.assertThat;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StreamTinyMCloudTest {

    List<DoorSensorJSON> expectedBlogPosts;

    @Autowired
    private WebTestClient webTestClient;


    @Value("${tinymesh.client.email}")
    private String email;

    @Value("${tinymesh.client.pass}")
    private String pass;

    @Value("${tinymesh.client.base_url}")
    private String baseUrl;

    @Value("${tinymesh.client.stream_messages_uri}")
    private String streamMessagesUri;

    @Before
    public void setUp()  {
        webTestClient = WebTestClient.bindToServer().baseUrl(baseUrl).build();
    }

    /**
     * Testing if there is connection with Tiny Mesh Cloud
     * Testing if status is OK
     * Testing if response is NOT NULL
     */
    @Test
    public void streamMessages() {

        webTestClient.get().uri("/v2/messages/T")
                .header("Authorization", "Basic " + Base64Utils
                .encodeToString((email + ":" + pass).getBytes(Charset.forName("US-ASCII"))))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());

    }

    /**
     * Testing if messages being streamed on declared time. In this case it is in application.properties at resources!
     * returnResult exits the chained API immediately after response status and header assertions
     */
    @Test
    public void streamingResponses() {
        FluxExchangeResult<DoorSensorJSON> result = webTestClient.get().uri(streamMessagesUri)
                .header("Authorization", "Basic " + Base64Utils
                        .encodeToString((email + ":" + pass).getBytes(Charset.forName("US-ASCII"))))
                .accept(TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(DoorSensorJSON.class);
    }
}
