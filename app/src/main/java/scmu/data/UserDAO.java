package main.java.scmu.data;

import java.util.List;

public class UserDAO {

    private String _rid;
    private String _ts;

    private String id;

    private List<UserBoard> boards;

    public UserDAO() {
    }

    public UserDAO(User user) {
        this(user.getId(), user.getBoards());
    }

    public UserDAO(String id, List<UserBoard> boards) {
        super();
        this.id = id;
        this.boards = boards;
    }
    public String get_rid() {
        return _rid;
    }

    public void set_rid(String _rid) {
        this._rid = _rid;
    }

    public String get_ts() {
        return _ts;
    }

    public void set_ts(String _ts) {
        this._ts = _ts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User toUser() {
        return new User(id, boards);
    }

    public List<UserBoard> getBoards() {
        return boards;
    }

    public void setBoards(List<UserBoard> boards) {
        this.boards = boards;
    }

    @Override
    public String toString() {
        return "UserDAO[_rid=" + _rid + ", _ts=" + _ts + ", id=" + id + ", boards=" + boards + "]";
    }

}
