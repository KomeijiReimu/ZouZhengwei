package com.rentcar.service.Impl;

import com.rentcar.common.ConsRent;
import com.rentcar.entity.pojo.Vehicle;
import com.rentcar.factory.CourseFactory;
import com.rentcar.mapper.VehicleMapper;
import com.rentcar.service.StoreMgrService;
import javafx.util.Pair;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StoreMgrServiceImpl implements StoreMgrService {
    Pair<SqlSession, VehicleMapper> sqlSessionVehicleMapperPair;

    /**
     * 逻辑：先获取代理，然后接受交互，选择执行操作：查看 修改 增加 删除
     * 查看：传递参数选择查看类型，之后提供返回
     * 增加：完整参数增加，增加合法性判断
     * 修改：传递id，修改全部，合法性判断
     * 删除：传递id删除
     */

    // 执行操作界面
    @Override
    public void Todo() {
        try {
            // 获取sqlSession和Mapper对象
            sqlSessionVehicleMapperPair = daoInit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean flag = true;
        while (flag) {
            Scanner input = new Scanner(System.in);
            try {
                // 异常抛出机制处理非法输入
                System.out.println(ConsRent.mainAdmin1);
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        while (select()) ;
                        break;
                    case 2:
                        while (mod()) ;
                        break;
                    case 3:
                        add();
                        break;
                    case 4:
                        while (del()) ;
                        break;
                    case 5:
                        System.out.println("营业额为：" +
                                rentPay(sqlSessionVehicleMapperPair.getValue()) + "元");
                        break;

                    case 6:
                        flag = false;
                        // 退出前释放资源
                        sqlSessionVehicleMapperPair.getKey().close();
                        break;
                    default:
                        System.out.println(ConsRent.errorSelAdmin); // 数字不正确
                }
            } catch (InputMismatchException e) {
                System.out.println(ConsRent.errorSelAdmin); // 数字
                input.nextLine();
            }
        }
    }


    /**
     * 查询车辆
     *
     * @param vehicleMapper 传递Mapper
     * @param k             选择查询条件
     * @return 返回查询结果
     */
    @Override
    public List<Vehicle> showCars(VehicleMapper vehicleMapper, int k) {
        switch (k) {
            case 0:
                return vehicleMapper.selectAll();
            case 1:
                return vehicleMapper.selectTrunk();
            case 2:
                return vehicleMapper.selectCar();
            case 3:
                return vehicleMapper.selectBus();
            default:
                return null;

        }
    }

    @Override
    public Boolean modifyCar(VehicleMapper vehicleMapper, int id) {
        CourseFactory factory = new CourseFactory();
        // 使用简单工厂创建实例对象
        Vehicle vehicle = factory.creatV("Vehicle");
        Scanner input = new Scanner(System.in);
        System.out.println(ConsRent.do2); // 种类
        int kind = input.nextInt();
        System.out.println(ConsRent.do3);
        String brand = input.next();
        System.out.println(ConsRent.do4); // model
        String model = input.next();
        System.out.println(ConsRent.do5);
        float price = input.nextFloat();
        vehicle.setKind(kind);
        vehicle.setModel(model);
        vehicle.setPrice(price);
        vehicle.setBrand(brand);
        vehicle.setId(id);
        return vehicleMapper.modifyVehicle(vehicle);
    }

    @Override
    public Boolean addCar(VehicleMapper vehicleMapper) {
        CourseFactory factory = new CourseFactory();
        // 使用简单工厂创建实例对象
        Vehicle car = factory.creatV("Car");
        Scanner input = new Scanner(System.in);
        System.out.println(ConsRent.do2); // 种类
        int kind = input.nextInt();
        System.out.println(ConsRent.do3);
        String brand = input.next();
        System.out.println(ConsRent.do4); // model
        String model = input.next();
        System.out.println(ConsRent.do5);
        float price = input.nextFloat();
        car.setKind(kind);
        car.setModel(model);
        car.setPrice(price);
        car.setBrand(brand);
        return vehicleMapper.addVehicle(car);
    }

    @Override
    public Boolean delCar(VehicleMapper vehicleMapper, int id) {
        return vehicleMapper.delVehicle(id);
    }

    @Override
    public Float rentPay(VehicleMapper vehicleMapper) {
        CarPay carPay = new CarPay(vehicleMapper);
        TrunkPay trunkPay = new TrunkPay(vehicleMapper);
        BusPay busPay = new BusPay(vehicleMapper);
        carPay.start();
        trunkPay.start();
        busPay.start();
        try {
            carPay.join();
            trunkPay.join();
            busPay.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return carPay.res + trunkPay.res + busPay.res;
    }

    /**
     * 进入查询页面，格式化输出
     *
     * @return
     */
    public boolean select() {
        Scanner input = new Scanner(System.in);
        System.out.println(ConsRent.mainAdmin2);
        int kind = input.nextInt();
        System.out.printf("%-5s%-5s%-16s%-20s%-17s%-17s%-17s%-8s%-10s%n", "ID", "种类", "品牌", "型号", "价格", "出借日期", "租借天数", "租借情况", "租借人");
        switch (kind) {
            case 0:
                List<Vehicle> vehicles = showCars(sqlSessionVehicleMapperPair.getValue(), 0);
                for (Vehicle vehicle : vehicles) {
                    System.out.printf("%-5s%-5s%-20s%-20s%-20s%-20s%-20s%-10s%-10s%n", vehicle.getId(), vehicle.getKind(), vehicle.getBrand(), vehicle.getModel(), vehicle.getPrice(), vehicle.getDate(), vehicle.getDays(), vehicle.getIs_rented(), vehicle.getRented_by());
                }
                System.out.println(ConsRent.back); // 任意返回
                input.next();
                return false;
            case 1:
                List<Vehicle> trunks = showCars(sqlSessionVehicleMapperPair.getValue(), 1);
                for (Vehicle trunk : trunks) {
                    System.out.printf("%-5s%-5s%-20s%-20s%-20s%-20s%-20s%-10s%-10s%n", trunk.getId(), trunk.getKind(), trunk.getBrand(), trunk.getModel(), trunk.getPrice(), trunk.getDate(), trunk.getDays(), trunk.getIs_rented(), trunk.getRented_by());
                }
                System.out.println(ConsRent.back); // 任意返回
                input.next();
                return false;
            case 2:
                List<Vehicle> cars = showCars(sqlSessionVehicleMapperPair.getValue(), 2);
                for (Vehicle car : cars) {
                    System.out.printf("%-5s%-5s%-20s%-20s%-20s%-20s%-20s%-10s%-10s%n", car.getId(), car.getKind(), car.getBrand(), car.getModel(), car.getPrice(), car.getDate(), car.getDays(), car.getIs_rented(), car.getRented_by());
                }
                System.out.println(ConsRent.back); // 任意返回
                input.next();
                return false;
            case 3:
                List<Vehicle> buses = showCars(sqlSessionVehicleMapperPair.getValue(), 3);
                for (Vehicle bus : buses) {
                    System.out.printf("%-5s%-5s%-20s%-20s%-20s%-20s%-20s%-10s%-10s%n", bus.getId(), bus.getKind(), bus.getBrand(), bus.getModel(), bus.getPrice(), bus.getDate(), bus.getDays(), bus.getIs_rented(), bus.getRented_by());
                }
                System.out.println(ConsRent.back); // 任意返回
                input.next();
                return false;
            default:
                System.out.println(ConsRent.errorSelAdmin); // 输入正确数字
                return true;
        }
    }

    /**
     * 进入修改页面
     *
     * @return
     */
    public boolean mod() {
        Scanner input = new Scanner(System.in);
        System.out.println(ConsRent.do1); // 选择修改id
        int id = input.nextInt();
        if (sqlSessionVehicleMapperPair.getValue().getPrice(id) == null) {
            System.out.println(ConsRent.errorNotFound);
            return true;
        } else {
            if (modifyCar(sqlSessionVehicleMapperPair.getValue(), id)) {
                System.out.println(ConsRent.SUCCEED);
                sqlSessionVehicleMapperPair.getKey().commit();
                return false;
            } else {
                System.out.println(ConsRent.FAIL);
                return false;
            }
        }
    }

    public void add() {
        if (addCar(sqlSessionVehicleMapperPair.getValue())) {
            System.out.println(ConsRent.SUCCEED);
            sqlSessionVehicleMapperPair.getKey().commit();
        } else {
            System.out.println(ConsRent.FAIL);
        }
    }

    /**
     * 进入删除页面
     *
     * @return
     */
    public boolean del() {
        Scanner input = new Scanner(System.in);
        System.out.println(ConsRent.do1);
        int id = input.nextInt();
        if (sqlSessionVehicleMapperPair.getValue().getPrice(id) == null) {
            System.out.println(ConsRent.errorNotFound);
            return true;
        }
        if (delCar(sqlSessionVehicleMapperPair.getValue(), id)) {
            System.out.println(ConsRent.SUCCEED);
            sqlSessionVehicleMapperPair.getKey().commit();
        } else {
            System.out.println(ConsRent.FAIL);
        }
        return false;
    }

}
