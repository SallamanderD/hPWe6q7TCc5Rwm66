package entity;

/**
 * Created by kingsdwarf on 25.08.17.
 */
public class Group {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static Group createGroup(String name){
        Group g = new Group();
        g.setName(name);
        return g;
    }
}
