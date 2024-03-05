package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class UpstashQueueService {
	
	private final String baseUrl; // Upstash base URL
    private final String authToken; // Authentication token

    public UpstashQueueService(Credential credential) {
        this.baseUrl = credential.getBaseUrl();
        this.authToken = credential.getAuthToken();
       
       
    }

    public void push(String queueName, String message) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/set/" + queueName + "/" + message))
                .header("Authorization", "Bearer " + authToken)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Pushed message: " + message);
            System.out.println("Response: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String pull(String queueName) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/get/" + queueName))
                .header("Authorization", "Bearer " + authToken)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Pulled message: " + response.body());
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   
}
