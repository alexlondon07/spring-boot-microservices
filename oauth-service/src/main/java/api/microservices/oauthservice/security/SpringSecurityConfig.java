package api.microservices.oauthservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationEventPublisher authenticationEventPublisher;

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private AppConfig config;

    @Override
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(this.userService).passwordEncoder(config.bCryptPasswordEncoder())
               .and().authenticationEventPublisher(authenticationEventPublisher);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
       return super.authenticationManager();
    }
}
