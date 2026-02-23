package DAO;

import BaseController.BaseDAO;
import models.Transactions;
import java.sql.*;
import java.util.List;

public class TransactionDAO extends BaseDAO<Transactions> {
    // أضف هذه الميثود داخل كلاس TransactionDAO
    public List<Transactions> getAllTransactions() {
        String sql = "SELECT * FROM transactions ORDER BY transaction_date DESC";
        return executeQuery(sql); // هنا بننادي الـ BaseDAO بدون بارامترات لأننا عايزين "الكل"
    }

    // 1. شرح كيفية تحويل صف المعاملات لأوبجكت (البصمة الخاصة بالجدول)
    @Override
    protected Transactions mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Transactions(
                rs.getInt("transaction_id"),
                rs.getInt("account_id"),
                (Integer) rs.getObject("from_account_id"),
                (Integer) rs.getObject("to_account_id"),
                rs.getString("type"),
                rs.getDouble("amount"),
                rs.getString("description"),
                rs.getTimestamp("transaction_date")
        );
    }

    // 2. إضافة عملية جديدة - سطر واحد!
    public boolean addTransaction(Transactions t) {
        String sql = "INSERT INTO transactions (account_id, from_account_id, to_account_id, type, amount, description) VALUES (?, ?, ?, ?, ?, ?)";
        return executeUpdate(sql,
                t.getAccountId(), t.getFromAccountId(), t.getToAccountId(),
                t.getType(), t.getAmount(), t.getDescription());
    }

    // 3. جلب كل عمليات المستخدم - سطر واحد باستخدام الـ Join!
    public List<Transactions> getTransactionsByUser(int userId) {
        String sql = "SELECT t.* FROM transactions t " +
                "JOIN accounts a ON t.account_id = a.account_id " +
                "WHERE a.user_id = ? ORDER BY t.transaction_date DESC";
        return executeQuery(sql, userId);
    }

    // 4. آخر N عمليات (للـ Dashboard) - سطر واحد!
    public List<Transactions> getLastTransactionsByUser(int userId, int limit) {
        String sql = "SELECT t.* FROM transactions t " +
                "JOIN accounts a ON t.account_id = a.account_id " +
                "WHERE a.user_id = ? ORDER BY t.transaction_date DESC LIMIT ?";
        return executeQuery(sql, userId, limit);
    }
}