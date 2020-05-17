package ir.hamyiar.hamyshow.dao;

import ir.hamyiar.hamyshow.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT u FROM User AS u OFFSET :offsetUser LIMIT :limitUser",
            nativeQuery = true)
    List<User> getAllUsers(@Param("offsetUser") int offsetUser, @Param("limitUser") int limitUser);

    void deleteByUsername(String username);
}
