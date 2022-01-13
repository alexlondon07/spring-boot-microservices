package api.microservices.usersservice.domains.repository;

import api.microservices.usersservice.domains.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    public User findByUsername(String username);

    @Query("select u from User u where u.username =?1")
    public User findByUsername2(String username);
}

