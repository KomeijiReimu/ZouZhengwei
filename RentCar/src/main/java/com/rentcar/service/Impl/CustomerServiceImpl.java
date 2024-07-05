package com.rentcar.service.Impl;

import com.rentcar.common.ConsRent;
import com.rentcar.entity.pojo.User;
import com.rentcar.entity.pojo.Vehicle;
import com.rentcar.mapper.VehicleMapper;
import com.rentcar.service.CustomerService;
import javafx.util.Pair;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CustomerServiceImpl implements CustomerService {
    User user;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date currentDate = new Date();
    String formattedDate = dateFormat.format(currentDate);
    Pair<SqlSession, VehicleMapper> sqlSessionVehicleMapperPair;


    public CustomerServiceImpl(User user) {
        this.user = user;
    }

    public float calRent(int days, float perRent) {
        // 计算租金，根据不同的折扣规则
        float rent = 0;
        if (days > 0 && days <= 7) {
            rent = days * perRent;
        } else if (days > 7 && days <= 30) {
            rent = days * perRent * 0.9f;
        } else if (days > 30 && days <= 150) {
            rent = days * perRent * 0.8f;
        } else if (days > 150) {
            rent = days * perRent * 0.7f;
        } else {
            System.out.println("请输入正确的天数");
        }
        return rent;
    }


    /**
     * 逻辑：用户界面，选择租车种类，之后展示相关车辆，选择租车id
     * 租车：传递id，修改四列
     */
    @Override
    public void Todo() {
        try {
            sqlSessionVehicleMapperPair = daoInit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean flag = true;
        while (flag) {
            Scanner input = new Scanner(System.in);
            try {

                List<Vehicle> Rentcar = showRentcars(sqlSessionVehicleMapperPair.getValue(), user.getUserId());
                if (!Rentcar.isEmpty()) {
                    System.out.println(ConsRent.mainUser1); // 目前租的车
                    System.out.printf("%-5s%-5s%-16s%-20s%-17s%n", "ID", "种类", "品牌", "型号", "价格");
                    for (Vehicle vehicle : Rentcar) {
                        System.out.printf("%-5s%-5s%-20s%-20s%-20s%n", vehicle.getId(), vehicle.getKind(), vehicle.getBrand(), vehicle.getModel(), vehicle.getPrice());
                    }

                }
                System.out.println(ConsRent.mainUser2); // 租什么车操作
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        List<Vehicle> trunks = showCars(sqlSessionVehicleMapperPair.getValue(), choice);
                        if (trunks == null) {
                            System.out.println(ConsRent.errorNoV); // 无车
                            continue;
                        }
                        System.out.printf("%-5s%-5s%-16s%-20s%-17s%n", "ID", "种类", "品牌", "型号", "价格");
                        for (Vehicle trunk : trunks) {
                            System.out.printf("%-5s%-5s%-20s%-20s%-20s%n", trunk.getId(), trunk.getKind(), trunk.getBrand(), trunk.getModel(), trunk.getPrice());
                        }
                        if (rentCar(sqlSessionVehicleMapperPair.getValue())) {
                            System.out.println(ConsRent.SUCCEED);
                            sqlSessionVehicleMapperPair.getKey().commit();
                            break;
                        } else {
                            System.out.println(ConsRent.FAIL);
                            break;
                        }
                    case 2:
                        List<Vehicle> cars = showCars(sqlSessionVehicleMapperPair.getValue(), choice);
                        if (cars == null) {
                            System.out.println(ConsRent.errorNoV); // 无车
                            continue;
                        }
                        for (Vehicle car : cars) {
                            System.out.printf("%-5s%-5s%-20s%-20s%-20s%n", car.getId(), car.getKind(), car.getBrand(), car.getModel(), car.getPrice());
                        }
                        if (rentCar(sqlSessionVehicleMapperPair.getValue())) {
                            System.out.println(ConsRent.SUCCEED);
                            sqlSessionVehicleMapperPair.getKey().commit();
                            break;
                        } else {
                            System.out.println(ConsRent.FAIL);
                            break;
                        }
                    case 3:
                        List<Vehicle> buses = showCars(sqlSessionVehicleMapperPair.getValue(), choice);
                        if (buses == null) {
                            System.out.println(ConsRent.errorNoV); // 无车
                            continue;
                        }
                        for (Vehicle bus : buses) {
                            System.out.printf("%-5s%-5s%-20s%-20s%-20s%n", bus.getId(), bus.getKind(), bus.getBrand(), bus.getModel(), bus.getPrice());
                        }
                        if (rentCar(sqlSessionVehicleMapperPair.getValue())) {
                            System.out.println(ConsRent.SUCCEED);
                            sqlSessionVehicleMapperPair.getKey().commit();
                            break;
                        } else {
                            System.out.println(ConsRent.FAIL);
                            break;
                        }
                    case 4:
                        if (!Rentcar.isEmpty()) {
                            System.out.println(ConsRent.unRent);
                            int id = input.nextInt();
                            boolean exist = false;
                            for (Vehicle vehicle : Rentcar) {
                                if (vehicle.getId() == id) {
                                    exist = true;
                                    break;
                                }
                            }
                            if (exist) {
                                if (unRentcar(sqlSessionVehicleMapperPair.getValue(), id)) {
                                    sqlSessionVehicleMapperPair.getKey().commit();
                                    System.out.println(ConsRent.unRentSuccess);
                                    break;
                                }
                            } else
                                System.out.println(ConsRent.unRemntError);
                        } else
                            System.out.println(ConsRent.unRemntNone);
                        break;
                    case 5:
                        System.out.println(ConsRent.back);
                        flag = false;
                        sqlSessionVehicleMapperPair.getKey().close();
                        input.next();
                        break;
                    default:
                        System.out.println(ConsRent.errorSelAdmin); // 数字不正确

                }

            } catch (InputMismatchException e) {
                System.out.println(ConsRent.errorSelAdmin);
                input.nextLine();
            }
        }

    }

    @Override
    public Boolean rentCar(VehicleMapper vehicleMapper) {
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        int id = 0;
        while (flag) {
            System.out.println(ConsRent.do1); // id
            id = input.nextInt();
            if (vehicleMapper.getPrice(id) == null) {
                System.out.println(ConsRent.errorNotFound); // 不存在车辆
                continue;
            }
            if (vehicleMapper.isEnabled(id)) {
                System.out.println(ConsRent.errorNotEnabled); // 车辆不可用
                continue;
            }
            flag = false;
        }
        System.out.println(ConsRent.rentDays);
        int days = input.nextInt();
        System.out.println("您需要支付的租赁费用是：" + calRent(days, vehicleMapper.getPrice(id)) + "元");
        System.out.println(ConsRent.rentcarPay);
        return vehicleMapper.rentVehicle(id, user.getUserId(), formattedDate, days);
    }


    @Override
    public List<Vehicle> showCars(VehicleMapper vehicleMapper, int k) {
        switch (k) {
            case 1:
                return vehicleMapper.userselectTrunk();
            case 2:
                return vehicleMapper.userselectCar();
            case 3:
                return vehicleMapper.userselectBus();
            default:
                return null;
        }
    }

    private List<Vehicle> showRentcars(VehicleMapper vehicleMapper, int id) {
        return vehicleMapper.userselectRentcar(id);
    }

    private Boolean unRentcar(VehicleMapper vehicleMapper, int id) {
        return vehicleMapper.unRentCar(id);
    }

}

