package com.idat.restclient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import jakarta.ws.rs.client.Entity;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@SpringBootApplication
public class RestclientApplication {

	private static final String BASE_URL = "http://localhost:8080/person";

	public static void main(String[] args) {
		//SpringApplication.run(RestclientApplication.class, args);

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL);

		// POST request
		String newPersonJson = "{\"name\":\"Edgard\",\"edad\":30}";
		WebTarget postTarget = client.target(BASE_URL);
		Response postResponse = postTarget.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(newPersonJson, MediaType.APPLICATION_JSON));
		handleResponse(postResponse);

		//GET request
		Response getRespons1 = target.request(MediaType.APPLICATION_JSON).get();
		handleResponse(getRespons1);

		// PUT request (update)
		String updatedPersonJson = "{\"name\":\"Edgard\",\"edad\":31}";
		WebTarget putTarget = client.target(BASE_URL + "/Edgard");
		Response putResponse = putTarget.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(updatedPersonJson, MediaType.APPLICATION_JSON));
		handleResponse(putResponse);

		// DELETE request
		WebTarget deleteTarget = client.target(BASE_URL + "/Pedro");
		Response deleteResponse = deleteTarget.request().delete();
		handleResponse(deleteResponse);

		//GET request
		Response getResponse2 = target.request(MediaType.APPLICATION_JSON).get();
		handleResponse(getResponse2);

		client.close();
	}

	private static void handleResponse(Response response) {
		if (response.getStatus() == 200) {
			System.out.println(response.readEntity(String.class));
		} else {
			System.out.println("Error: " + response.getStatus());
		}
		response.close();
	}
}
