package edu.temple.homerecognition;

public interface Database {

    public boolean addUser(User user);

    public boolean addHouse(House house);

    public User getUser(User user);

    public House getHouse();
}
