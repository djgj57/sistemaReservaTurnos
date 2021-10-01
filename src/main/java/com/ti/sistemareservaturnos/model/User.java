package com.ti.sistemareservaturnos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private Set<Rol> roles;

    public User(Long id, String name, String password, Set<Rol> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.id = id;
    }

    public User() {

    }
}
