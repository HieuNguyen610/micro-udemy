package hieu.gatewayservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class FirstPreLastPostGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String queryParam = exchange.getRequest().getQueryParams().getFirst("locale");
        ServerWebExchange modifiedExchange = exchange.mutate()
                .request(originRequest -> originRequest.headers(httpHeaders -> httpHeaders.remove(HttpHeaders.ORIGIN)))
                .build();

        log.info("First Pre Global Filter: " + exchange.getRequest().getPath());

        return chain.filter(modifiedExchange)
                .then(Mono.fromRunnable(()-> log.info("Last Post Global Filter: " + modifiedExchange.getRequest().getPath())));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
