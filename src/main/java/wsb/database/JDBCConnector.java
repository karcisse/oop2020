package wsb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCConnector {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/";

    static final String USER = "postgres";
    static final String PASS = "sela1234";

    public static void connect() throws SQLException {
        String url = "jdbc:postgresql://localhost/qqp";
        Properties props = new Properties();
        props.setProperty("user",USER);
        props.setProperty("password",PASS);
//        props.setProperty("ssl","true");
        Connection conn = DriverManager.getConnection(url, props);
        System.out.println("connected");

        Statement st = conn.createStatement();
        st.execute("insert into animal values ('dog');");
    }

}
