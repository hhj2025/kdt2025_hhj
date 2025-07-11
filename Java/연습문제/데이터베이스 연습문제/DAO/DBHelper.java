package DAOstudent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static DBHelper current;
    private static Connection conn;

    public static DBHelper getInstance() {
        if (current == null) {
            current = new DBHelper();
        }
        return current;
    }

    public Connection open() {
        String url = "jdbc:mysql://localhost:3306/school_db?serverTimezone=Asia/Seoul";
        String user = "root";
        String password = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
        } catch (SQLException e) {
            System.out.println("DB 접속 실패: " + e.getMessage());
        }

        return conn;
    }

    public void close() {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        conn = null;
        current = null;
    }
}

