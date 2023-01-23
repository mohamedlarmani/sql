import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Students studen1 = new Students("Mohamed", "Larmani");
        Students studen2 = new Students("Mario", "Rossi");
        Students studen3 = new Students("Carlo", "Verdi");
        Students studen4 = new Students("Luca", "Bianchi");

        List<Students> studentsList = new ArrayList<>();
        studentsList.add(studen1);
        studentsList.add(studen2);
        studentsList.add(studen3);
        studentsList.add(studen4);
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        try {
            String url = "jdbc:mysql://localhost:3306/newdb";
            String user = "developer1";
            String password = "password";

            conn = DriverManager.getConnection(url, user, password);


            ps = conn.prepareStatement("CREATE TABLE develhope_test ("
                +"student_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                +"first_name VARCHAR(128),"
                +"last_name VARCHAR(128)"
                +")");
            ps.executeUpdate();

            for(Students s : studentsList){
                ps1 = conn.prepareStatement("INSERT INTO newdb.develhope_test (first_name, last_name) VALUES (?, ?)");
                ps1.setString(1, s.getName());
                ps1.setString(2, s.getSurname());
                ps1.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (ps != null)
                    ps.close();
                if (ps1 != null)
                    ps1.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}