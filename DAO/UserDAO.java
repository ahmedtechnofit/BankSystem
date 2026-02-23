package DAO;

import BaseController.BaseDAO;
import models.User;
import java.sql.*;
import java.util.List;

public class UserDAO extends BaseDAO<User> {
    public boolean updateUser(User user) {
        // استعلام SQL لتحديث البيانات بناءً على الـ ID
        String sql = "UPDATE users SET username = ?, email = ?, password = ?, image_path = ? WHERE user_id = ?";

        // تمرير القيم بالترتيب الصحيح لميثود executeUpdate الموجودة في الـ Base
        return executeUpdate(sql,
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getImage_Path(), // المسار الذي تم حفظه عند اختيار الصورة
                user.getUserId()      // الشرط لضمان تحديث الشخص الصحيح
        );
    }

    // 1. شرحنا للجافا إزاي تحول صف جدول الـ users لأوبجكت User
    @Override
    protected User mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("user_id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("role"),
                rs.getString("status"),
                (Integer) rs.getObject("account_id"),
                rs.getString("image_path")


        );
    }

    // 2. ميثود تسجيل الدخول - سطر واحد تنفيذ SQL!
    public User login(String email, String password) {
        String sql = "SELECT u.*, a.account_id FROM users u " +
                "LEFT JOIN accounts a ON u.user_id = a.user_id " +
                "WHERE u.email=? AND u.password=? AND u.status='active'";

        return executeQuerySingle(sql, email, password);
    }

    // 3. البحث عن مستخدم بالإيميل
    public User getUserByEmail(String email) {
        String sql = "SELECT u.*, a.account_id FROM users u " +
                "LEFT JOIN accounts a ON u.user_id = a.user_id " +
                "WHERE u.email=?";

        return executeQuerySingle(sql, email);
    }

    // 4. إنشاء مستخدم جديد
    public boolean createUser(User user) {
        String sql = "INSERT INTO users (username, first_name, last_name, email, password, role, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        return executeUpdate(sql,
                user.getUsername(), user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getPassword(), user.getRole(), user.getStatus());
    }

    // أضف هذه الميثود داخل كلاس UserDAO
    public String getUserNameAndAccByAccountId(int accountId) {
        String sql = "SELECT u.first_name, u.last_name, a.account_number FROM users u " +
                "JOIN accounts a ON u.user_id = a.user_id WHERE a.account_id = ?";

        // بما أن الميثود دي بترجع String مش أوبجكت User، هنستخدم ميثود خاصة أو نكتبها يدوي بداخلها
        try (Connection conn = db.DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("first_name") + " " + rs.getString("last_name") +
                        " (" + rs.getString("account_number") + ")";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Unknown Account (N/A)";
    }
}