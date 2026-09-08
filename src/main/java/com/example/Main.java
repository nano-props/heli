package com.example;

import io.helidon.webserver.WebServer;
import io.helidon.service.registry.GlobalServiceRegistry;
import io.helidon.service.registry.ServiceRegistryConfig;
import io.helidon.service.registry.ServiceRegistryManager;

/** Starts a minimal Helidon SE web server. */
public final class Main {
    static {
        var registryConfig = ServiceRegistryConfig.builder()
                .discoverServices(false)
                .discoverServicesFromServiceLoader(false)
                .build();
        GlobalServiceRegistry.registry(ServiceRegistryManager.create(registryConfig).registry());
    }

    private Main() {
    }

    public static void main(String[] args) {
        var port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        var server = startServer(port);
        System.out.printf("Helidon SE is listening on http://localhost:%d/%n", server.port());
    }

    static WebServer startServer(int port) {
        // Keep Helidon's service-provider interface reachable in GraalVM Native Image.
        var serverFeatureProviderType = io.helidon.webserver.spi.ServerFeatureProvider.class;
        return WebServer.builder()
                .port(port)
                .routing(routing -> routing.get("/", (request, response) -> response.send("Hello World!")))
                .build()
                .start();
    }
}
