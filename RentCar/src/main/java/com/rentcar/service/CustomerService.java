package com.rentcar.service;

import com.rentcar.mapper.VehicleMapper;

public interface CustomerService extends UserService {
    void Todo();

    Boolean rentCar(VehicleMapper vehicleMapper);
}
