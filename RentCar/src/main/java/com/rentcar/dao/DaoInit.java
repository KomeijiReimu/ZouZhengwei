package com.rentcar.dao;

import com.rentcar.mapper.VehicleMapper;
import javafx.util.Pair;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DaoInit {
    public static Pair<SqlSession, VehicleMapper> init() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        return new Pair<>(sqlSession, sqlSession.getMapper(VehicleMapper.class));
    }
}
