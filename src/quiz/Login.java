/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quiz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Login extends JFrame implements ActionListener
{
    
    JTextField tfname;
    JButton rules, back;
    
    Login(){
        
        getContentPane().setBackground(Color.PINK);
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/q2.0.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 600, 480);
        add(image);
        
        JLabel heading = new JLabel("Brain Twister");
        heading.setBounds(750, 60, 300, 45);
        heading.setFont(new Font("Bauhaus 93", Font.BOLD, 40));
        heading.setForeground(Color.BLACK);
        add(heading);
                
        JLabel name = new JLabel("Enter your name");
        name.setBounds(810, 150, 300, 20);
        name.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
        name.setForeground(Color.BLACK);
        add(name);
        
        tfname = new JTextField();
        tfname.setBounds(735, 200, 300, 25);
        tfname.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfname);
        
        rules = new JButton("Rules");
        rules.setBounds(735, 270, 120, 25);
        rules.setBackground(Color.BLACK);
        rules.setForeground(Color.WHITE);
        rules.addActionListener(this);
        add(rules);
        
        back = new JButton("Back");
        back.setBounds(915, 270, 120, 25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        
        setSize(1200, 500);
        setLocation(200, 150);
        setVisible(true);
        
    
    }
    public void actionPerformed(ActionEvent ae){
        
        if (ae.getSource() == rules) {
            String name = tfname.getText();
            setVisible(false);
            new Rules(name);
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }
    public static void main(String[] args){
        new Login();
        
    }
}
      