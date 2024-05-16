package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection(){
        String dataBaseName = "dbtangen";
        String user = "testuser";
        String pass = "test";
        Connection connection=null;
        // JDBCドライバの読み込み
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
            return connection; //nullが帰る
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dataBaseName, user, pass);
        }catch (SQLException e){
            e.printStackTrace();
        }
            return connection;
    }
}
