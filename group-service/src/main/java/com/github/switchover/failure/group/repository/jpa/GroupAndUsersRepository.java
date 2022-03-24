package com.github.switchover.failure.group.repository.jpa;

import com.github.switchover.failure.group.model.GroupAndUsers;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Profile("spring-data-jpa")
public interface GroupAndUsersRepository extends JpaRepository<GroupAndUsers, Integer> {
    Optional<GroupAndUsers> findByGroupName(String groupName);
}
