package com.example.healthcare.model;

public class LabTestModel {
    public int img;
    public String name;
    public String price;
    public String no_of_test;
    public String test_details;
    public String test_time;

    public LabTestModel(int img, String name, String price, String no_of_test, String test_details, String test_time) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.no_of_test = no_of_test;
        this.test_details = test_details;
        this.test_time = test_time;
    }
}
