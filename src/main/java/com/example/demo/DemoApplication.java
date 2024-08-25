package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.stripPrefix;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * Used for our custom logout handler
     *
     * @param builder
     * @return
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }


    /**
     * For demo's sake, poor man's route config, and setup.  Atrociously hardcoded.
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> getRoute() {
        Map<String, String> routeMap = new HashMap<>();
        routeMap.put("/api/catalog/**", "http://localhost:8082");
        routeMap.put("/api/user-mgmt/**", "http://localhost:8083");

        var router = route();
        for (var kvp : routeMap.entrySet()) {
            router.add(
                    route().route(RequestPredicates.path(kvp.getKey()), http(kvp.getValue()))
                            .filter(stripPrefix(2))
                            .build());
        }
        return router.build();
    }

    private static HandlerFilterFunction<ServerResponse, ServerResponse> erasePathPrefix(String prefix) {
        return (request, next) -> {
            if (prefix == null) return next.handle(request);

            String newPath = request.uri().getPath().replaceAll("^" + prefix, "");
            URI u = UriComponentsBuilder.fromPath(newPath).build().toUri();
            ServerRequest modified = ServerRequest.from(request).uri(u).build();
            return next.handle(modified);
        };
    }

}
