import com.rentcar.entity.pojo.Vehicle;
import com.rentcar.mapper.VehicleMapper;
import com.rentcar.service.Impl.RegisterImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class selTest {
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

    @Test
    public void testselectAll() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);

        List<Vehicle> v = vehicleMapper.selectAll();
        System.out.println(v);

        sqlSession.close();
    }

    @Test
    public void testselectCar() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);

        List<Vehicle> v = vehicleMapper.selectCar();
        System.out.println(v);

        sqlSession.close();
    }

    @Test
    public void testselectBus() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);

        List<Vehicle> v = vehicleMapper.selectBus();
        System.out.println(v);
        sqlSession.close();
    }

    @Test
    public void testselectTrunk() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);

        List<Vehicle> v = vehicleMapper.selectTrunk();
        System.out.println(v);

        sqlSession.close();
    }

    @Test
    public void testuserselectTrunk() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);

        List<Vehicle> v = vehicleMapper.userselectTrunk();
        System.out.println(v);

        sqlSession.close();
    }

    @Test
    public void testuserselectCar() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);

        List<Vehicle> v = vehicleMapper.userselectCar();
        System.out.println(v);

        sqlSession.close();
    }

    @Test
    public void testuserselectBus() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);

        List<Vehicle> v = vehicleMapper.userselectTrunk();
        for (Vehicle vehicle : v) {
            System.out.println(vehicle.getId());
        }
        System.out.println(v);

        sqlSession.close();
    }

    @Test
    public void testdelVehicle() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);

        vehicleMapper.delVehicle(11);

        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void testaddVehicle() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);
        Scanner scanner = new Scanner(System.in);
        // int kind = scanner.nextInt();
        // String brand = scanner.next();
        // String model = scanner.next();
        // float price = scanner.nextFloat();
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("asdaswwwwwwwww");
        vehicle.setKind(2);
        vehicle.setModel("asdasd");
        vehicle.setPrice((float) 350);
        System.out.println(vehicleMapper.addVehicle(vehicle));

        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void testmodifyVehicle() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);
        Scanner scanner = new Scanner(System.in);
        Vehicle vehicle = new Vehicle();
        vehicle.setId(3);
        vehicle.setBrand("asdaswwwwwwwww");
        vehicle.setKind(2);
        vehicle.setModel("asdasd");
        vehicle.setPrice((float) 350);
        System.out.println(vehicleMapper.modifyVehicle(vehicle));

        sqlSession.commit();

        sqlSession.close();
    }

    // @Test
    // public void testrentVehicle() throws IOException {
    //     String resource = "mybatis-config.xml";
    //     InputStream inputStream = Resources.getResourceAsStream(resource);
    //     SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    //
    //     SqlSession sqlSession = sqlSessionFactory.openSession();
    //     VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);
    //     Scanner scanner = new Scanner(System.in);
    //     Vehicle vehicle = new Vehicle();
    //     vehicle.setId(3);
    //     vehicle.setBrand("asdaswwwwwwwww");
    //     vehicle.setKind(2);
    //     vehicle.setModel("asdasd");
    //     vehicle.setPrice((float) 350);
    //     System.out.println(vehicleMapper.rentVehicle(4, "哇色调"));
    //
    //     sqlSession.commit();
    //
    //     sqlSession.close();
    // }

    @Test
    public void testgetPrice() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);

        System.out.println(vehicleMapper.getPrice(8));


        sqlSession.close();
    }

    @Test
    public void testisEnabled() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);

        System.out.println(vehicleMapper.isEnabled(2));


        sqlSession.close();
    }

    @Test
    public void testrentVehicle() throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);
        System.out.println(formattedDate);
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);
        int id = 2;
        vehicleMapper.rentVehicle(id, 1, formattedDate, 65);
        System.out.println(calRent(65, vehicleMapper.getPrice(id)));

        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void testIsexit() {
        String name = "老王";
        RegisterImpl register = new RegisterImpl();
        System.out.println(register.isExist(name));
    }

    @Test
    public void testgetDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);
        System.out.println(formattedDate);
    }

    @Test
    public void testUnrent() throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);
        System.out.println(formattedDate);
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        VehicleMapper vehicleMapper = sqlSession.getMapper(VehicleMapper.class);
        vehicleMapper.unRentCar(4);
        sqlSession.commit();

    }
}
