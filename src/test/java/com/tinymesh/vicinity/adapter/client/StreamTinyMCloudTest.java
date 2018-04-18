package com.tinymesh.vicinity.adapter.client;

import com.tinymesh.vicinity.adapter.model.DoorSensorJSON;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StreamTinyMCloudTest {

    List<DoorSensorJSON> expectedBlogPosts;
    @Autowired
    TinyMStreamClient tinyMStreamClient;

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
    }

    /**
     * Testing if there is connection with Tiny Mesh Cloud
     * Testing if status is OK
     * Testing if response is NOT NULL
     */
    @Test
    public void streamMessages() {

/*
        webTestClient.get().uri("/v2/messages/T")
                .header("Authorization", "Basic " + Base64Utils
                .encodeToString((email + ":" + pass).getBytes(Charset.forName("US-ASCII"))))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());
*/

    }

    /**
     * Testing if messages being streamed on declared time. In this case it is in application.properties at resources!
     * returnResult exits the chained API immediately after response status and header assertions
     */
    @Test
    public void streamingResponses() {
/*
        FluxExchangeResult<String> result = webTestClient.get().uri(streamMessagesUri)
                .header("Authorization", "Basic " + Base64Utils
                        .encodeToString((email + ":" + pass).getBytes(Charset.forName("US-ASCII"))))
                .accept(TEXT_EVENT_STREAM)
                .exchange()
                .expectStatus().isOk()
                .returnResult(String.class);
*/
    }

    @Test
    public void messagesStreamTest() throws InterruptedException {
        Flux<String> messages = tinyMStreamClient.streamMessages(email, pass);
        messages.subscribe(System.out::println, System.out::println);

                Thread.sleep(10000);

    }
}
