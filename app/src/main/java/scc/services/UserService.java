package main.java.scc.services;

import main.java.scc.data.User;
import main.java.scc.data.UserDAO;
import main.java.scc.db.UserRepository;
import main.java.scc.srv.MainApplication;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

public class UserService {

    public static User create(User user) {
        UserDAO userDAO = new UserDAO();

        //Generate an automatic id
        userDAO.setId(UUID.randomUUID().toString());

        //Check for nulls
        if (user.getName() == null || user.getPwd() == null)
            throw new NotFoundException();

        // Fields to insert
        userDAO.setName(user.getName());
        userDAO.setPwd(user.getPwd());

        UserRepository userDB = MainApplication.DB_LAYER.getUsersRepository();
        return userDB.create(userDAO).toUser();
    }

    public static User update(String id, User user) {
        UserRepository userDB = MainApplication.DB_LAYER.getUsersRepository();
        UserDAO userDAO = userDB.get(id);

        // Fields to update
        if (user.getName() != null)
            userDAO.setName(user.getName());
        if (user.getPwd() != null)
            userDAO.setPwd(user.getPwd());

        return userDB.put(userDAO).toUser();
    }

    public static User get(String id) {
        UserRepository userDB = MainApplication.DB_LAYER.getUsersRepository();
        return userDB.get(id).toUser();
    }

    public static Object delete(String id) {
        UserRepository userDB = MainApplication.DB_LAYER.getUsersRepository();
        UserDAO userDAO = userDB.get(id);

        return userDB.delete(userDAO);
    }

    public static List<User> list() {
        UserRepository userDB = MainApplication.DB_LAYER.getUsersRepository();
        return userDB.list().stream().map(UserDAO::toUser).toList();
    }

}
