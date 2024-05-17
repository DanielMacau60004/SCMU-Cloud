package main.java.scmu.data;

public class UserBoard {

    private String board;
    private String name;
    private boolean notifications;

    public UserBoard() {}

    public UserBoard(String board, String name, boolean notifications) {
        this.board = board;
        this.name = name;
        this.notifications = notifications;
    }

    public String getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public boolean isNotifications() {
        return notifications;
    }

}
