package admin;

import BaseController.basecontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import utils.Session;

public class dashboard_admin extends basecontroller {
    @FXML
    public Label date_lab;

    @FXML public Label WelcomeMessage;

    @FXML
    private void initialize() {
        // Validation: التأكد من وجود جلسة عمل
        if (Session.loggedUser == null) {
            showError("User not logged in!");
            return;
        }

        // Nested Method: نمرر اسم الأدمن من السيشن لميثود التنسيق
        WelcomeMessage.setText(formatAdminWelcome(Session.loggedUser.getFirstName()));
        displayCurrentDate(date_lab);

        loadDashboard();
    }

    private void loadDashboard() {
        // هنا مستقبلاً هتضيف الأكواد اللي بتجيب إحصائيات البنك (إجمالي المستخدمين، الحسابات النشطة، إلخ)
    }

    // ميثود التنسيق الخاصة بالأدمن (Configuration Method)
    private String formatAdminWelcome(String firstName) {
        return "System Administrator: " + firstName + " | Welcome back!";
    }


}