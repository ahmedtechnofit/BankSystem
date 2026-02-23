package user;

import DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import models.User;

import BaseController.basecontroller; // لو عندك كلاس أساسي

import javafx.scene.control.Alert;

public class RegisterController_user extends basecontroller {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField fullnameField;

    @FXML
    private PasswordField passwordField;

    private UserDAO userDAO = new UserDAO();

    // زرار التسجيل
    @FXML
    public void ahmed(ActionEvent event) {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String fullname = fullnameField.getText().trim();

        // تقسيم الاسم الكامل
        String firstName = "";
        String lastName = "";
        if (fullname.contains(" ")) {
            int idx = fullname.indexOf(" ");
            firstName = fullname.substring(0, idx);
            lastName = fullname.substring(idx + 1);
        } else {
            firstName = fullname;
        }

        // فحص الحقول الفارغة
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || firstName.isEmpty()) {
            showAlert("Error", "Please fill all fields");
            return;
        }

        // إنشاء كائن مستخدم
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // لاحقًا ممكن تحط تشفير
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole("user"); // افتراضيًا أي مسجل جديد هو user
        user.setStatus("active");

        // تسجيل في قاعدة البيانات
        boolean success = userDAO.createUser(user);

        if (success) {
            showAlert("Success", "User registered successfully");
            // فتح صفحة Login بعد التسجيل
            openWindow("/fxml/client/login_user.fxml", "Login", true, event);
        } else {
            showAlert("Error", "Failed to register user. Username or email might exist.");
        }
    }

    @FXML
    public void login(ActionEvent event) {
        navigateTo("login", event);
    }

    // دالة بسيطة لعرض رسالة
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}








































/*
package user;

import BaseController.basecontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class RegisterController_user  extends basecontroller {

    @FXML
    public void ahmed(ActionEvent event) {
        openWindow("/fxml/client/home_user.fxml",
                "HOME", true, event);
    }
    @FXML
    public void login(ActionEvent event) {
        openWindow("/fxml/client/login_user.fxml",
                "" +
                        "Login", true, event);
    }


}



*/
