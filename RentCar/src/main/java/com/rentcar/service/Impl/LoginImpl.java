package com.rentcar.service.Impl;

import com.rentcar.dao.DaoInit;
import com.rentcar.entity.pojo.User;
import com.rentcar.factory.CourseFactory;
import com.rentcar.mapper.VehicleMapper;
import com.rentcar.service.Login;
import javafx.util.Pair;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.Objects;

public class LoginImpl implements Login {
    Pair<SqlSession, VehicleMapper> sqlSessionVehicleMapperPair;

    {
        try {
            sqlSessionVehicleMapperPair = DaoInit.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User loginAdmin(String name, String password) {
        CourseFactory factory = new CourseFactory();
        User user = factory.creatU("User");
        String md5Password = getAdminPassword(sqlSessionVehicleMapperPair.getValue(), name);
        password = DigestUtils.md5Hex(password);
        if (Objects.equals(password, md5Password)) {
            user.setUserName(name);
            user.setUserPwd(password);
            user.setUserKind(1);
        } else
            user.setUserKind(0);

        return user;
    }

    @Override
    public User loginUser(String name, String password) {
        CourseFactory factory = new CourseFactory();
        User user = factory.creatU("User");
        String md5Password = getUserPassword(sqlSessionVehicleMapperPair.getValue(), name);
        password = DigestUtils.md5Hex(password);
        if (Objects.equals(password, md5Password)) {
            user.setUserName(name);
            user.setUserPwd(password);
            user.setUserId(getUserId(name, sqlSessionVehicleMapperPair.getValue()));
            user.setUserKind(2);
        } else
            user.setUserKind(0);
        sqlSessionVehicleMapperPair.getKey().close();
        return user;
    }

    @Override
    public String getUserPassword(VehicleMapper vehicleMapper, String name) {
        return vehicleMapper.getUSerPassword(name);
    }

    @Override
    public String getAdminPassword(VehicleMapper vehicleMapper, String name) {
        return vehicleMapper.getAdminPassword(name);
    }

    @Override
    public int getUserId(String name, VehicleMapper vehicleMapper) {
        return vehicleMapper.getUserId(name);
    }
}
