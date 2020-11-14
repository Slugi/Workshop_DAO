package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String Create_user_query =
            "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String Read_user_query =
            "SELECT * FROM users  id = ?";
    private static String Update_user_query =
            "UPDATE users SET username = ?, email = ?, password = ?, where id =?";
    private static String Delete_user_query =
            "DELETE FROM users WHERE id = ?";
    private static final String Find_all_users_query =
            "SELECT * FROM users";
    public String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    private User[] addToArray(User u,User[] users){
        User[] tempUsers = Arrays.copyOf(users, users.length +1);
        tempUsers[users.length] = u;
        return tempUsers;
    }
    public User create(User user){
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement prestat =
                    conn.prepareStatement(Create_user_query, Statement.RETURN_GENERATED_KEYS);
            prestat.setString(1, user.getUserName());
            prestat.setString(2,user.getEmail());
            prestat.setString(3, hashPassword(user.getPassword()));
            ResultSet resultSet = prestat.getGeneratedKeys();
            if(resultSet.next()){
                user.setId(resultSet.getInt(1));
            }
            return user;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public User read(int userId){
        try(Connection conn = DbUtil.getConnection()){
            PreparedStatement prestat = conn.prepareStatement(Read_user_query);
            prestat.setInt(1, userId);
            ResultSet resultSet = prestat.executeQuery();
            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public void update(User user){
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement prestat = conn.prepareStatement(Update_user_query);
            prestat.setString(1, user.getUserName());
            prestat.setString(2, user.getEmail());
            prestat.setString(3,this.hashPassword(user.getPassword()));
            prestat.setInt(4, user.getId());
            prestat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public User[] findAll(){
        try (Connection conn = DbUtil.getConnection()){
            User[] users = new User[0];
            PreparedStatement prestat = conn.prepareStatement((Find_all_users_query));
            ResultSet resultSet = prestat.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users = addToArray(user, users);
            }
            return users;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public void delete(int userId){
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement prestat = conn.prepareStatement(Delete_user_query);
            prestat.setInt(1,userId);
            prestat.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
