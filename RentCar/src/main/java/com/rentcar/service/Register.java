package com.rentcar.service;

public interface Register {
    void registerUser(String name, String password);

    Boolean isExist(String name);
}
