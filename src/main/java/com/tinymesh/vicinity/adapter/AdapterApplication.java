package com.tinymesh.vicinity.adapter;

import org.mockserver.integration.ClientAndServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

@SpringBootApplication
public class AdapterApplication {


	public static void main(String[] args) {
		getDoorsFromAPI();
		SpringApplication.run(AdapterApplication.class, args);

	}


	private static void getDoorsFromAPI(){
		new ClientAndServer(1080)
				.when(request()
						.withMethod("GET")
						.withPath("/headers"))
				.respond(response()
						.withStatusCode(200)
						.withBody(json(
								"{\n" +
										"  \"key\" : \"gidKaYtHvUVOo\",\n" +
										"  \"location\" : [],                \n" +
										"  \"proto/tm\" : {                  \n" +
										"    \"firmware\" : \"2.01\",\n" +
										"    \"hardware\" : \"2.00\"\n" +
										"  },\n" +
										"  \"network\" : \"T\",                \n" +
										"  \"type\" : \"building-sensor-v3\", \n" +
										"  \"address\" : 1074135040,      \n" +
										"  \"name\" : \"Test B - 119 sek CO2, 10 min intervall\",\n" +
										"  \"provisioned\" : \"active\",   \n" +
										"  \"meta\" : {\n" +
										"    \"updated\" : \"2018-03-19T09:53:50.760Z\",\n" +
										"    \"event/date\" : \"2017-06-23T11:20:52.446Z\",  \n" +
										"    \"created\" : \"2017-03-17T15:17:58.369Z\",     \n" +
										"    \"event/key\" : \"1eq-6jok-djhg-511-VC9naWRLYVl0SHZVVk9v\" \n" +
										"  }\n" +
										"}"))
				);
	}
}
