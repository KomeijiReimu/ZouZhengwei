package com.rentcar.service.Impl;

import com.rentcar.common.ConsRent;
import com.rentcar.dao.DaoInit;
import com.rentcar.mapper.VehicleMapper;
import com.rentcar.service.Register;
import javafx.util.Pair;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterImpl implements Register {
    Pair<SqlSession, VehicleMapper> sqlSessionVehicleMapperPair;

    {
        try {
            sqlSessionVehicleMapperPair = DaoInit.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerUser(String name, String password) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);
        password = DigestUtils.md5Hex(password);
        if (sqlSessionVehicleMapperPair.getValue().registerUser(name, password, formattedDate))
            System.out.println(ConsRent.RegisterSuccess);
        else {
            System.out.println(ConsRent.RegisterFail);
            return;
        }
        sqlSessionVehicleMapperPair.getKey().commit();
        sqlSessionVehicleMapperPair.getKey().close();
    }

    @Override
    public Boolean isExist(String name) {
        if (sqlSessionVehicleMapperPair.getValue().getUSerPassword(name) == null) {
            return false;
        } else return true;
    }
}
