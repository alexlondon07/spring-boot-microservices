package api.microservices.gatewayserverservice.factory;

import api.microservices.gatewayserverservice.filters.ExampleGlobalFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ExampleGatewayFilterFactory extends AbstractGatewayFilterFactory<ExampleGatewayFilterFactory.ConfigurationFilter> {

    public ExampleGatewayFilterFactory() {
        super(ConfigurationFilter.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("message", "cookieValue", "cookieName");
    }

    @Override
    public String name() {
        return "ExampleCookie";
    }

    private final Logger logger = LoggerFactory.getLogger(ExampleGlobalFilter.class);

    public static class ConfigurationFilter {
        private String message;
        private String cookieValue;
        private String cookieName;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCookieValue() {
            return cookieValue;
        }

        public void setCookieValue(String cookieValue) {
            this.cookieValue = cookieValue;
        }

        public String getCookieName() {
            return cookieName;
        }

        public void setCookieName(String cookieName) {
            this.cookieName = cookieName;
        }
    }

    @Override
    public GatewayFilter apply(ConfigurationFilter config) {
        return  (exchange, chain) -> {
            logger.info("Ejecutando pre gateway filter factory");
            return chain.filter(exchange).then(Mono.fromRunnable( ()->{

                Optional.ofNullable(config.cookieValue).ifPresent( cookie ->{
                    exchange.getResponse().addCookie(ResponseCookie.from(config.cookieName, cookie).build());
                });

                logger.info("Ejecutando pos gateway filter factory " + config.message);
            }));
        };
    }
}
