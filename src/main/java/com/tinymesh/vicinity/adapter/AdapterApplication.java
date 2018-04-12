package com.tinymesh.vicinity.adapter;

import com.tinymesh.vicinity.adapter.client.TinyMClient;
import com.tinymesh.vicinity.adapter.database.Device;
import com.tinymesh.vicinity.adapter.jsonmodels.DoorSensorJSON;
import org.mockserver.integration.ClientAndServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

@SpringBootApplication
public class AdapterApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdapterApplication.class, args);
	}
}
