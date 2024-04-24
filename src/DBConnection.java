import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static void Connection() {
        String url = "jdbc:mariadb://localhost:3306/spec_check";
        String user = "spec_check";
        String password = "1234";

        try {
            // 데이터베이스 드라이버 클래스를 로드
            Class.forName("org.mariadb.jdbc.Driver");
            // 데이터베이스에 연결
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("데이터베이스에 성공적으로 연결되었습니다.");
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (
                SQLException e) {
            System.out.println("데이터베이스 연결 실패");
            e.printStackTrace();
        }
    }
}
