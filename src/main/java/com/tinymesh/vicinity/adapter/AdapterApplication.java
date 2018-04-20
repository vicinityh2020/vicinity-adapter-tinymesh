package com.tinymesh.vicinity.adapter;
//
//import org.mockserver.integration.ClientAndServer;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import static org.mockserver.model.HttpRequest.request;
//import static org.mockserver.model.HttpResponse.response;
//import static org.mockserver.model.JsonBody.json;
//
//@SpringBootApplication
//public class AdapterApplication {
//	public static void main(String[] args) {
//		SpringApplication.run(AdapterApplication.class, args);
//	}
//}

import com.tinymesh.vicinity.adapter.entity.Device;
import com.tinymesh.vicinity.adapter.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class AdapterApplication implements CommandLineRunner {

	@Autowired
	private DeviceRepository deviceRepository;

	public static void main(String[] args) {
		SpringApplication.run(AdapterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        Device d = new Device("TestDevice", "Door", UUID.randomUUID(), LocalDateTime.now(), true, "www.test.com", 1);
        Device d2 = new Device("TestDevice2", "Door2", UUID.randomUUID(), LocalDateTime.now(), true, "www.test2.com", 2);
        Device d3 = new Device("TestDevice3", "Door3", UUID.fromString("ea10fb0e-b3da-4fea-8ccd-818123004ca5"), LocalDateTime.now(), null, "www.test3.com", 3);
        List<Device> devList = new ArrayList<>();
        devList.add(d);
        devList.add(d2);
        devList.add(d3);
        deviceRepository.saveAll(devList);
        Device device = deviceRepository.findByTinyMuid(2);
        System.out.println(device);
    }
}