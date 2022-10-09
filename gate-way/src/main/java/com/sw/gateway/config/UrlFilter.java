package com.sw.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class UrlFilter implements GlobalFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("cross url filter");
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getURI().getPath();
        logger.info("url {}", url);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
