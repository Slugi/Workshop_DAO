package pl.coderslab.entity;


public class MainDao {
    public static void main(String[] args){


    UserDao userDao = new UserDao();
    User user = new User();
    user.setUserName("Maciek");
    user.setEmail("maciek.m@gmail.com");
    user.setPassword("maslo");
    userDao.create(user);

}}
