package com.atacadista.user;


// Armazena as possíveis roles do sistema
public enum UserRole {
    ADMIN("admin"),
    DIRECTOR("director"),
    MANAGER("manager"),
    CASHIER("cashier"),
    STOCKIST("stockist"),
    USER("user");

    private String userRole;

    UserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getRole() {
        return userRole;
    }
}
