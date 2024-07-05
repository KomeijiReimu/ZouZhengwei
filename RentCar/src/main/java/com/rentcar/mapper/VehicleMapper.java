package com.rentcar.mapper;

import com.rentcar.entity.pojo.Vehicle;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface VehicleMapper {
    @Select("select * from vehicle")
    List<Vehicle> selectAll();

    @Select("select * from vehicle where kind = 2")
    List<Vehicle> selectCar();

    @Select("select * from vehicle where kind = 3")
    List<Vehicle> selectBus();

    @Select("select * from vehicle where kind = 1")
    List<Vehicle> selectTrunk();

    @Select("select id, kind, brand, model, price, is_rented from vehicle where kind = 1")
    List<Vehicle> userselectTrunk();

    @Select("select id, kind, brand, model, price, is_rented from vehicle where kind = 2")
    List<Vehicle> userselectCar();

    @Select("select id, kind, brand, model, price, is_rented from vehicle where kind = 3")
    List<Vehicle> userselectBus();

    @Select("select * from vehicle where rented_by = #{id}")
    List<Vehicle> userselectRentcar(int id);

    @Update("update vehicle set is_rented = 0, rented_by = null,date = null, days = null where id = #{id}")
    Boolean unRentCar(int id);

    @Delete("delete from vehicle where id = #{id}")
    Boolean delVehicle(int id);

    @Insert("insert into vehicle(kind, brand, model, price) values (#{kind}, #{brand}, #{model}, #{price})")
    Boolean addVehicle(Vehicle vehicle);

    Boolean modifyVehicle(Vehicle vehicle);

    @Update("update vehicle set is_rented = 1, rented_by = #{rent_byid},date = #{date}, days = #{days} where id = #{id}")
    Boolean rentVehicle(@Param("id") int id, @Param("rent_byid") int rentid, @Param("date") String date, @Param("days") int days);

    @Select("select price from vehicle where id = #{id}")
    Integer getPrice(int id);

    @Select("select is_rented from vehicle where id = #{id}")
    Boolean isEnabled(int id);

    @Select("select password from user where binary name = #{name}")
    String getUSerPassword(String name);

    @Select("select password from admin where binary name = #{name}")
    String getAdminPassword(String name);
    @Select("select id from user where binary name = #{name}")
    int getUserId(String name);

    @Insert("insert into user(name, password, registerdate) values (#{name}, #{password}, #{registerdate})")
    Boolean registerUser(@Param("name") String name, @Param("password") String password, @Param("registerdate") String registerdate);

    @Select("select * from vehicle where kind = 2 and is_rented = 1")
    List<Vehicle> selectCarPay();

    @Select("select * from vehicle where kind = 1 and is_rented = 1")
    List<Vehicle> selectTrunkPay();

    @Select("select * from vehicle where kind = 3 and is_rented = 1")
    List<Vehicle> selectBusPay();

}
