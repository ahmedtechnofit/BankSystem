package admin;

import BaseController.basecontroller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class homecontroller_admin extends basecontroller {
    @FXML public Button dashboardBtn;
    @FXML public Button accountsBtn;
    @FXML public Button DepositBut;
    @FXML public Button logoutBtn;
    @FXML public Button TransactionBut;

    @FXML
    private StackPane contentArea; // المنطقة اللي هيظهر فيها المحتوى المتغير

    @FXML
    public void initialize() {
        applyTransitions(dashboardBtn); // افترضت أن هذه أسماء الـ fx:id
        applyTransitions(accountsBtn);
        applyTransitions(DepositBut);
        applyTransitions(logoutBtn);
        applyTransitions(TransactionBut);
        // تحميل صفحة الداش بورد كصفحة افتراضية عند فتح هوم الأدمن
        loadView("/fxml/admin/dashboard_admin.fxml");

    }
    private void setActiveButton(Button selectedBtn) {
        // مسح الـ CSS الخاص بالزر النشط من كل الأزرار أولاً
        dashboardBtn.getStyleClass().remove("sidebar-btn-active");
        accountsBtn.getStyleClass().remove("sidebar-btn-active");
        TransactionBut.getStyleClass().remove("sidebar-btn-active");
        DepositBut.getStyleClass().remove("sidebar-btn-active");
        logoutBtn.getStyleClass().remove("sidebar-btn-active");



        // إضافته للزر الذي تم ضغطه فقط
        selectedBtn.getStyleClass().add("sidebar-btn-active");
    }

    // الميثود المسؤولة عن تغيير المحتوى في الـ StackPane
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
    public void h_c(ActionEvent event) {
        loadView("/fxml/admin/createaccount.fxml");
        setActiveButton((Button)event.getSource()); // تمييز الزر
    }

    @FXML
    public void h_d(ActionEvent event) {
        loadView("/fxml/admin/dashboard_admin.fxml");
        setActiveButton((Button)event.getSource()); // تمييز الزر
    }

    @FXML
    public void tra(ActionEvent event) {
        loadView("/fxml/admin/transactions_admin.fxml");
        setActiveButton((Button)event.getSource()); // تمييز الزر
    }

    @FXML
    public void hh_dd(ActionEvent event) {
        loadView("/fxml/admin/Deposit_admin.fxml");
        setActiveButton((Button)event.getSource()); // تمييز الزر
    }

    @FXML
    public void log(ActionEvent event) {
        // تسجيل الخروج يغلق النافذة بالكامل ويعود للـ Login
        navigateTo("login", event);
        setActiveButton((Button)event.getSource()); // تمييز الزر
    }
}