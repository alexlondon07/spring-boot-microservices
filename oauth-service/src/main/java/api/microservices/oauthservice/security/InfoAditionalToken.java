package api.microservices.oauthservice.security;

import api.microservices.oauthservice.services.IUserService;
import api.microservices.userscommons.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class InfoAditionalToken implements TokenEnhancer {

    @Autowired
    private IUserService usersService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        User user = usersService.findByUsername(authentication.getName());
        Map<String, Object> info = new HashMap<>();
        info.put("aditional_info", "Hello, How are you? ".concat(authentication.getName()));

        info.put("name", user.getName());
        info.put("last_name", user.getLastName());
        info.put("email", user.getEmail());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }
}