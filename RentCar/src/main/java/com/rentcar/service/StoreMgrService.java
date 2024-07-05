package com.rentcar.service;

import com.rentcar.mapper.VehicleMapper;

public interface StoreMgrService extends UserService {
    void Todo();


    Boolean modifyCar(VehicleMapper vehicleMapper, int id);

    Boolean addCar(VehicleMapper vehicleMapper);

    Boolean delCar(VehicleMapper vehicleMapper, int id);

    Float rentPay(VehicleMapper vehicleMapper);
}
