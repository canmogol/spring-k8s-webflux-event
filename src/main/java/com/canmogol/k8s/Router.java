package com.canmogol.k8s;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Handles all the routes.
 */
@Configuration
@AllArgsConstructor
@Slf4j
public class Router {

    private final AppConfig config;

    /**
     * Entry point for all requests.
     *
     * @return Router function that handles all the requests.
     */
    @Bean
    public RouterFunction<ServerResponse> all() {
        return route(RequestPredicates.all(), this::handleRequest);
    }

    private Mono<ServerResponse> handleRequest(final ServerRequest request) {
        return ServerResponse.ok().body(Mono.just(config.getKeyValuePairs()), Map.class);
    }
}