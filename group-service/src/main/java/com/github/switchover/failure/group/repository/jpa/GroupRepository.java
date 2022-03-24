package com.github.switchover.failure.group.repository.jpa;

import com.github.switchover.failure.group.model.Group;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Profile("spring-data-jpa")
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Optional<Group> findByGroupName(String groupName);
}
