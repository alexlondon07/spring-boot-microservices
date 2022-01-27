package api.microservices.oauthservice.services;

import api.microservices.userscommons.models.entity.User;

public interface IUserService {
    public User findByUsername(String username);
    public User update(User user, Long id);
}
