package com.tinymesh.vicinity.adapter.client;

import com.tinymesh.vicinity.adapter.entity.Device;
import com.tinymesh.vicinity.adapter.repository.DeviceRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TinyMStreamClientTest {

    private BufferedReader bufferedReader;
    private String jsonTestObject;

    @Mock
    Map<Long, String> fakeDeviceTypes;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    @InjectMocks
    private TinyMStreamClient tinyMStreamClient;

    /**
     * Creating bufferReader to read singleMessageJsonTest.json file to test it later.
     */
    @Before
    public void setUp() {
        bufferedReader = new BufferedReader(new InputStreamReader(

                getClass().getResourceAsStream("/singleMessageJsonTest.json")));

        Optional<String> optionalRespBody = bufferedReader.lines().reduce(String::concat);
        optionalRespBody.ifPresent(s -> jsonTestObject = s);
        MockitoAnnotations.initMocks(this);
    }

    /**
     * If bufferReader is null, then it is not created.
     * If bufferReader is not null, it is open and it needs to be closed!
     *
     * @throws IOException
     */
    @After
    public void teardown() throws IOException {
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        bufferedReader = null;
    }

    /**
     * Testing device.
     * Is device State TRUE?
     * Is device has different dateTime than actual dateTime?
     * Here device data in singleMessageJsonTest.json file being tested!
     *
     * @see Device
     * @see DeviceRepository
     * @see TinyMStreamClient
     */
    @Test
    public void testUpdateDeviceStat() {
        Device dev = deviceRepository.findByTinyMuid(4);
        Mockito.when(fakeDeviceTypes.get(any())
        ).thenReturn("door-sensor");
        dev.setState(false);
        deviceRepository.save(dev);

        tinyMStreamClient.updateDeviceState(jsonTestObject);

        dev = deviceRepository.findByTinyMuid(4);
        assertTrue(dev.isState());
        assertNotEquals(dev.getDateTime().toString(), "2018-04-18T08:47:40.979Z");
    }

    /**
     * Tests if client will resync with cloud upon getting event from unknown device.
     */
    @Test
    public void testDeviceResync() {
        // delete device so that it appears as unknown
        Mockito.when(fakeDeviceTypes.get(any())
        ).thenReturn("door-sensor");

        // insert client spy to verify that resync happened
        TinyMClient client = (TinyMClient) ReflectionTestUtils.getField(tinyMStreamClient, "tinyMClient");
        TinyMClient spyClient = spy(client);
        ReflectionTestUtils.setField(tinyMStreamClient, "tinyMClient", spyClient);


        tinyMStreamClient.updateDeviceState(jsonTestObject);
        Mockito.verify(spyClient, atLeastOnce()).syncDevices();
    }
}