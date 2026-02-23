package admin;

import BaseController.basecontroller;
import DAO.AccountDAO;
import DAO.UserDAO;
import DAO.TransactionDAO;
import javafx.scene.control.Label;
import models.Account;
import models.User;
import models.Transactions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.util.List;

public class Deposit extends basecontroller {

    @FXML private TextField search_field; // حقل البحث بالإيميل
    @FXML private ListView<Account> user_list; // غيرنا النوع هنا لـ Account عشان نشيل الأوبجيكت نفسه
    @FXML private TextField amount_field; // حقل المبلغ

    private final UserDAO userDAO = new UserDAO();
    private final AccountDAO accountDAO = new AccountDAO();
    private final TransactionDAO transactionDAO = new TransactionDAO();
    @FXML public Label date_lab;

    private User selectedUser;
    @FXML
    private  void initialize(){
        displayCurrentDate(date_lab);
        }

    @FXML
    private void searchUser(ActionEvent event) {
        String email = search_field.getText().trim();
        if (email.isEmpty()) {
            showError("Please enter an email address.");
            return;
        }

        selectedUser = userDAO.getUserByEmail(email);

        if (selectedUser != null) {
            // هنجيب كل حسابات المستخدم ده (سواء Checking أو Saving)
            List<Account> accounts = accountDAO.getAccountsByUserId(selectedUser.getUserId());

            if (accounts.isEmpty()) {
                showError("This user has no accounts!");
                user_list.getItems().clear();
                return;
            }

            // عرض الحسابات في الـ ListView
            ObservableList<Account> accountObservableList = FXCollections.observableArrayList(accounts);
            user_list.setItems(accountObservableList);

            // تخصيص شكل العرض في الـ ListView عشان يظهر النوع والرصيد بدل عنوان الميموري
            user_list.setCellFactory(param -> new javafx.scene.control.ListCell<Account>() {
                @Override
                protected void updateItem(Account item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getAccountType().toUpperCase() + " - " + item.getAccountNumber() + " (Balance: $" + item.getBalance() + ")");
                    }
                }
            });

        } else {
            showError("User not found!");
            user_list.getItems().clear();
        }
    }

    @FXML
    private void onDeposit(ActionEvent event) {
        // هنا بنشوف الأدمن اختار أنهي حساب من القائمة
        Account selectedAccount = user_list.getSelectionModel().getSelectedItem();

        if (selectedAccount == null) {
            showError("Please select an account from the list first!");
            return;
        }

        String amountText = amount_field.getText().trim();
        try {
            double amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                showError("Enter a valid amount.");
                return;
            }

            // تنفيذ الإيداع باستخدام الـ DAO اللي معاك
            double newBalance = selectedAccount.getBalance() + amount;
            boolean success = accountDAO.updateBalance(selectedAccount.getAccountId(), newBalance);

            if (success) {
                // تسجيل المعاملة
                transactionDAO.addTransaction(new Transactions(0, null, selectedAccount.getAccountId(), "deposit", amount, "Admin Deposit"));

                showInfo("Successfully deposited $" + amount + " to " + selectedAccount.getAccountType());
                amount_field.clear();
                searchUser(null); // تحديث القائمة عشان الرصيد الجديد يظهر
            }
        } catch (NumberFormatException e) {
            showError("Invalid amount.");
        }
    }


}