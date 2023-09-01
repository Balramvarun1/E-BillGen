package e.billgen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class ForgotPassword extends JFrame implements ActionListener {

    JLabel userIdLabel, phoneNoLabel, otpLabel;
    JTextField userIdTextField, phoneNoTextField, otpTextField;
    JButton sendOtpButton, resetButton,back;
    private String randomOTP = "";

    // Replace [YourAccountSID] and [YourAuthToken] with your actual credentials
    public static final String TWILIO_ACCOUNT_SID = "";
    public static final String TWILIO_AUTH_TOKEN = "";

    public ForgotPassword() {
        super("Forgot Password Page");
        getContentPane().setBackground(new Color(0X78DEC7));
        setLayout(null);

        userIdLabel = new JLabel("Meter No :");
        userIdLabel.setBounds(350, 120, 100, 20);
        userIdLabel.setFont(new Font("MV BOli", Font.BOLD, 14));
        
        userIdTextField = new JTextField();
        userIdTextField.setBounds(450, 120, 150, 20);
        
        phoneNoLabel = new JLabel("Phone No :");
        phoneNoLabel.setFont(new Font("MV BOli", Font.BOLD, 14));
        phoneNoLabel.setBounds(350, 160, 100, 20);
        
        phoneNoTextField = new JTextField();
        phoneNoTextField.setBounds(450, 160, 150, 20);
        
        otpLabel = new JLabel("Enter OTP :");
        otpLabel.setBounds(350, 200, 100, 20);
        otpLabel.setFont(new Font("MV BOli", Font.BOLD, 14));
        
        otpTextField = new JTextField();
        otpTextField.setBounds(450, 200, 150, 20);
        
        sendOtpButton = new JButton("Send OTP");
        sendOtpButton.setBackground(Color.black);
        sendOtpButton.setForeground(Color.white);
        sendOtpButton.setBounds(350, 260, 100, 40);
        sendOtpButton.addActionListener(this);
        
        back = new JButton("Back");
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setBounds(540, 260, 100, 40);
        back.addActionListener(this);
        
        resetButton = new JButton("Reset Password");
        resetButton.setBackground(Color.black);
        resetButton.setForeground(Color.white);
        resetButton.setBounds(430, 350, 150, 40);
        resetButton.addActionListener(this);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("images/forgotpassword3.png"));
        Image i8 = i7.getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(40, 70, 250, 250);
       

        add(userIdLabel);
        add(userIdTextField);
        add(phoneNoLabel);
        add(phoneNoTextField);
        add(otpLabel);
        add(otpTextField);
        add(sendOtpButton);
        add(back);
        add(resetButton);
        add(image);
        
        setSize(840, 500);
        setLocation(200, 100);
        setVisible(true);
    }

    // Implement actionPerformed to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
         String meter = userIdTextField.getText();
         String phoneNo = phoneNoTextField.getText();
         randomOTP = generateRandomOTP();
        if (e.getSource() == sendOtpButton) {
           
            // Use the Conn class to check if the user exists in the database
            boolean isValidUser = isValidUser(meter, phoneNo);

            if (isValidUser) {

                // Send OTP to user's phone using the sendSms method
                boolean otpSent = sendSms(phoneNo, randomOTP);

                if (otpSent) {
                    JOptionPane.showMessageDialog(this, "OTP sent successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to send OTP");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Meter No or Phone No");
            }
            
        } else if (e.getSource() == resetButton) {
           
            if (meter.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Meter No cannot be empty.");
                return; // Don't proceed further
            }
            if (phoneNo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Phone No cannot be empty.");
                return; // Don't proceed further
            }
            
            String enteredOTP = otpTextField.getText();
            //System.out.println(enteredOTP);
            // TODO: Compare enteredOTP with the sent OTP
            if (enteredOTP.equals(randomOTP)) {
                setVisible(false);
                new ResetPassword(meter);
                // Navigate to the Reset Password page
                // TODO: Implement the reset password functionality
            } else {
                JOptionPane.showMessageDialog(this, "Wrong OTP");
            }
        }
        else if (e.getSource() == back) {
        setVisible(false);

        new Login();
    }
    }

    private boolean isValidUser(String userId, String phoneNo) {
        // Use the Conn class to query the database
        try {
            Conn conn = new Conn();
            String query = "select * from customer where meter_no = '" + userId + "' AND phone = '" + phoneNo + "'";
            ResultSet rs = conn.s.executeQuery(query);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to generate a random 6-digit OTP
    private String generateRandomOTP() {
        Random rand = new Random();
        int otp = rand.nextInt(900000) + 100000; // Generates a random 6-digit number
        return String.valueOf(otp);
    }

    // Method to send OTP via SMS using Twilio
    private boolean sendSms(String phoneNumber, String otp) {
        try {
            Twilio.init(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN); // Initialize Twilio here

            Message message = Message.creator(
                    new PhoneNumber("+91" + phoneNumber),   // Recipient's phone number
                    new PhoneNumber("+12053156575"),  // Your Twilio phone number
                    "Your OTP is: " + otp)  // Message body with OTP
                    .create();

            // Check if the message was sent successfully
            if (message.getSid() != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ... (existing code)

    public static void main(String[] args) {
        new ForgotPassword();
    }
}
