package user;

import BaseController.basecontroller;
import DAO.TransactionDAO;
import DAO.UserDAO;
import models.Transactions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import utils.Session;
import java.util.List;

public class transactions_user extends basecontroller {

    @FXML
    private ListView<Transactions> transaction_list;

    private final TransactionDAO transactionDAO = new TransactionDAO();
    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void initialize() {
        if (Session.loggedUser == null) {
            showError("User not logged in!");
            return;
        }

        // 1. استخدم الميثود الموحدة من الـ BaseController (دي فيها كل ستايل الألوان)
        // 2. حمل البيانات
        renderTransactionListView(
                transaction_list,
                userDAO,
                Session.loggedUser.getAccountId(),
                null,   // الليميت = null ليعرض الكل
                false );  // isAdmin = false
        loadAllTransactions();
    }

    private void loadAllTransactions() {
        // تأكد أن userId هنا صحيح وليس accountId
        List<Transactions> transactions = transactionDAO.getTransactionsByUser(Session.loggedUser.getUserId());
        if (transactions != null) {
            ObservableList<Transactions> observableList = FXCollections.observableArrayList(transactions);
            transaction_list.setItems(observableList);
        }
    }

}