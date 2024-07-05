package com.rentcar.service;

import com.rentcar.dao.DaoInit;
import com.rentcar.entity.pojo.Vehicle;
import com.rentcar.mapper.VehicleMapper;
import javafx.util.Pair;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public interface UserService {
    default Pair<SqlSession, VehicleMapper> daoInit() throws IOException {
        return DaoInit.init();
    }

    List<Vehicle> showCars(VehicleMapper vehicleMapper, int k);

}
