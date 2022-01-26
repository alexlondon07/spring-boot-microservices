package api.microservices.gatewayserverservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity httpSecurity){
        return httpSecurity.authorizeExchange()
                .anyExchange().authenticated()
                .and().csrf().disable()
                .build();
    }
}
