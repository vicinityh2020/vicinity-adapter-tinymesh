package com.tinymesh.vicinity.adapter.client;

import com.tinymesh.vicinity.adapter.repository.DeviceRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MockTest {
    private BufferedReader in;
    private String respBody;


    @Value("${tinymesh.client.email}")
    private String email;

    @Value("${tinymesh.client.pass}")
    private String pass;

    @Value("${tinymesh.client.base_url}")
    private String baseUrl;

    @Value("${tinymesh.client.stream_messages_uri}")
    private String streamMessagesUri;

    @Mock
    private WebClient webClient;

    private DeviceRepository deviceRepo;

    @InjectMocks
    private TinyMStreamClient tinyMStreamClient;

    @Before
    public void setUp() throws Exception {
        ReflectionTestUtils.setField(tinyMStreamClient, "baseURL", "https://http.cloud.tiny-mesh.com");
        ReflectionTestUtils.setField(tinyMStreamClient, "pass", "***REMOVED***");
        ReflectionTestUtils.setField(tinyMStreamClient, "email", "test@tiny-mesh.com");
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void teardown() throws IOException {
        if (in != null) {
            in.close();
        }
        in = null;
    }

    @Test
    public void testUpdateDeviceStat() throws Exception {

        in = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/messageJSONTestData.json")));

        Optional<String> optionalRespBody = in.lines().reduce(String::concat);
        optionalRespBody.ifPresent(s -> respBody = s);

        ResponseEntity<String> testEntity = new ResponseEntity<>(respBody, HttpStatus.OK);

        webClient = mock(WebClient.class);

        final String testKey = "gifooVvrHGC24";
        Mockito.when(webClient.get()
                .exchange().thenReturn(testEntity));

        Flux<String> listOfObjects = tinyMStreamClient.streamMessages(email,pass);
        assertNotNull(listOfObjects);
        //assertTrue(listOfObjects.size() > 0);
     //   assertEquals(listOfObjects.size(), 17);
    }
}