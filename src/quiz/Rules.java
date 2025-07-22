package Quiz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Nazeeha Farvin
 */
public class Rules extends JFrame implements ActionListener{
    String name;
    JButton start, back;
    Rules(String name){
        this.name = name;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Welcome " + name + " To Brain Twister");
        heading.setBounds(50, 20, 700, 30);
        heading.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 28));
        heading.setForeground(Color.BLACK);
        add(heading);
        
           JLabel rules = new JLabel();
        rules.setBounds(20, 90, 700, 350);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rules.setForeground(Color.BLACK);
        rules.setText(
            "<html>"+ 
                "1. You are trained to be a programmer and not a story teller, answer point to point" + "<br><br>" +
                "2. Do not unnecessarily smile at the person sitting next to you, they may also not know the answer" + "<br><br>" +
                "3. You may have lot of options in life but here all the questions are compulsory" + "<br><br>" +
                "4. Crying is allowed but please do so quietly." + "<br><br>" +
                "5. Only a fool asks and a wise answers (Be wise, not otherwise)" + "<br><br>" +
                "6. Do not get nervous if your friend is answering more questions" + "<br><br>" +
                "7. You can use 50-50 lifeline once!" + "<br><br>" +
                "8.GOOD LUCK" + "<br><br>" +
            "<html>"
        );
        add(rules);
        
        back = new JButton("Back");
        back.setBounds(250, 500, 100, 30);
        
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
        start = new JButton("Start");
        start.setBounds(400, 500, 100, 30);

        start.setBackground(Color.BLACK);
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        add(start);
        
        setSize(800, 650);
        setLocation(350, 100);
        setVisible(true);
        
        
    }
     
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == start) {
            setVisible(false);
            new Quiz(name);
        } else {
            setVisible(false);
            new Login();
        }
    }
    
        
        
    public static void main(String[] args) {
        new Rules("User");
    }
}
    
    


