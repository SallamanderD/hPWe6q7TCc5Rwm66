package entity;

/**
 * Created by kingsdwarf on 25.08.17.
 */
public class User {
    private int id;
    private String login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }

    public static User createUser(String login){
        User u = new User();
        u.setLogin(login);
        return u;
    }
}
