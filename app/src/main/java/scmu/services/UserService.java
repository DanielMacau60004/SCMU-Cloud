package main.java.scmu.services;

import main.java.scmu.data.User;
import main.java.scmu.data.UserDAO;
import main.java.scmu.db.UserRepository;
import main.java.scmu.srv.MainApplication;

import javax.ws.rs.NotFoundException;
import java.util.LinkedList;
import java.util.List;

public class UserService {

    public static User getOrCreate(User user) {
        //Check for nulls
        if (user.getId() == null)
            throw new NotFoundException();

        UserDAO userDAO = new UserDAO(user);
        UserRepository userDB = MainApplication.DB_LAYER.getUserRepository();

        try {
            userDAO = userDB.get(user.getId());
        } catch (Exception e) {
            userDAO = userDB.create(userDAO);
        }

        return userDAO.toUser();
    }

    public static User update(String id, User user) {
        UserRepository userDB = MainApplication.DB_LAYER.getUserRepository();
        UserDAO userDAO = userDB.get(id);

        userDAO.setId(id);
        userDAO.setBoards(user.getBoards() != null ? user.getBoards() : new LinkedList<>());

        return userDB.put(userDAO).toUser();
    }

    public static User get(String id) {
        UserRepository userDB = MainApplication.DB_LAYER.getUserRepository();
        return userDB.get(id).toUser();
    }

    public static Object delete(String id) {
        UserRepository userDB = MainApplication.DB_LAYER.getUserRepository();
        UserDAO userDAO = userDB.get(id);

        return userDB.delete(userDAO);
    }

    public static List<User> list() {
        UserRepository userDB = MainApplication.DB_LAYER.getUserRepository();
        return userDB.list().stream().map(UserDAO::toUser).toList();
    }

}
