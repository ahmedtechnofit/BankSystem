package admin;

import Abstract.LoginHandler;
import BaseController.basecontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import user.UserLoginHandler;

public class logincontrollor_base extends basecontroller {

    @FXML
    private Label errorLabel, errorLabel1, errorLabel2;
    @FXML
    private RadioButton adminRadio, userRadio;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    public void d_ad(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();


        if (username.isEmpty() || password.isEmpty()) {
            errorLabel1.setText("Enter email");
            errorLabel2.setText("Enter password");
            return;
        }

        LoginHandler handler;

        if (adminRadio.isSelected()) {
            handler = new AdminLoginHandler();
        } else if (userRadio.isSelected()) {
            handler = new UserLoginHandler();
        } else {
            errorLabel.setText("Select role");
            return;
        }

        if (handler.authenticate(username, password)) {
            handler.openHome(event);
        } else {
            errorLabel.setText("Invalid username or password");
        }
    }

    @FXML
    public void register(ActionEvent event) {
        navigateTo( "register",  event);
    }
}
