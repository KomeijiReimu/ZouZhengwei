package com.rentcar.factory;

import com.rentcar.entity.pojo.*;

public class CourseFactory {
    public Vehicle creatV(String name) {
        switch (name) {
            case "Vehicle":
                return new Vehicle();
            case "Car":
                return new Car();
            case "Trunk":
                return new Trunk();
            case "Bus":
                return new Bus();
            default:
                return null;
        }
    }

    public User creatU(String name) {
        switch (name) {
            case "User":
                return new User();
            default:
                return null;
        }
    }
}
