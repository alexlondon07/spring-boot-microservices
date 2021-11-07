package api.microservices.gatewayserverservice.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class ExampleGlobalFilter implements org.springframework.cloud.gateway.filter.GlobalFilter, Ordered {

    private final Logger logger = LoggerFactory.getLogger(ExampleGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Ejecutando Filtro Pre");
        exchange.getRequest().mutate().headers( h-> h.add("token", "12345"));
        return chain.filter(exchange).then(Mono.fromRunnable( ()->{
            logger.info("Ejecutando filtro post");

            Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent( value -> {
                exchange.getResponse().getHeaders().add("token", value);
            });
            exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "red").build());
            // exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        }));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
