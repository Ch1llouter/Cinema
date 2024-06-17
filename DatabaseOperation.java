import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseOperation {

    static final String DB_URL = "jdbc:mysql://localhost/movieticketbookingsystem";
    static final String USER = "root";
    static final String PASS = "1234"; // This should be replaced with a secure password

    public Connection connectToDatabase() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public int executeUpdate(String sql, Object[] values) throws SQLException {
    int rowsAffected = 0;
    try (Connection conn = connectToDatabase();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        for (int i = 0; i < values.length; i++) {
            ps.setObject(i + 1, values[i]);
        }
        rowsAffected = ps.executeUpdate();
    }
    return rowsAffected;
}

}
