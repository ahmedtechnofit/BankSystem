package user;

import Abstract.LoginHandler;
import DAO.UserDAO;
import javafx.event.ActionEvent;
import models.User;
import utils.Session;
import BaseController.basecontroller;

public class UserLoginHandler extends basecontroller implements LoginHandler {

    @Override
    public boolean authenticate(String username, String password) {
        UserDAO dao = new UserDAO();
        User userFromDB = dao.login(username, password);

        if (userFromDB != null && userFromDB.getRole().equals("user")) {
            // ✅ تعيين المستخدم في الـ Session
            Session.loggedUser = userFromDB;
            return true;
        }
        return false;
    }

    @Override
    public void openHome(ActionEvent event) {
        navigateTo( "home_user", event);
    }
}
