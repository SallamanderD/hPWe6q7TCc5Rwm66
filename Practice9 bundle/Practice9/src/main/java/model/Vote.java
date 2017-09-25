package model;

import java.util.List;

public class Vote {
    List<String> logins;
    String name;
    int count;

    public Vote(List<String> logins, String name, int count) {
        this.logins = logins;
        this.name = name;
        this.count = count;
    }

    public List<String> getLogins() {
        return logins;
    }

    public void setLogins(List<String> logins) {
        this.logins = logins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

