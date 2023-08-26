package e.billgen;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ViewInformation extends JFrame implements ActionListener{
    JButton cancel;
    ViewInformation(String meter) {
        setBounds(350, 150, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        
        JLabel heading = new JLabel("CUSTOMER INFORMATION");
        heading.setBounds(300, 0, 500, 40);
        heading.setFont(new Font("MV Boli", Font.BOLD, 15));
        add(heading);
        
        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("MV Boli", Font.BOLD, 15));
        lblname.setBounds(70, 80, 100, 20);
        add(lblname);
        
        JLabel name = new JLabel("");
        name.setFont(new Font("MV Boli", Font.BOLD, 20));
        name.setBounds(250, 80, 100, 20);
        add(name);
        
        JLabel lblmeternumber = new JLabel("Meter No");
        lblmeternumber.setFont(new Font("MV Boli", Font.BOLD, 15));
        lblmeternumber.setBounds(70, 140, 100, 20);
        add(lblmeternumber);
        
        JLabel meternumber = new JLabel("");
        meternumber.setFont(new Font("MV Boli", Font.BOLD, 20));
        meternumber.setBounds(250, 140, 100, 20);
        add(meternumber);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setFont(new Font("MV Boli", Font.BOLD, 15));
        lbladdress.setBounds(70, 200, 100, 20);
        add(lbladdress);
        
        JLabel address = new JLabel("");
        address.setFont(new Font("MV Boli", Font.BOLD, 20));
        address.setBounds(250, 200, 200, 20);
        add(address);
        
        JLabel lblcity = new JLabel("City");
        lblcity.setFont(new Font("MV Boli", Font.BOLD, 15));
        lblcity.setBounds(70, 260, 100, 20);
        add(lblcity);
        
        JLabel city = new JLabel("");
        city.setFont(new Font("MV Boli", Font.BOLD, 20));
        city.setBounds(250, 260, 200, 20);
        add(city);
        
        JLabel lblstate = new JLabel("State");
        lblstate.setFont(new Font("MV Boli", Font.BOLD, 15));
        lblstate.setBounds(500, 80, 100, 20);
        add(lblstate);
        
        JLabel state = new JLabel("");
        state.setFont(new Font("MV Boli", Font.BOLD, 20));
        state.setBounds(600, 80, 200, 20);
        add(state);
        
        JLabel lblemail = new JLabel("Email");
        lblemail.setFont(new Font("MV Boli", Font.BOLD, 15));
        lblemail.setBounds(500, 140, 100, 20);
        add(lblemail);
        
        JLabel email = new JLabel("");
        email.setFont(new Font("MV Boli", Font.BOLD, 20));
        email.setBounds(600, 140, 200, 20);
        add(email);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setFont(new Font("MV Boli", Font.BOLD, 15));
        lblphone.setBounds(500, 200, 100, 20);
        add(lblphone);
        
        JLabel phone = new JLabel("");
        phone.setFont(new Font("MV Boli", Font.BOLD, 20));
        phone.setBounds(600, 200, 200, 20);
        add(phone);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
            while(rs.next()) {
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
                meternumber.setText(rs.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(350, 340, 100, 25);
        add(cancel);
        cancel.addActionListener(this);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20, 350, 600, 300);
        add(image);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }
    
    public static void main(String[] args) {
        new ViewInformation("");
    }

}
