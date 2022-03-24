package com.github.switchover.failure.group.model;

import com.github.switchover.failure.common.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "groups")
public class Group extends BaseEntity {
    @Column(name = "groupname")
    @NotBlank
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
