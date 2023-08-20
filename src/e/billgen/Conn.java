package e.billgen;

import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    Conn() {
        try {
            c = DriverManager.getConnection("jdbc:mysql:///ebs", "root", "Varun123#"); 
            s = c.createStatement();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
