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

import java.net.InetSocketAddress;


@Component
public class IpFilter implements GlobalFilter, Ordered {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("cross ip filter");
        ServerHttpRequest request = exchange.getRequest();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        logger.info("ip {}", remoteAddress != null ? remoteAddress.getHostName() : null);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
