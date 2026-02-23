package admin;

import BaseController.basecontroller;
import DAO.AccountDAO;
import DAO.TransactionDAO;
import DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import models.Account;
import models.Transactions;
import models.User;
import utils.Session;

public class createaccount_admin extends basecontroller {

    @FXML
    private TextField payeeField;

    @FXML
    private CheckBox addCheckingCheckbox;
    @FXML
    private TextField checkingBalanceField;

    @FXML
    private CheckBox addSavingCheckbox;
    @FXML
    private TextField savingBalanceField;

    private final UserDAO userDAO = new UserDAO();
    private final AccountDAO accountDAO = new AccountDAO();
    private final TransactionDAO transactionDAO = new TransactionDAO();

    @FXML
    public void createAccount(ActionEvent event) {

        User user = userDAO.getUserByEmail(payeeField.getText().trim());

        if (user == null) {
            showAlert("Error", "User not found");
            return;
        }

        boolean createdAny = false;

        // Checking
        if (addCheckingCheckbox.isSelected()) {

            if (accountDAO.getAccountByUserAndType(user.getUserId(), "checking") != null) {
                showAlert("Error", "Checking account already exists");
                return;
            }

            double balance = Double.parseDouble(checkingBalanceField.getText().trim());

            Account acc = new Account(0, user.getUserId(), "checking", balance);

            int accountId = accountDAO.createAccount(acc);

            if (accountId == -1) {
                showAlert("Error", "Failed to create checking account");
                return;
            }

            transactionDAO.addTransaction(new Transactions(
                    accountId,
                    null,
                    accountId,
                    "deposit",
                    balance,
                    "Initial checking deposit",
                    null
            ));

            createdAny = true;
        }

        // Saving
        if (addSavingCheckbox.isSelected()) {

            if (savingBalanceField.getText().trim().isEmpty()) {
                showAlert("Error", "Enter saving balance");
                return;
            }

            if (accountDAO.getAccountByUserAndType(user.getUserId(), "saving") != null) {
                showAlert("Error", "Saving account already exists");
                return;
            }

            double balance = Double.parseDouble(savingBalanceField.getText().trim());

            Account acc = new Account(0, user.getUserId(), "saving", balance);

            int accountId = accountDAO.createAccount(acc);

            if (accountId == -1) {
                showAlert("Error", "Failed to create saving account");
                return;
            }

            transactionDAO.addTransaction(new Transactions(
                    accountId,
                    null,
                    accountId,
                    "deposit",
                    balance,
                    "Initial saving deposit",
                    null
            ));

            createdAny = true;
        }

        if (!createdAny) {
            showAlert("Error", "Select at least one account");
            return;
        }

        showAlert("Success", "Accounts created successfully");
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.show();
    }

}
