package com.example;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UpstashQueueServiceTest {
    private UpstashQueueService queueService;

    @Mock
    private HttpClient httpClient;

    @Before
   public void setUp() {
        MockitoAnnotations.openMocks(this);
        queueService = new UpstashQueueService(new Credential());
    }

    @Test
   public  void testPush() throws Exception {
        // Given
        String queueName = "testQueue";
        String message = "Testmessage";
        HttpResponse<String> response = mock(HttpResponse.class);
        when(response.statusCode()).thenReturn(200);
        when(response.body()).thenReturn("Response body");

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);

        // When
        queueService.push(queueName, message);
        String msg1 = queueService.pull(queueName);

        // Then
        assertNotNull(msg1);
    }

    @Test
   public void testPull() throws Exception {
        // Given
        String queueName = "testQueue";
        HttpResponse<String> response = mock(HttpResponse.class);
        when(response.statusCode()).thenReturn(200);
        when(response.body()).thenReturn("Test message");

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);

        // When
        String pulledMessage = queueService.pull(queueName);

        // Then
        assertNotNull(pulledMessage);
        
    }
}
