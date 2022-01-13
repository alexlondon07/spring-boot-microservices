package api.microservices.usersservice.configuration;


import api.microservices.userscommons.models.entity.User;
import api.microservices.userscommons.models.entity.Rol;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration configuration, CorsRegistry cors){
        configuration.exposeIdsFor(User.class, Rol.class);
    }
}
