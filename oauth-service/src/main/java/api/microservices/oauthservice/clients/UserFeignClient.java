package api.microservices.oauthservice.clients;


import api.microservices.userscommons.models.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("users-service")
public interface UserFeignClient {

    @GetMapping("/users/search/search-username")
    public User findByUsername(@RequestParam String username);

    @PutMapping("/users/{id}")
    public User update(@RequestBody User user, @PathVariable Long id);
}
