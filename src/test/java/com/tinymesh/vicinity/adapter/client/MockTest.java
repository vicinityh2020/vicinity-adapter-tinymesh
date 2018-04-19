package com.tinymesh.vicinity.adapter.client;

import com.tinymesh.vicinity.adapter.entity.Device;
import com.tinymesh.vicinity.adapter.repository.DeviceRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockTest {
    private BufferedReader in;
    private String fakeObject;

    @Autowired
    private DeviceRepository deviceRepo;

    @Autowired
    private TinyMStreamClient tinyMStreamClient;

    @Before
    public void setUp() throws Exception {
        in = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/singleFakeEvent.json")));

        Optional<String> optionalRespBody = in.lines().reduce(String::concat);
        optionalRespBody.ifPresent(s -> fakeObject = s);
    }

    @After
    public void teardown() throws IOException {
        if (in != null) {
            in.close();
        }
        in = null;
    }
    //2018-04-18T08:47:40.979Z

    @Test
    public void testUpdateDeviceStat() throws Exception {
        Device dev = deviceRepo.findByTinyMuid(839188480);
        dev.setState(false);
        deviceRepo.save(dev);
        tinyMStreamClient.updateDeviceState(fakeObject);
        assertTrue(deviceRepo.findByTinyMuid(839188480).isState());


    }
}