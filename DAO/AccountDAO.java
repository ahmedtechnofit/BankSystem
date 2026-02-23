package DAO;

import BaseController.BaseDAO;
import models.Account;
import java.sql.*;
import java.util.List;

public class AccountDAO extends BaseDAO<Account> {

    // دي الميثود الوحيدة اللي "بنشرح" فيها إزاي بنبني أوبجكت الأكاونت
    @Override
    protected Account mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Account(
                rs.getInt("account_id"),
                rs.getInt("user_id"),
                rs.getString("account_type"),
                rs.getString("account_number"),
                rs.getDouble("balance"),
                rs.getDouble("transaction_limit"),
                rs.getTimestamp("created_at")
        );
    }

    // جلب كل الحسابات - بقت سطر واحد!
    public List<Account> getAccountsByUserId(int userId) {
        String sql = "SELECT * FROM accounts WHERE user_id=?";
        return executeQuery(sql, userId);
    }

    // تحديث الرصيد - سطر واحد!
    public boolean updateBalance(int accountId, double newBalance) {
        String sql = "UPDATE accounts SET balance=? WHERE account_id=?";
        return executeUpdate(sql, newBalance, accountId);
    }

    // جلب حساب محدد - سطر واحد!
    public Account getAccountByUserAndType(int userId, String type) {
        String sql = "SELECT * FROM accounts WHERE user_id=? AND account_type=?";
        return executeQuerySingle(sql, userId, type);
    }

    // أضف هذه الميثود داخل كلاس AccountDAO
    public int createAccount(Account account) {
        String sql = "INSERT INTO accounts (user_id, account_type, account_number, balance) VALUES (?, ?, ?, ?)";

        // بنستخدم رقم حساب عشوائي بسيط للتيست
        String generatedAccNum = "ACC" + System.currentTimeMillis();

        try (Connection conn = db.DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, account.getUserId());
            stmt.setString(2, account.getAccountType());
            stmt.setString(3, generatedAccNum);
            stmt.setDouble(4, account.getBalance());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // بيرجع الـ ID الجديد عشان تستخدمه في الـ Transaction
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // لو فشل العملية
    }
}
















/*
package DAO;

import db.DBConnection;
import models.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    // جلب كل الحسابات لمستخدم معين
    public List<Account> getAccountsByUserId(int userId) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE user_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                accounts.add(new Account(
                        rs.getInt("account_id"),
                        rs.getInt("user_id"),
                        rs.getString("account_type"), // صححت الاسم هنا
                        rs.getString("account_number"),
                        rs.getDouble("balance"),
                        rs.getDouble("transaction_limit"),
                        rs.getTimestamp("created_at")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    // تحديث الرصيد لحساب معين
    public boolean updateBalance(int accountId, double newBalance) {
        String sql = "UPDATE accounts SET balance=? WHERE account_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newBalance);
            stmt.setInt(2, accountId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // جلب حساب محدد لنوع معين
    public Account getAccountByUserAndType(int userId, String type) {
        String sql = "SELECT * FROM accounts WHERE user_id=? AND account_type=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setString(2, type);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt("account_id"),
                        rs.getInt("user_id"),
                        rs.getString("account_type"),
                        rs.getString("account_number"),
                        rs.getDouble("balance"),
                        rs.getDouble("transaction_limit"),
                        rs.getTimestamp("created_at")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // إنشاء حساب جديد
    public int createAccount(Account account) {
        String sql = "INSERT INTO accounts (user_id, account_type, account_number, balance) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, account.getUserId());
            stmt.setString(2, account.getAccountType());
            stmt.setString(3, "ACC" + System.currentTimeMillis()); // رقم الحساب تلقائي
            stmt.setDouble(4, account.getBalance());

            int rows = stmt.executeUpdate();
            if (rows == 0) return -1;

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1); // إعادة الـ account_id

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
*/
