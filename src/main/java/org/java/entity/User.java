package org.java.entity;

import java.io.Serializable;

/**
 * Created by jh on 2017/6/24.
 */
public class User implements Serializable{

    private  Integer id;

    private String name;

    private String birthdate;


    public User() {
    }

    public User(Integer id, String name, String birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
