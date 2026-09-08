package com.example;

import io.helidon.webserver.WebServer;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    @Test
    void returnsHelloWorld() throws Exception {
        var server = Main.startServer(0);
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:" + server.port() + "/"))
                    .timeout(Duration.ofSeconds(5))
                    .GET()
                    .build();

            var response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            assertEquals(200, response.statusCode());
            assertEquals("Hello World!", response.body());
        } finally {
            server.stop();
        }
    }
}
