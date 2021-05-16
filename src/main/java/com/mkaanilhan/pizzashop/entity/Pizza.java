package com.mkaanilhan.pizzashop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pizza")
public class Pizza {

    @Id
    private String id;
    private String img;
    private String name;
    private double price;
    private String description;

    public Pizza(){

        super();

    }

    public Pizza(String id,String img, String name, double price, String description) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.price = price;
        this.description = description;
    }


    public Pizza(String img, String name, double price, String description) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
