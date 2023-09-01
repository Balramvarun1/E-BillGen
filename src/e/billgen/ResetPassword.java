package e.billgen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResetPassword extends JFrame implements ActionListener {
    JLabel newpassword,conpassword;
    JTextField lblnewpassword;
    JPasswordField lblconpassword;
    JButton changepassword;
    String meter;
    ResetPassword(String meter){
        super("Reset Password Page");
        getContentPane().setBackground(new Color(0X78DEC7));
        setLayout(null);
        
        this.meter = meter;
        
        newpassword = new JLabel("New Password :-");
        newpassword.setBounds(300, 120, 200, 20);
        newpassword.setFont(new Font("MV BOli", Font.BOLD, 14));
        add(newpassword);
        
        lblnewpassword = new JTextField();
        lblnewpassword.setBounds(450, 120, 150, 20);
        add(lblnewpassword);
        
        conpassword = new JLabel("Confirm Password :-");
        conpassword.setBounds(300, 180, 200, 20);
        conpassword.setFont(new Font("MV BOli", Font.BOLD, 14));
        add(conpassword);
        
        lblconpassword = new JPasswordField();
        lblconpassword.setBounds(450, 180, 150, 20);
        add(lblconpassword);
        
        changepassword = new JButton("Change Password");
        changepassword.setBackground(Color.black);
        changepassword.setForeground(Color.white);
        changepassword.setBounds(350, 260, 200, 40);
        changepassword.addActionListener(this);
        add(changepassword);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("images/resetpassword1.jpg"));
        Image i8 = i7.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(40, 70, 250, 250);
        add(image);
        
        setSize(700, 400);
        setLocation(400, 100);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if (e.getSource() == changepassword) {
            String newPassword = new String(lblnewpassword.getText());
            String confirmPassword = new String(lblconpassword.getPassword());

            if (newPassword.equals(confirmPassword)) {
                // Hash the password before updating it
                try {
                    Conn c = new Conn();
           
                    // Continue with account creation
                    String hashedPassword = Conn.hashPassword(newPassword);

                    String query = "UPDATE login SET password = '" + hashedPassword + "' WHERE meter_no = '" + meter + "'";
                    
                    c.s.executeUpdate(query);
                }
                
                catch (SQLException ae) {
                    ae.printStackTrace();
                }

                // Display a success message or perform any other actions as needed
                JOptionPane.showMessageDialog(this, "Password changed successfully!");
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Passwords do not match. Please try again.");
            }
        }
    }
    public static void main(String args[]){
        new ResetPassword("");
    }
}
