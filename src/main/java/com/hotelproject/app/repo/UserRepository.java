package com.hotelproject.app.repo;

import com.hotelproject.app.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByUsernameAndPassword(String username, String password);
}
