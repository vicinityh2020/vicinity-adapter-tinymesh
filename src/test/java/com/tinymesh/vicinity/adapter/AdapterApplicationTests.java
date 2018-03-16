package com.tinymesh.vicinity.adapter;

import com.tinymesh.vicinity.adapter.api.ObjectsApiController;
import com.tinymesh.vicinity.adapter.database.DeviceDataHandler;
import com.tinymesh.vicinity.adapter.model.Device;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class AdapterApplicationTests {


	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	@Autowired
	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny()
				.orElse(null);

		assertNotNull("the JSON message converter must not be null",
				this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setup() {
	}

	@Test
	public void getAllObjects() throws Exception {
		List<Device> deviceList =  new ArrayList<>();
		deviceList.add(new Device("Device1","Device1", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com"));
		DeviceDataHandler deviceDataHandler = DeviceDataHandler.getInstance();
		deviceDataHandler.setData(deviceList);

        mockMvc.perform(get("/objects"))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$", samePropertyValuesAs(ObjectsApiController.mapDataToObjectInfo(deviceList))))
                .andDo(print());
	}
	@Test
    public void readProperty() throws Exception{
        List<Device> deviceList =  new ArrayList<>();
        deviceList.add(new Device("Device1","Device1", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com"));
        DeviceDataHandler deviceDataHandler = DeviceDataHandler.getInstance();
        deviceDataHandler.setData(deviceList);
        Device device = new Device("Device1","Device1", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com");
        String uuid = device.getUuid().toString();


        mockMvc.perform(get("/objects/{uuid}/properties/NOT_FOUND").param("uuid", uuid))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}