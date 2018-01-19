package com.pwc.newfind.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lhuang126 on 1/17/2018.
 */

@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private Integer age;
    private String token;

    @Generated(hash = 555753134)
    public User(Long id, String name, Integer age, String token) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.token = token;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}