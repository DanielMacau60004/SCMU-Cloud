package main.java.scmu.data;

import java.util.List;

public class User {

    private String id;

    private List<UserBoard> boards;

    public User() {
    }

    public User(String id, List<UserBoard> boards) {
        super();
        this.id = id;
        this.boards = boards;
    }

    public String getId() {
        return id;
    }

    public List<UserBoard> getBoards() {
        return boards;
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", boards=" + boards + "]";
    }

}
