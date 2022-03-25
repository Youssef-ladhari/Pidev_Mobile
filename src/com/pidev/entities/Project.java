package com.pidev.entities;

import java.lang.reflect.Array;

public class Project {

Integer id ;
Integer creator_id ;
Integer category_id;
String name;
String description;
Integer periode;
Integer price;
String image;
Integer state;
Array blocked;



    public Project() {
    }

    public Project(Integer id, Integer creator_id, Integer category_id, String name, String description, Integer periode, Integer price, String image, Integer state, Array blocked) {
        this.id = id;
        this.creator_id = creator_id;
        this.category_id = category_id;
        this.name = name;
        this.description = description;
        this.periode = periode;
        this.price = price;
        this.image = image;
        this.state = state;
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", creator_id=" + creator_id +
                ", category_id=" + category_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", periode=" + periode +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", state=" + state +
                ", blocked=" + blocked +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Integer creator_id) {
        this.creator_id = creator_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPeriode() {
        return periode;
    }

    public void setPeriode(Integer periode) {
        this.periode = periode;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Array getBlocked() {
        return blocked;
    }

    public void setBlocked(Array blocked) {
        this.blocked = blocked;
    }


}
