package user;

import BaseController.basecontroller;
import DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.Session;
import javafx.scene.image.ImageView;

public class profile_user extends basecontroller {
@FXML public ImageView profileImageView;

@FXML public TextField nameField,emailField;
@FXML public PasswordField passwordField;

@FXML public Label statusLabel;

    @FXML
    public void handleSaveChanges(ActionEvent event) {
        if (Session.loggedUser != null) {
            // 1. تحديث بيانات الكائن في الذاكرة (Session) من التكست فيلدز
            Session.loggedUser.setUsername(nameField.getText());
            Session.loggedUser.setEmail(emailField.getText());
            Session.loggedUser.setPassword(passwordField.getText());

            // 2. استدعاء الـ DAO للحفظ في MySQL
            UserDAO dao = new UserDAO();
            boolean isUpdated = dao.updateUser(Session.loggedUser);

            if (isUpdated) {
                showInfo("Profile updated successfully in Database!");
            } else {
                showError("Failed to update profile. Please check your connection.");
            }
        }
    }
@FXML
    public void handleLogout(ActionEvent event){
    navigateTo("login", event);
    Session.loggedUser = null; // مسح الجلسة
}
@FXML
    public void initialize() {
    loadUserDataToUI( nameField,  emailField,  passwordField,  statusLabel,  profileImageView) ;

}
    @FXML
    void handleUploadPhoto(ActionEvent event) {
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Select Profile Picture");

        // تحديد أنواع الملفات المسموح بها
        fileChooser.getExtensionFilters().addAll(
                new javafx.stage.FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        // فتح نافذة الاختيار
        java.io.File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            // 1. تحديث الصورة في الواجهة فوراً
            javafx.scene.image.Image image = new javafx.scene.image.Image(selectedFile.toURI().toString());
            profileImageView.setImage(image);

            // 2. حفظ المسار في كائن المستخدم (السيشن)
            if (utils.Session.loggedUser != null) {
                utils.Session.loggedUser.setImage(selectedFile.getAbsolutePath());
            }
        }
    }




}
