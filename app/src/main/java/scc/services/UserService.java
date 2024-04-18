package main.java.scc.services;

import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.core.Cookie;
import main.java.scc.data.User;
import main.java.scc.data.UserDAO;
import main.java.scc.db.HouseRepository;
import main.java.scc.db.QuestionRepository;
import main.java.scc.db.RentalRepository;
import main.java.scc.db.UserRepository;
import main.java.scc.db.comosdb.*;
import main.java.scc.db.hibernate.HibernateUserRepository;
import main.java.scc.srv.MainApplication;
import org.jboss.jandex.Main;

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

    public static User update(@CookieParam("scc:session") Cookie session, String id, User user) {
        UserRepository userDB = MainApplication.DB_LAYER.getUsersRepository();
        UserDAO userDAO = userDB.get(id);

        //Check permission
        AuthService.verifyUser(session, userDAO.getId());

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

    public static Object delete(@CookieParam("scc:session") Cookie session, String id) {
        UserRepository userDB = MainApplication.DB_LAYER.getUsersRepository();
        HouseRepository houseDB = MainApplication.DB_LAYER.getHousesRepository();
        UserDAO userDAO = userDB.get(id);

        //Check permission
        AuthService.verifyUser(session, userDAO.getId());

        //Delete all houses
        houseDB.deleteAllByUser(id);

        RentalRepository rentalDB = MainApplication.DB_LAYER.getRentalsRepository();
        QuestionRepository questionDB = MainApplication.DB_LAYER.getQuestionsRepository();

        //Delete all rentals and messages
        rentalDB.deleteAllByUser(id);
        questionDB.deleteAllByUser(id);

        return userDB.delete(userDAO);
    }

    public static List<User> list() {
        UserRepository userDB = MainApplication.DB_LAYER.getUsersRepository();
        return userDB.list().stream().map(UserDAO::toUser).toList();
    }

}
