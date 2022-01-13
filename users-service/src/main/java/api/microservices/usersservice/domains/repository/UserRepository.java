package api.microservices.usersservice.domains.repository;

import api.microservices.usersservice.domains.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @RestResource(path = "search-username")
    public User findByUsername(@Param("username") String username);

    @Query("select u from User u where u.username =?1")
    public User findByUsername2(String username);
}

