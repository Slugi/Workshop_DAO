package pl.coderslab.entity;

public class MainDao {
  public static void main(String[] args) {

    UserDao userDao = new UserDao();
    User user = new User();
    /*   user.setUserName("Maciek");
    user.setEmail("maciek.m@gmail.com");
    user.setPassword("maslo");
    userDao.create(user);*/
    /*User read = userDao.read(1);
    System.out.println(read);
    User readNotExist = userDao.read(2);
    System.out.println(readNotExist);*/
   /* User userToUpdate = userDao.read(1);
    userToUpdate.setUserName("Nowe imie");
    userToUpdate.setEmail("nowyemail@poczta.pl");
    userToUpdate.setPassword("superMaslo");
    userDao.update(userToUpdate);*/
    /*User secondUser = new User();
    secondUser.setUserName("Maćko");
    secondUser.setEmail("macko.zbogdanca@email.com");
    secondUser.setPassword("potop");
    userDao.create(secondUser);
    User[] all = userDao.findAll();
    for(User u : all){
      System.out.println(u);
          }*/
    userDao.delete(1);
    userDao.delete(2);
  }
}
