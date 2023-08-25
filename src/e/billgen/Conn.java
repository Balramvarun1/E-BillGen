package e.billgen;

import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;


public class Conn {

    Connection c;
    Statement s;
    Conn() {
        try {
            c = DriverManager.getConnection("jdbc:mysql:///ebs", "root", "Varun123#");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String hashPassword(String password) {
        // Use a strong hashing algorithm like bcrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }
}
