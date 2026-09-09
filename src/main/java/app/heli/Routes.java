package app.heli;

import io.helidon.webserver.http.HttpRouting;

final class Routes {
    void configure(HttpRouting.Builder routing) {
        routing.get("/", (request, response) -> response.send("Hello World!"));
    }
}
