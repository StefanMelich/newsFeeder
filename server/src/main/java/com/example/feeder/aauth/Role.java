package com.example.feeder.aauth;

public enum Role {
    USER("U"),
    ADMIN("A");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }
}
