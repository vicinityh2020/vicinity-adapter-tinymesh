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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TinyMStreamClientTest {

    private BufferedReader bufferedReader;
    private String jsonTestObject;

    /**
     * {@link DeviceRepository}
     * {@link TinyMStreamClient}
     */
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private TinyMStreamClient tinyMStreamClient;

    /**
     * Creating bufferReader to read singleMessageJsonTest.json file to test it later
     */
    @Before
    public void setUp() {
        bufferedReader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/singleMessageJsonTest.json")));

        Optional<String> optionalRespBody = bufferedReader.lines().reduce(String::concat);
        optionalRespBody.ifPresent(s -> jsonTestObject = s);
    }

    /**
     * @throws IOException
     * If bufferReader is null, then it is not created
     * If bufferReader is not null, it is open and it needs to be closed!
     */
    @After
    public void teardown() throws IOException {
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        bufferedReader = null;
    }

    /**
     * Testing device
     * Is device State TRUE
     * Is device has different dateTime than actual dateTime
     * Here device data in singleMessageJsonTest.json file being tested!
     */
    @Test
    public void testUpdateDeviceStat() {
        Device dev = deviceRepository.findByTinyMuid(839188480);

        dev.setState(false);
        deviceRepository.save(dev);

        tinyMStreamClient.updateDeviceState(jsonTestObject);

        assertTrue(deviceRepository.findByTinyMuid(839188480).isState());
        assertNotEquals(deviceRepository.findByTinyMuid(839188480).getDateTime().toString(), "2018-04-18T08:47:40.979Z" );
    }
}