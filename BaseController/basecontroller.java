package BaseController;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.User;
import models.Transactions;
import DAO.UserDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.Session;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;

public class basecontroller {
    // داخل كلاس basecontroller
    public void applyTransitions(Button btn) {
        // حركة الانزلاق لليمين عند دخول الماوس
        btn.setOnMouseEntered(e -> {
            javafx.animation.TranslateTransition transition = new javafx.animation.TranslateTransition(javafx.util.Duration.millis(200), btn);
            transition.setToX(10);
            transition.play();
        });

        // العودة لمكانه عند خروج الماوس
        btn.setOnMouseExited(e -> {
            javafx.animation.TranslateTransition transition = new javafx.animation.TranslateTransition(javafx.util.Duration.millis(200), btn);
            transition.setToX(0);
            transition.play();
        });
    }


    /*
        protected void saveActivityToFile(String activityType, String details) {
            User user = getLoggedUser();
            if (user == null) return; // تأمين لو مفيش مستخدم مسجل

            try {
                // 1. إنشاء المجلد الرئيسي للبيانات
                File logDir = new File("System_Logs");
                if (!logDir.exists()) logDir.mkdir();

                // 2. إنشاء ملف خاص لكل مستخدم (بناءً على الـ ID أو الـ Username)
                File userFile = new File(logDir, "user_" + user.getId() + "_history.txt");

                // 3. الكتابة في الملف (مع تفعيل الـ Append لإضافة البيانات)
                try (FileWriter writer = new FileWriter(userFile, true)) {
                    String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    writer.write(String.format("[%s] [%s] : %s\n", time, activityType.toUpperCase(), details));
                }

                System.out.println("✅ Activity Logged to File.");
            } catch (IOException e) {
                System.err.println("❌ Failed to write to log file: " + e.getMessage());
            }
        }
    *///لاضافه تقارير للمستخدم ف ملف text
// أضف هذه الميثود داخل كلاس basecontroller
    protected void loadUserDataToUI(TextField nameField, TextField emailField, PasswordField passwordField, Label statusLabel, ImageView profileImageView) {
        // جلب المستخدم من السيشين
        User user = Session.loggedUser;

        if (user != null) {
            if (nameField != null) nameField.setText(user.getUsername());
            if (emailField != null) emailField.setText(user.getEmail());
            if (passwordField != null) passwordField.setText(user.getPassword());

            if (statusLabel != null) {
                statusLabel.setText("Active (" + user.getRole().toUpperCase() + ")");
            }

            // التحقق من وجود الصورة (حتى لو العمود مش موجود في الداتابيز حالياً)
            if (profileImageView != null) {
                try {
                    // هنا نضع شرط: لو الموديل فيه مسار صورة وصحيح اعرضه، غير كدة اعرض صورة افتراضية
                    if (user.getImage_Path() != null && !user.getImage_Path().isEmpty()) {
                        File file = new File(user.getImage_Path());
                        if (file.exists()) {
                            profileImageView.setImage(new Image(file.toURI().toString()));
                        }
                    } else {
                        // تحميل صورة افتراضية من مجلد assets الخاص بمشروعك
                        // profileImageView.setImage(new Image(getClass().getResourceAsStream("/assets/default-user.png")));
                    }
                } catch (Exception e) {
                    System.out.println("Image load error: " + e.getMessage());
                }
            }
        } else {
            showError("No logged-in user found!");
        }
    }

    protected void displayCurrentDate(Label label) {
        if (label != null) {
            String formattedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            label.setText("Date: " + formattedDate);
        }
    }

    private User loggedUser;

    protected void setLoggedUser(User user) {
        this.loggedUser = user;
    }

    protected User getLoggedUser() {
        return loggedUser;
    }

    protected void navigateTo(String pageName, ActionEvent event) {
        String path = "";
        String title = "";

        switch (pageName.toLowerCase()) {
            case "dashboard":
                path = "/fxml/client/dashboard_user.fxml";
                title = "User Dashboard";
                break;
            case "transactions":
                path = "/fxml/client/transactions_user.fxml";
                title = "My Transactions";
                break;
            case "login":
                path = "/fxml/admin/login_admin.fxml";
                title = "Bank Login";
                break;
            case "register":
                path = "/fxml/client/register_user.fxml";
                title = "Register";
                break;
            case "accounts":
                path = "/fxml/client/accounts_user.fxml";
                title = "Accounts";
                break;
            case "profile":
                path = "/fxml/client/profile_user.fxml";
                title = "Profile";
                break;
            case "transactions_admin":
                path = "/fxml/admin/transactions_admin.fxml";
                title = "Transactions";
                break;
            case "home_admin":
                path = "/fxml/admin/home_admin.fxml";
                title = "Home_admin";
                break;
            case "home_user":
                path = "/fxml/client/home_user.fxml";
                title = "Home_user";
                break;
            case "dashboard_admin":
                path = "/fxml/admin/dashboard_admin.fxml";
                title = "Dashboard_admin";
                break;
            case "createaccount_admin":
                path = "/fxml/admin/createaccount.fxml";
                title = "CreateAccount";
                break;
            case "deposit":
                path = "/fxml/admin/Deposit_admin.fxml";
                title = "Deposit";
                break;
            default:
                System.out.println("Page not found: " + pageName);
                return;
        }
        openWindow(path, title, true, event);
    }

    // --- ميثود التنقل بين الصفحات ---
    protected void openWindow(String fxmlPath, String title, boolean closeCurrent, ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();

            if (closeCurrent && event != null) {
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // --- ميثود عرض الرسائل ---
    protected void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    protected void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // --- الميثود السحرية: توحيد شكل قائمة العمليات في كل البرنامج ---
    protected void renderTransactionListView(ListView<Transactions> listView, UserDAO userDAO, Integer myAccountId, Integer limit, boolean isAdmin) {
        listView.setCellFactory(param -> new ListCell<Transactions>() {
            @Override
            protected void updateItem(Transactions item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || (limit != null && getIndex() >= limit)) {
                    setGraphic(null);
                    setText(null); // تأكد من مسح النص
                    setStyle("-fx-background-color: transparent;");
                } else {
                    HBox hBox = new HBox(15);
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    hBox.setPadding(new Insets(10, 15, 10, 15));
                    hBox.setStyle("-fx-background-color: white; -fx-background-radius: 12; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 0);");

                    VBox detailsVBox = new VBox(2);
                    String displayName = "Unknown";
                    Color statusColor = Color.BLACK;
                    String sign = "";

                    try {
                        if (isAdmin) {
                            // للأدمن: جلب اسم المرسل والمستقبل
                            String sender = userDAO.getUserNameAndAccByAccountId(item.getFromAccountId());
                            String receiver = userDAO.getUserNameAndAccByAccountId(item.getToAccountId());
                            displayName = (sender != null ? sender.split(" ")[0] : "System")
                                    + " ➔ " +
                                    (receiver != null ? receiver.split(" ")[0] : "System");
                            statusColor = Color.web("#0598ff"); // لون أزرق للأدمن
                        } else {
                            // لليوزر: تحديد هل هو مرسل أم مستقبل
                            if (item.getFromAccountId().equals(myAccountId)) {
                                String receiver = userDAO.getUserNameAndAccByAccountId(item.getToAccountId());
                                displayName = "Sent to: " + (receiver != null ? receiver.split(" ")[0] : "External");
                                sign = "- ";
                                statusColor = Color.web("#EA5455"); // أحمر
                            } else {
                                String sender = userDAO.getUserNameAndAccByAccountId(item.getFromAccountId());
                                displayName = "Received from: " + (sender != null ? sender.split(" ")[0] : "System");
                                sign = "+ ";
                                statusColor = Color.web("#28C76F"); // أخضر
                            }
                        }
                    } catch (Exception e) {
                        displayName = "Transaction #" + item.getTransactionId();
                    }

                    Label nameLabel = new Label(displayName);
                    nameLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
                    nameLabel.setTextFill(statusColor);

                    Label dateLabel = new Label(item.getTransactionDate().toString());
                    dateLabel.setFont(Font.font("System", 11));
                    dateLabel.setTextFill(Color.GRAY);

                    detailsVBox.getChildren().addAll(nameLabel, dateLabel);

                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);

                    Label amountLabel = new Label(sign + "$" + item.getAmount());
                    amountLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
                    amountLabel.setTextFill(statusColor);

                    hBox.getChildren().addAll(detailsVBox, spacer, amountLabel);

                    setGraphic(hBox);
                    setStyle("-fx-background-color: transparent; -fx-padding: 5;");
                }
            }
        });
    }}