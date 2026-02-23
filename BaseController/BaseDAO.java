package BaseController;

import db.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {

    // ميثود مجردة (Abstract) كل DAO لازم ينفذها بطريقته لتحويل الـ ResultSet لأوبجكت
    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;

    // 1. ميثود عامة لجلب قائمة (List) من البيانات
    protected List<T> executeQuery(String sql, Object... params) {
        List<T> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. ميثود عامة لجلب أوبجكت واحد فقط
    protected T executeQuerySingle(String sql, Object... params) {
        List<T> results = executeQuery(sql, params);
        return results.isEmpty() ? null : results.get(0);
    }

    // 3. ميثود عامة للتحديث (Insert, Update, Delete)
    protected boolean executeUpdate(String sql, Object... params) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}