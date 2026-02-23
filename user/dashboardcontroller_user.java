package user;

import BaseController.basecontroller;
import DAO.AccountDAO;
import DAO.TransactionDAO;
import DAO.UserDAO;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.Account;
import models.Transactions;
import models.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.Session;
import java.util.List;

public class dashboardcontroller_user extends basecontroller {

    @FXML public Label checkingaccount_lab, savingsaccount_lab, income_lab, expances_lab;

    // ✅ التعديل 1: تغيير النوع من String إلى Transactions ليتوافق مع ميثود الرندر
    @FXML public ListView<Transactions> transaction_list;

    @FXML public TextField email_lab, ammount_lab;
    @FXML public TextArea message_lab;
    @FXML public Label WelcomeMessage;
    @FXML public Label date_lab;

    private Account checkingAccount;
    private final AccountDAO accountDAO = new AccountDAO();
    private final TransactionDAO transactionDAO = new TransactionDAO();
    private final UserDAO userDAO = new UserDAO();

    @FXML
    public void initialize() {
        if (Session.loggedUser == null) return;

        WelcomeMessage.setText("Welcome back, " + Session.loggedUser.getFirstName() + "!");
        displayCurrentDate(date_lab);

        loadDashboard();
    }

    private void loadDashboard() {
        User currentUser = Session.loggedUser;
        List<Account> accounts = accountDAO.getAccountsByUserId(currentUser.getUserId());

        // تصنيف الحسابات وتحديث الواجهة
        for (Account acc : accounts) {
            if (acc.getAccountType().equalsIgnoreCase("checking")) {
                checkingAccount = acc;
                checkingaccount_lab.setText("$ " + acc.getBalance());
            } else if (acc.getAccountType().equalsIgnoreCase("saving")) {
                savingsaccount_lab.setText("$ " + acc.getBalance());
            }
        }

        // ✅ التعديل 2: جلب البيانات واستدعاء ميثود الرندر السحرية
        List<Transactions> lastTrans = transactionDAO.getLastTransactionsByUser(currentUser.getUserId(), 10); // نجلب كمية كافية

        // تحويل القائمة لـ ObservableList
        transaction_list.setItems(FXCollections.observableArrayList(lastTrans));

        // ✅ التعديل 3: استخدام ميثود الرندر من الـ BaseController مع ليميت 10
        renderTransactionListView(transaction_list, userDAO, currentUser.getAccountId(), 10, false);

        // حساب الدخل والمصاريف (Income/Expenses)
        calculateSummary(lastTrans);
    }

    private void calculateSummary(List<Transactions> transactions) {
        double income = 0, expenses = 0;
        for (Transactions t : transactions) {
            if (t.getType().equalsIgnoreCase("deposit")) {
                income += t.getAmount();
            } else if (t.getType().equalsIgnoreCase("withdrawal")) {
                expenses += t.getAmount();
            } else if (t.getType().equalsIgnoreCase("transfer")) {
                if (t.getFromAccountId().equals(Session.loggedUser.getAccountId())) expenses += t.getAmount();
                else income += t.getAmount();
            }
        }
        income_lab.setText("+ $ " + income);
        expances_lab.setText("- $ " + expenses);
    }


    @FXML
    public void sendMoney(ActionEvent event) {

        if (checkingAccount == null) {
            showError("No checking account found!");
            return;
        }

        User currentUser = Session.loggedUser;
        String email = email_lab.getText().trim().toLowerCase();
        String message = message_lab.getText().trim();
        double amount;

        try {
            amount = Double.parseDouble(ammount_lab.getText().trim());
        } catch (NumberFormatException e) {
            showError("Please enter a valid amount!");
            return;
        }

        if (amount <= 0) {
            showError("Amount must be greater than zero!");
            return;
        }

        User receiver = new UserDAO().getUserByEmail(email);
        if (receiver == null) {
            showError("No user found with this email!");
            return;
        }

        if (receiver.getUserId() == currentUser.getUserId()) {
            showError("You cannot transfer money to yourself!");
            return;
        }

        Account receiverAccount = null;
        for (Account acc : accountDAO.getAccountsByUserId(receiver.getUserId())) {
            if ("checking".equalsIgnoreCase(acc.getAccountType().trim())) {
                receiverAccount = acc;
                break;
            }
        }

        if (receiverAccount == null) {
            showError("Receiver has no checking account!");
            return;
        }

        if (checkingAccount.getBalance() < amount) {
            showError("Not enough balance!");
            return;
        }

        double newSenderBalance = checkingAccount.getBalance() - amount;
        double newReceiverBalance = receiverAccount.getBalance() + amount;

        if (!accountDAO.updateBalance(checkingAccount.getAccountId(), newSenderBalance)
                || !accountDAO.updateBalance(receiverAccount.getAccountId(), newReceiverBalance)) {

            showError("Failed to update accounts!");
            return;
        }

        checkingAccount.setBalance(newSenderBalance);
        receiverAccount.setBalance(newReceiverBalance);

        Transactions transfer = new Transactions(
                checkingAccount.getAccountId(),
                checkingAccount.getAccountId(),
                receiverAccount.getAccountId(),
                "transfer",
                amount,
                message
        );

        transactionDAO.addTransaction(transfer);

        loadDashboard();
        showInfo("Money sent successfully!");
    }
}