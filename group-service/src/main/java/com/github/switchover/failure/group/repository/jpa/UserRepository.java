package com.github.switchover.failure.group.repository.jpa;

import com.github.switchover.failure.group.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Profile("spring-data-jpa")
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNickName(String nickName);
}
