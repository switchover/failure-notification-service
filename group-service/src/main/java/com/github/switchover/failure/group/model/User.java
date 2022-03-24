package com.github.switchover.failure.group.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.switchover.failure.common.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "nickname")
    @NotBlank
    private String nickName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userId", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Messenger> messengers = new LinkedHashSet<>();

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setMessengers(Set<Messenger> messengers) {
        this.messengers = messengers;
    }

    public Set<Messenger> getMessengers() {
        return messengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return nickName.equals(user.nickName);
    }

    @Override
    public int hashCode() {
        return nickName.hashCode();
    }
}
