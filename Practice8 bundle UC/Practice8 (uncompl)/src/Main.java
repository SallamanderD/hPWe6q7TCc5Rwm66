import entity.Group;
import entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by kingsdwarf on 25.08.17.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        DBManager dbManager = DBManager.getInstance();
        List<Group> groups = dbManager.getAllGroups();
        Group[] gs = new Group[groups.size()];
        for(int i = 0; i < groups.size(); i++){
            gs[i] = groups.get(i);
        }
        User u = dbManager.getUserByLogin("asd");
        dbManager.setGroupsForUser(u, gs);
    }
}