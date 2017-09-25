import entity.Group;
import entity.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingsdwarf on 25.08.17.
 */
public class DBManager {
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SELECT_ALL_GROUPS = "SELECT * FROM groups";
    private static final String INSERT_USER = "INSERT INTO users VALUES(DEFAULT, ?)";
    private static final String INSERT_GROUP = "INSERT INTO groups VALUES(DEFAULT, ?)";
    private static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String GET_GROUP_BY_NAME = "SELECT * FROM groups WHERE name=?";
    private static final String DELETE_GROUP_BY_NAME = "DELETE FROM groups WHERE name=?";
    private static final String CREATE_USERGROUPS = "INSERT INTO users_groups VALUES(?, ?)";
    private String              DELETE_USER_BY_LOGIN = "DELETE FROM users WHERE login=?";

    private DBManager(){

    }
    private static DBManager dbManager;

    public static synchronized DBManager getInstance(){
        if(dbManager == null){
            dbManager = new DBManager();

        }
        return dbManager;
    }

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(Constants.DATABASE_URI, Constants.DATABASE_LOGIN, Constants.DATABASE_PASSWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(AutoCloseable autoCloseable) {
        try {
            autoCloseable.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        User user = null;
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(SELECT_ALL_USERS);
        if(rs == null){
            return users;
        }
        while (rs.next()){
            user = extractUser(rs);
            users.add(user);
        }
        closeConnection(connection);
        return users;
    }

    public List<Group> getAllGroups() throws SQLException {
        List<Group> groups = new ArrayList<>();
        Group group = null;
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(SELECT_ALL_GROUPS);
        if(rs == null){
            return groups;
        }
        while (rs.next()){
            group = extractGroup(rs);
            groups.add(group);
        }
        closeConnection(connection);
        return groups;
    }

    public User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        return user;
    }

    public Group extractGroup(ResultSet rs) throws SQLException {
        Group group = new Group();
        group.setId(rs.getInt("id"));
        group.setName(rs.getString("name"));
        return group;
    }

    public boolean insertUser(User user){
        PreparedStatement statement;
        Connection connection;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(INSERT_USER);
            statement.setString(1, user.getLogin());
            statement.execute();
        } catch (SQLException e) {
            return false;
        }
        closeConnection(connection);
        return true;
    }

    public boolean insertGroup(Group group){
        PreparedStatement statement;
        Connection connection = getConnection();
        try {
            statement = connection.prepareStatement(INSERT_GROUP);
            statement.setString(1, group.getName());
            statement.execute();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public User getUserByLogin(String login){
        PreparedStatement preparedStatement;
        User user = null;
        ResultSet rs = null;
        Connection connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            rs = preparedStatement.executeQuery();
            if(rs != null && rs.next()){
                user = extractUser(rs);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return user;
    }

    public Group getGroupByName(String name){
        PreparedStatement preparedStatement;
        Group group = null;
        ResultSet rs = null;
        Connection connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(GET_GROUP_BY_NAME);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            if(rs != null && rs.next()){
                group = extractGroup(rs);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return group;
    }

    public boolean deleteUserByLogin(String login){
        PreparedStatement preparedStatement;
        Connection connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(DELETE_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.execute();
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteGroupByName(String name){
        PreparedStatement preparedStatement;
        Connection connection = getConnection();
        try {
            preparedStatement = connection.prepareStatement(DELETE_GROUP_BY_NAME);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
        return true;
    }

    public boolean setGroupsForUser(User u, Group... groups){

        Connection connection = getConnection();
        PreparedStatement preparedStatement;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CREATE_USERGROUPS);
            preparedStatement.setInt(1, u.getId());
            for(Group g : groups){
                preparedStatement.setInt(2, g.getId());
                preparedStatement.execute();
            }
            commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
            return false;
        } finally {
            closeConnection(connection);
        }
        return true;
    }

    private void rollback(Connection connection){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void commit(Connection connection){
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
