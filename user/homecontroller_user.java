package user;

import BaseController.basecontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import utils.Session;
import java.io.IOException;

public class homecontroller_user extends basecontroller {
    @FXML public Button dashboardBtn;
    @FXML public Button accountsBtn;
    @FXML public Button ProfileBut;
    @FXML public Button logoutBtn;
    @FXML public Button TransactionBut;

    @FXML
    private StackPane contentArea; // المساحة اللي على اليمين

    @FXML
    public void initialize() {
        applyTransitions(dashboardBtn); // افترضت أن هذه أسماء الـ fx:id
        applyTransitions(accountsBtn);
        applyTransitions(ProfileBut);
        applyTransitions(logoutBtn);
        applyTransitions(TransactionBut);
        // أول ما الصفحة تفتح، يحمل الداش بورد تلقائياً في الجزء اليمين
        loadView("/fxml/client/dashboard_user.fxml");

    }
    private void setActiveButton(Button selectedBtn) {
        // مسح الـ CSS الخاص بالزر النشط من كل الأزرار أولاً
        dashboardBtn.getStyleClass().remove("sidebar-btn-active");
        accountsBtn.getStyleClass().remove("sidebar-btn-active");
        TransactionBut.getStyleClass().remove("sidebar-btn-active");
        ProfileBut.getStyleClass().remove("sidebar-btn-active");
        logoutBtn.getStyleClass().remove("sidebar-btn-active");



        // إضافته للزر الذي تم ضغطه فقط
        selectedBtn.getStyleClass().add("sidebar-btn-active");
    }


    // ميثود مسؤولة عن تبديل المحتوى الداخلي فقط
    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent node = loader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
            showError("Error loading view: " + fxmlPath);
        }
    }

    @FXML
    public void D(ActionEvent event) {
        loadView("/fxml/client/dashboard_user.fxml");
    }

    @FXML
    public void home_acc(ActionEvent event) {
        loadView("/fxml/client/accounts_user.fxml");
    }

    @FXML
    public void home_tr(ActionEvent event) {
        loadView("/fxml/client/transactions_user.fxml");
    }

    @FXML
    public void pro(ActionEvent event) {
        loadView("/fxml/client/profile_user.fxml");
    }

    @FXML
    public void home_log(ActionEvent event) {
        Session.loggedUser = null;
        navigateTo("login", event); // تسجيل الخروج الوحيد اللي بيقفل النافذة بالكامل
    }
}











/*
package user;

import BaseController.basecontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import utils.Session;

public class homecontroller_user extends basecontroller {

    @FXML
    public void D(ActionEvent event) {
        if (Session.loggedUser == null) {
            showError("User not logged in!");
            return;
        }
        navigateTo( "dashboard", event);
    }

    @FXML
    public void home_acc(ActionEvent event) {
        navigateTo("accounts", event);
    }

    @FXML
    public void home_tr(ActionEvent event) {
        navigateTo("transactions", event);
    }
    @FXML
    public void pro(ActionEvent event) {
        navigateTo("profile", event);
    }

    @FXML
    public void home_log(ActionEvent event) {
        // مسح الجلسة عند تسجيل الخروج
        Session.loggedUser = null;
        navigateTo("login",  event);
    }
}
*/
