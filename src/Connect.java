import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private String dbname = "sewamobil";
    private String user = "root";
    private String password = "";
    private Connection con;
    

    public Connect() {
        String url = "jdbc:mysql://localhost:3306/" + dbname;
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi berhasil!");
        } catch (SQLException e) {
            System.out.println("Koneksi gagal!");
            e.printStackTrace();
        }
    }
    
    public Connection getCon() {
        return con;
    }
}
