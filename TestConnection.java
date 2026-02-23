
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // لو فيه خطأ هنا، الكنكتور مش موجود
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/DB_banksystemone1",
                    "root",
                    ""
            );
            System.out.println("Connection  ahmed successful!");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

