package com.cognizant.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOGGER.info("API Gateway Incoming Request URI: {}", exchange.getRequest().getURI());
        LOGGER.info("API Gateway Request Method: {}", exchange.getRequest().getMethod());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            LOGGER.info("API Gateway Response Status Code: {}", exchange.getResponse().getStatusCode());
        }));
    }
}
