package api.microservices.oauthservice.security.event;

import api.microservices.oauthservice.services.IUserService;
import api.microservices.userscommons.models.entity.User;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler  implements AuthenticationEventPublisher {

    private final Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

    @Autowired
    private IUserService userService;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        if(authentication.getDetails() instanceof WebAuthenticationDetails){
            return;
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String message = "Success Login " + userDetails.getUsername();
        System.out.println(message);
        log.info(message);
        User user =  userService.findByUsername(authentication.getName());
        if (user.getAttempts() == null){
            user.setAttempts(0);
        }
        userService.update(user, user.getId());
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        String message = "Error login: " + exception.getMessage();
        System.out.println(message);
        log.error(message);

        try {
            User user =  userService.findByUsername(authentication.getName());
            if (user.getAttempts() == null){
                user.setAttempts(0);
            }
            log.info("Intento actual es  " , user.getAttempts());
            user.setAttempts(user.getAttempts() + 1);
            if(user.getAttempts() >= 3){
                log.info("El usuario %s es desabilitadoi por maximo intentos " , user.getUsername());
                user.setEnabled(false);
            }
            userService.update(user, user.getId());

        }catch (FeignException e){
            log.error("El usuario  %s no existe en el sistema", authentication.getName());
        }
    }
}
