import javax.persistence.Entity;
import javax.persistence.Id;

package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
//@Entity
public class User {

    @Id
    private long id;
    private String username;
    private String password;
    private String role = "USER"; // default role
    // private String passwordHash; // stores password hashed


    // getters and setters
    public long getId{
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

}