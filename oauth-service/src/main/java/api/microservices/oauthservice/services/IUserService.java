package api.microservices.oauthservice.services;

import api.microservices.userscommons.models.entity.User;

public interface IUserService {
    User findByUsername(String username);
    User update(User user, Long id);
}
