package com.rentcar.service.Impl;

import com.rentcar.entity.pojo.Vehicle;
import com.rentcar.mapper.VehicleMapper;

import java.util.List;

public class TrunkPay extends Thread {
    float res = 0;

    VehicleMapper vehicleMapper;

    public TrunkPay(VehicleMapper vehicleMapper) {
        this.vehicleMapper = vehicleMapper;
    }

    public float getRes() {
        return res;
    }

    @Override
    public void run() {
        List<Vehicle> vehicles = vehicleMapper.selectTrunkPay();
        for (Vehicle vehicle : vehicles) {
            res += vehicle.getPrice() * vehicle.getDays();
        }
    }
}
