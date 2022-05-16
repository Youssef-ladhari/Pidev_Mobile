package com.pidev.entities;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class Project {

Integer id ;
User creator ;
Category category;
String name;
ArrayList<User> users;
String description;
Integer periode;
Float price;
String image;
Integer state;
ArrayList<Integer> blocked;



    public Project() {
    }

    public Project(Integer id, User creator, Category category, String name, String description, Integer periode, Float price, String image, Integer state, ArrayList<Integer> blocked,ArrayList<User> users) {
        this.id = id;
        this.creator = creator;
        this.category = category;
        this.name = name;
        this.description = description;
        this.periode = periode;
        this.price = price;
        this.image = image;
        this.state = state;
        this.blocked = blocked;
        this.users=users;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", periode=" + periode +
                ", price=" + price +

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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
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

    public ArrayList<Integer> getBlocked() {
        return blocked;
    }

    public void setBlocked(ArrayList<Integer> blocked) {
        this.blocked = blocked;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
