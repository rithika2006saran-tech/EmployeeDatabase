import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "system",        // your Oracle username
                "password"  // your Oracle password
        );
    }
}

