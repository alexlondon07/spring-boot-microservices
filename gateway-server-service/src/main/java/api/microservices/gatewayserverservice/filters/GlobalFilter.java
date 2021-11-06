package api.microservices.gatewayserverservice.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalFilter  implements org.springframework.cloud.gateway.filter.GlobalFilter {
    private final Logger logger = LoggerFactory.getLogger(GlobalFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Ejecutando Filtro Pre");
        return chain.filter(exchange).then(Mono.fromRunnable( ()->{
            logger.info("Ejecutando filtro post");
            exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "red").build());
            exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        }));
    }
}
