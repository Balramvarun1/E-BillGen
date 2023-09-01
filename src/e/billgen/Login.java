package e.billgen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Login extends JFrame implements ActionListener{
    JButton login, cancel, signup;
    JTextField username;
    JPasswordField password;
    Choice logginin;
    Login() {
        super("Login Page");
        getContentPane().setBackground(new Color(0X78DEC7));
        setLayout(null);
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(350, 120, 100, 20);
        lblusername.setFont(new Font("MV BOli", Font.BOLD, 14));
        add(lblusername);
        
        username = new JTextField();
        username.setBounds(450, 120, 150, 20);
        add(username);
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setFont(new Font("MV BOli", Font.BOLD, 14));
        lblpassword.setBounds(350, 160, 100, 20);
        add(lblpassword);
        
        password = new JPasswordField();
        password.setBounds(450, 160, 150, 20);
        add(password);
        
        JLabel loggininas = new JLabel("Loggin in as");
        loggininas.setFont(new Font("MV BOli", Font.BOLD, 14));
        loggininas.setBounds(350, 200, 100, 20);
        add(loggininas);
        
        logginin = new Choice();
        logginin.add("Admin");
        logginin.add("Customer");
        logginin.setBounds(450, 200, 150, 20);
        add(logginin);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("images/login.png"));
        Image i2=i1.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
        
        login = new JButton("Login",new ImageIcon(i2));
        login.setBounds(350, 260, 100, 40);
        login.setBackground(Color.orange);
        login.addActionListener(this);
        add(login);
        
        ImageIcon i3=new ImageIcon(ClassLoader.getSystemResource("images/cancel.jpg"));
        Image i4=i3.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
        
        cancel = new JButton("Cancel",new ImageIcon(i4));
        cancel.setBounds(450, 350, 100, 40);
        cancel.setBackground(Color.orange);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i5=new ImageIcon(ClassLoader.getSystemResource("images/signup.png"));
        Image i6=i5.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
        
        signup = new JButton("Signup",new ImageIcon(i6));
        signup.setBackground(Color.orange);
        signup.setBounds(550, 260, 100, 40);
        signup.addActionListener(this);
        add(signup);
        
        JLabel forgotpassword = new JLabel("<html><u>Forgot Password</u></html>");
        forgotpassword.setBounds(450, 400, 300, 40);
        forgotpassword.setFont(new Font("Arial", Font.PLAIN, 14));
        forgotpassword.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent e) {
            // Open the ForgotPasswordPage here
            // For example:
                
                new ForgotPassword();
                setVisible(false);// Create the ForgotPasswordPage class to handle the page
            }
        });
        add(forgotpassword);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("images/secondd.png"));
        Image i8 = i7.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(40, 70, 250, 250);
        add(image);
        
        setSize(840, 500);
        setLocation(200, 100);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String susername = username.getText();
            String spassword = password.getText();
            String user = logginin.getSelectedItem();

            try {
                Conn c = new Conn();
                String query = "select * from login where username = '"+susername+"' and user = '"+user+"'";
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    String storedHashedPassword = rs.getString("password");
                    if (BCrypt.checkpw(spassword, storedHashedPassword)) {
                        String meter = rs.getString("meter_no");
                        setVisible(false);
                        new Home(user, meter);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Login");
                        username.setText("");
                        password.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    username.setText("");
                    password.setText("");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        } else if (ae.getSource() == signup) {
            setVisible(false);

            new Signup();
        }
          
    }
    
    public static void main(String[] args) {
        new Login();
    }

}