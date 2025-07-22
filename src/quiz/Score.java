/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quiz;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class Score extends JFrame implements ActionListener {
    private String name;
    private int score;
    private int timeTaken;

    Score(String name, int score,int timeTaken) {
        this.name = name;
        this.score = score;
        this.timeTaken = timeTaken;
        
        setBounds(400, 150, 750, 550);
        getContentPane().setBackground(Color.pink);
        setLayout(null);
         
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/score2.0.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 200, 300, 250);
        add(image);
        
        // Set up the score display
        JLabel heading = new JLabel("Thank you " + name + " for playing Brain Twister");
        heading.setBounds(45, 30, 700, 30);
        heading.setForeground(Color.black);
        heading.setFont(new Font("Forte", Font.BOLD, 32));
        add(heading);
        
        JLabel lblscore = new JLabel("Your score is " + score);
        lblscore.setBounds(350, 200, 300, 30);
        lblscore.setForeground(Color.BLACK);
        lblscore.setFont(new Font("Forte", Font.PLAIN, 26));
        add(lblscore);
        
        // Save score to leaderboard
        saveScore(name, score,timeTaken);
        
        
        // Play Again button
        JButton submit = new JButton("Play Again");
        submit.setBounds(380, 270, 120, 30);

        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);
        
        // Leaderboard button
        JButton leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.setBounds(380, 320, 120, 30);
        
        leaderboardButton.setBackground(Color.BLACK);
        leaderboardButton.setForeground(Color.WHITE);
        leaderboardButton.addActionListener(e -> {
            setVisible(false);
            new Leaderboard();
        });
        add(leaderboardButton);
        
        setVisible(true);
    }
     
      
    
    private void saveScore(String name, int score,int timeTaken) {
        try (FileWriter writer = new FileWriter("leaderboard.txt", true)) {
              writer.write(name + ", " + score + ", " + timeTaken + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login(); // Adjust this to return to the appropriate class
    }

    public static void main(String[] args) {
        new Score("User", 0,0);
    }
}