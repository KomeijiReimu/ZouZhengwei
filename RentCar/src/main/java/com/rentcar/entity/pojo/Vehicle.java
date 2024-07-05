package com.rentcar.entity.pojo;

import java.sql.Date;

public class Vehicle {
    // 建表之后直接把名称拉下来，生成getter和setter方法
    private Integer id;
    /**
     * kind 1:trunk 2:car 3:bus
     */
    private Integer kind;
    private String brand;
    private String model;
    private Float price;
    private Date date;
    private Integer days;
    private Boolean is_rented;
    private Integer rented_by;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Boolean getIs_rented() {
        return is_rented;
    }

    public void setIs_rented(Boolean is_rented) {
        this.is_rented = is_rented;
    }

    public Integer getRented_by() {
        return rented_by;
    }

    public void setRented_by(Integer rented_by) {
        this.rented_by = rented_by;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", kind=" + kind +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", days=" + days +
                ", is_rented=" + is_rented +
                ", rented_by='" + rented_by + '\'' +
                '}';
    }
}
