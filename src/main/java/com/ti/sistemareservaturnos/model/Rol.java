package com.ti.sistemareservaturnos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
public class Rol {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Rol(Long id, String name) {
        this.name = name;
        this.id = id;
    }


    public Rol() {

    }
}
