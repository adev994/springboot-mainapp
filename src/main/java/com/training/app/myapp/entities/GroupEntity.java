package com.training.app.myapp.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name="groups")
public class GroupEntity {

    @Id
    @GeneratedValue
    private String id;

    @Column(name="name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="groups_users",joinColumns = @JoinColumn(name="groups_id"),inverseJoinColumns = @JoinColumn(name="users_id"))
    private Set<UserEntity> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
