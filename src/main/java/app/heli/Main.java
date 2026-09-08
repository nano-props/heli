package app.heli;

import io.helidon.webserver.WebServer;
import io.helidon.service.registry.GlobalServiceRegistry;
import io.helidon.service.registry.ServiceRegistryConfig;
import io.helidon.service.registry.ServiceRegistryManager;

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
        IO.println("Helidon SE is listening on http://localhost:" + server.port() + "/");
    }

    static WebServer startServer(int port) {
        return WebServer.builder()
                .port(port)
                .routing(Routes::configure)
                .build()
                .start();
    }
}
