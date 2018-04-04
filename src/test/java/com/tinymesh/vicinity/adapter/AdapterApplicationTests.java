package com.tinymesh.vicinity.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinymesh.vicinity.adapter.controller.ObjectsApiController;
import com.tinymesh.vicinity.adapter.database.DeviceDataHandler;
import com.tinymesh.vicinity.adapter.database.Device;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class AdapterApplicationTests {

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

	//Testing if object has same values as Device
	@Test
    @Repeat(3)
	public void getAllObjects() throws Exception {
		List<Device> deviceList =  new ArrayList<>();
		deviceList.add(new Device("Device1","Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com"));
		DeviceDataHandler deviceDataHandler = DeviceDataHandler.getInstance();
		deviceDataHandler.setData(deviceList);

        mockMvc.perform(get("/objects"))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$", samePropertyValuesAs(ObjectsApiController.mapDeviceDataToObjectInfo(deviceList))))
                .andDo(print());
	}

	//Testing GET from property
	@Test
    public void readProperty() throws Exception{
		checkGetStatusOK();
		checkGetStatusNOT_FOUND();
    }

    //Testing PUT to properties
    //@Test
	public void setProperty() throws Exception{
		checkSetStatusOK();
		checkGetStatusNOT_FOUND();
	}

	//Testing GET from actions
    @Test
    public void readAction() throws Exception{
	    checkGetActionStatusOK();
	    checkGetActionStatusNOT_FOUND();
    }

    //Testing PUT to actions
    @Test
    public void setAction() throws Exception{
	    checkPutActionStatusOK();
	    checkPutActionStatusNOT_FOUND();
    }

	//Checking if status OK when we GET from Object Property
    public void checkGetStatusOK() throws Exception{
		List<Device> deviceList = Stream.of(
				new Device("Device1","Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com"),
				new Device("Device2","Sensor2", UUID.randomUUID(), LocalDateTime.now(), false, "www.test2.com"))
				.collect(Collectors.toList());

		mockMvc.perform(get("/objects/{uuid}/properties/getState", deviceList.get(0).getUuid()))    //).param("uuid", uuid.toString()))
				.andExpect(status().isOk())
				.andDo(print());
	}

	//Checking if Status NOT FOUND when we GET with wrong pid from Object Property
	public void checkGetStatusNOT_FOUND() throws Exception{
		List<Device> deviceList = Stream.of(
				new Device("Device1","Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com"),
				new Device("Device2","Sensor2", UUID.randomUUID(), LocalDateTime.now(), false, "www.test2.com"))
				.collect(Collectors.toList());

		mockMvc.perform(get("/objects/{uuid}/properties/NOT_FOUND", deviceList.get(0).getUuid()))    //).param("uuid", uuid.toString()))
				.andExpect(status().isNotFound())
				.andDo(print());
	}

	//Checking Status is OK, when we PUT to Object Property
	public void checkSetStatusOK() throws Exception {

        Device device = new Device("Device1", "Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(device);

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
        MockHttpServletRequestBuilder request = put("/objects/{uuid}/properties/setState", device.getUuid());

        request.content(json);
        request.accept(MEDIA_TYPE_JSON_UTF8);
        request.contentType(MEDIA_TYPE_JSON_UTF8);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

        //Testing if Status NOT FOUND when we PUT with wrong pid to Object Property
        public void checkSetStatusNOT_FOUND () throws Exception {

            Device device = new Device("Device1", "Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com");

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(device);

            MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
            MockHttpServletRequestBuilder request = put("/objects/{uuid}/properties/NOT_FOUND", device.getUuid());

            request.content(json);
            request.accept(MEDIA_TYPE_JSON_UTF8);
            request.contentType(MEDIA_TYPE_JSON_UTF8);

            mockMvc.perform(request)
                    .andDo(print())
                    .andExpect(status().isNotFound());

    }

    //Checking Status is OK, when we GET from Action
    public void checkGetActionStatusOK () throws Exception {

        Device device = new Device("Device1", "Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(device);

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
        MockHttpServletRequestBuilder request = get("/objects/{uuid}/actions/getAction", device.getUuid());

        request.content(json);
        request.accept(MEDIA_TYPE_JSON_UTF8);
        request.contentType(MEDIA_TYPE_JSON_UTF8);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print());
    }

    //Checking Status is NOT_FOUND, when we GET from Action
    public void checkGetActionStatusNOT_FOUND () throws Exception {

        Device device = new Device("Device1", "Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(device);

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
        MockHttpServletRequestBuilder request = get("/objects/{uuid}/actions/NOT_FOUND", device.getUuid());

        request.content(json);
        request.accept(MEDIA_TYPE_JSON_UTF8);
        request.contentType(MEDIA_TYPE_JSON_UTF8);

        mockMvc.perform(request)
                .andExpect(status().isNotFound())
                .andDo(print());
    }


    //Checking Status is OK, when we PUT to Action
    public void checkPutActionStatusOK() throws Exception {

        Device device = new Device("Device1", "Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(device);

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
        MockHttpServletRequestBuilder request = put("/objects/{uuid}/actions/putAction", device.getUuid());

        request.content(json);
        request.accept(MEDIA_TYPE_JSON_UTF8);
        request.contentType(MEDIA_TYPE_JSON_UTF8);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }

    //Testing if Status NOT FOUND when we PUT with wrong aid to Action
    public void checkPutActionStatusNOT_FOUND() throws Exception {

        Device device = new Device("Device1", "Sensor", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(device);

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
        MockHttpServletRequestBuilder request = put("/objects/{uuid}/actions/NOT_FOUND", device.getUuid());

        request.content(json);
        request.accept(MEDIA_TYPE_JSON_UTF8);
        request.contentType(MEDIA_TYPE_JSON_UTF8);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}