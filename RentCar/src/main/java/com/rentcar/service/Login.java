package com.rentcar.service;

import com.rentcar.entity.pojo.User;
import com.rentcar.mapper.VehicleMapper;

public interface Login {
    User loginUser(String name, String password);

    User loginAdmin(String name, String password);

    String getUserPassword(VehicleMapper vehicleMapper, String name);

    String getAdminPassword(VehicleMapper vehicleMapper, String name);

    int getUserId(String name, VehicleMapper vehicleMapper);
}
