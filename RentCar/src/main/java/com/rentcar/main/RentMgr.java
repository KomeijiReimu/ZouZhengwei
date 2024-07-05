package com.rentcar.main;

import com.rentcar.common.ConsRent;
import com.rentcar.entity.pojo.User;
import com.rentcar.service.CustomerService;
import com.rentcar.service.Impl.CustomerServiceImpl;
import com.rentcar.service.Impl.LoginImpl;
import com.rentcar.service.Impl.RegisterImpl;
import com.rentcar.service.Impl.StoreMgrServiceImpl;
import com.rentcar.service.Login;
import com.rentcar.service.StoreMgrService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RentMgr {
    static boolean flag = true;
    static String name, password;
    static Scanner input = new Scanner(System.in);


    static User user;

    public static void main(String[] args) {
        while (flag) {
            try {
                System.out.println(ConsRent.welcome);
                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        loginAdmin();
                        break;
                    case 2:
                        loginUser();
                        break;
                    case 3:
                        registerUser();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(ConsRent.errorSelAdmin);
                input.nextLine();
            }
        }
    }

    static void loginAdmin() {
        Login login = new LoginImpl();
        System.out.println(ConsRent.welcome1);
        name = input.next();
        System.out.println(ConsRent.welcome2);
        password = input.next();
        user = login.loginAdmin(name, password);
        if (user.getUserKind() != 0) {
            System.out.println(ConsRent.welAdmin);
            StoreMgrService storeMgrService = new StoreMgrServiceImpl();
            input.nextLine();
            storeMgrService.Todo();
            flag = false;
        } else
            System.out.println(ConsRent.errorUser);
    }

    private static void loginUser() {
        Login login = new LoginImpl();
        System.out.println(ConsRent.welcome1);
        name = input.next();
        System.out.println(ConsRent.welcome2);
        password = input.next();
        user = login.loginUser(name, password);
        if (user.getUserKind() != 0) {
            System.out.println(ConsRent.welUser);
            CustomerService customerService = new CustomerServiceImpl(user);
            input.nextLine();
            customerService.Todo();
            flag = false;
        } else
            System.out.println(ConsRent.errorUser);

    }

    private static void registerUser() {
        RegisterImpl register = new RegisterImpl();
        System.out.println(ConsRent.Register1);
        Scanner input = new Scanner(System.in);
        name = input.next();
        if (!register.isExist(name)) {
            System.out.println(ConsRent.welcome2);
            password = input.next();
            register.registerUser(name, password);
        } else
            System.out.println(ConsRent.RegisterExist);

    }
}
