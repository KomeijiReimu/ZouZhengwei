package com.rentcar.service.Impl;

import com.rentcar.entity.pojo.Vehicle;
import com.rentcar.mapper.VehicleMapper;

import java.util.List;

public class CarPay extends Thread {
    float res = 0;

    VehicleMapper vehicleMapper;

    public CarPay(VehicleMapper vehicleMapper) {
        this.vehicleMapper = vehicleMapper;
    }

    public float getRes() {
        return res;
    }

    @Override
    public void run() {
        List<Vehicle> vehicles = vehicleMapper.selectCarPay();
        for (Vehicle vehicle : vehicles) {
            res += vehicle.getPrice() * vehicle.getDays();
        }
    }
}
