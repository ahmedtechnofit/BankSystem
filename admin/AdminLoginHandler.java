package admin;

import Abstract.LoginHandler;
import DAO.UserDAO;
import javafx.event.ActionEvent;
import models.User;
import BaseController.basecontroller;
import utils.Session;

public class AdminLoginHandler extends basecontroller implements LoginHandler {

    @Override
    public boolean authenticate(String username, String password) {
        UserDAO dao = new UserDAO();

        // --- التعديل الجوهري هنا ---
        // بنستخدم Nested Assignment: بنجيب نتيجة اللوجن ونرميها في السيشن فوراً
        Session.loggedUser = dao.login(username, password);

        // بنرجع true لو السيشن اتملى واليوزر فعلاً admin
        return Session.loggedUser != null && Session.loggedUser.getRole().equalsIgnoreCase("admin");
    }

    @Override
    public void openHome(ActionEvent event) {
        navigateTo( "home_admin",  event);
    }
}
