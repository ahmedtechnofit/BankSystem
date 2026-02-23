package admin;

import BaseController.basecontroller;
import DAO.TransactionDAO;
import DAO.UserDAO;
import models.Transactions;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.util.List;

public class transactions_admin extends basecontroller {

    // ✅ التعديل الأساسي: تغيير النوع ليتوافق مع ميثود الرندر
    @FXML
    private ListView<Transactions> transaction_list;

    private final TransactionDAO transactionDAO = new TransactionDAO();
    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void initialize() {
        // 1. تحميل البيانات كأوبجكت وليس كنصوص
        loadAllTransactions();

        // 2. استدعاء ميثود الرندر لتقوم بالتلوين وعرض الأسماء
        renderTransactionListView(
                transaction_list,
                userDAO,
                null, // الأدمن يرى كل الحسابات
                null, // عرض الكل بدون ليميت
                true  // وضع الأدمن (إظهار الراسل والمستقبل)
        );
    }

    private void loadAllTransactions() {
        List<Transactions> transactions = transactionDAO.getAllTransactions();
        if (transactions != null) {
            // ✅ نضع القائمة كـ Transactions مباشرة
            transaction_list.setItems(FXCollections.observableArrayList(transactions));
        }
    }
}