package com.mokhovav.boardgames.authorization;


import com.mokhovav.base.databases.SQL.entities.BaseUserGroup;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group extends BaseUserGroup {
    public Group() {
    }

    public Group(@NotBlank(message = "Group name cannot be empty") String group) {
        super(group);
    }

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)         // Bidirectional communication
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
