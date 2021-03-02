package edu.temple.homerecognition;

public class DatabaseMethods implements Database {
    @Override
    public boolean addUser(User user) {
        return true;
    }

    @Override
    public boolean addHouse(House house) {
        return true;
    }

    @Override
    public User getUser(User user) {

        return user;
    }

    @Override
    public House getHouse() {
        House house = new House();
        return house;
    }
}
