package com.tinymesh.vicinity.adapter.client;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.reactive.function.client.WebClient;

@RunWith(MockitoJUnitRunner.class)
public class StreamTinyMCloudTest {

    @Mock
    private WebClient webClient;

    @InjectMocks
    private StreamTinyMCloud streamTinyMCloud;

    @Before
    public void setUp()  {
    }

    @After
    public void teardown() {
    }

    @Test
    public void requestMessages() {
    }
}
