package com.ua.vinn.MyBetaApp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {
    @Id
    private String id;
    private ERole role;

    public Role() {}

    public Role(ERole role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public ERole getRole() {
        return role;
    }
    public void setRole(ERole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" + "id='" + id + '\'' + ", role=" + role + '}';
    }
}
