package com.example.feeder.domain.repository;

import java.util.Optional;

import com.example.feeder.domain.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsUserByUsername(String username);

    Boolean existsUserByEmail(String email);
}
