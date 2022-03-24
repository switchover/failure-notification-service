package com.github.switchover.failure.group.model;

import com.github.switchover.failure.common.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class GroupAndUsers extends BaseEntity {
    @Column(name = "groupname")
    @NotBlank
    private String groupName;

    @ManyToMany
    @JoinTable(name = "groups_users",
    joinColumns = @JoinColumn(name = "group_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userList = new LinkedHashSet<>();

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<User> getUserList() {
        return userList;
    }

    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }
}
