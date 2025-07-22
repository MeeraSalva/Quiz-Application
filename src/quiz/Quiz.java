

package Quiz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;



public class Quiz extends JFrame implements ActionListener {
      // Timer logic
    long startTime; // Start time of the quiz
    long endTime;   // End time of the quiz

//    String questions[][] = new String[10][5];
    List<String[]> questions;
    List<String> useranswers = new ArrayList<>();
    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, submit, lifeline;

    public static int timer = 15;
    public static int ans_given = 0;
    public static int currentQuestion;
    public static int score = 0;    
    public static int count = 0;

     public static int maxQuestions = 10;
    public static int timeTaken; // Track the time taken to complete the quiz
    String name;
     
    
    Quiz(String name) {
        this.name = name;

        // Set the start time when the quiz begins
        startTime = System.currentTimeMillis();
 
        
         setBounds(50, 0, 1440, 850);
         getContentPane().setBackground(Color.white);
         setLayout(null);
        
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/quizz.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1440, 392);
        add(image);
        
        qno = new JLabel();
        qno.setBounds(100, 450, 50, 30);
        qno.setForeground(Color.black);
        
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(qno);
        
        question = new JLabel();
        question.setBounds(150, 450, 900, 30);
        question.setForeground(Color.black);
        
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(question);
        questions = loadQuestions();
         Collections.shuffle(questions);
         
        opt1 = new JRadioButton();
        opt1.setBounds(170, 520, 700, 30);
        opt1.setForeground(Color.black);
        opt1.setBackground(Color.white);
        opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt1);
        
        opt2 = new JRadioButton();
        opt2.setBounds(170, 560, 700, 30);
        opt2.setForeground(Color.black);
        opt2.setBackground(Color.white);
        opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt2);
        
        opt3 = new JRadioButton();
        opt3.setBounds(170, 600, 700, 30);
        opt3.setForeground(Color.black);
        opt3.setBackground(Color.white);
        opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt3);
        
        opt4 = new JRadioButton();
        opt4.setBounds(170, 640, 700, 30);
        opt4.setForeground(Color.black);
        opt4.setBackground(Color.white);
        opt4.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt4);
        
        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);
        
        next = new JButton("Next");
        next.setBounds(1100, 550, 200, 40);
        next.setFont(new Font("Tahoma", Font.PLAIN, 22));
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.addActionListener(this);
        add(next);
        
        lifeline = new JButton("50-50 Lifeline");
        lifeline.setBounds(1100, 630, 200, 40);
        lifeline.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lifeline.setBackground(Color.BLACK);
        lifeline.setForeground(Color.WHITE);
        lifeline.addActionListener(this);
        add(lifeline);
        
        submit = new JButton("Submit");
        submit.setBounds(1100, 710, 200, 40);
        submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);
        
        start(count);
        
        setVisible(true);
    }
    private List<String[]> loadQuestions(){
        List<String[]> data = new ArrayList<>();
         List<String[]> quizEntries= new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("questions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length == 6 ) {
                    String question = parts[0]; 
                    String option1 = parts[1];
                    String option2 = parts[2];
                    String option3 = parts[3];
                    String option4 = parts[4];
                    String answer = parts[5];
                    
                   data.add(new String[]{question,option1,option2,option3,option4,answer}); // Rank placeholder (will be updated later)
                }
            }
         Collections.shuffle(data);
         for(int i=0; i<maxQuestions; i++){
             quizEntries.add(data.get(i));
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quizEntries;
    }
     public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            handleNextQuestion();
        } else if (ae.getSource() == lifeline) {
            handleLifeline();
        } else if (ae.getSource() == submit) {
            handleSubmit();
        }
    }

    private void handleNextQuestion() {
        ans_given = 1;
        currentQuestion = count + 2;
        if (groupoptions.getSelection() == null) {
            useranswers.add(count, "");
        } else {
            useranswers.add(count, groupoptions.getSelection().getActionCommand());
        }
        System.out.println(currentQuestion);
        if (currentQuestion == maxQuestions) {
            next.setEnabled(false);
            submit.setEnabled(true);
        }
        count++;
        start(count);
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);
    }

    private void handleLifeline() {
        List<JRadioButton> optionsList = new ArrayList<>(List.of(opt1,opt2,opt3,opt4));
        Collections.shuffle(optionsList);
        int answerButtonIndex = 0;
        for(int i=0; i<optionsList.size(); i++){
            String btnAnswer = optionsList.get(i).getText();
            String actualAnswer =  questions.get(count)[5];
            if(btnAnswer.equals(actualAnswer)){
                answerButtonIndex = i;
                break;
            }
        }
        optionsList.remove(answerButtonIndex);
         for(int i=0; i<optionsList.size(); i++){
            String btnAnswer = optionsList.get(i).getText();
        }
         optionsList.getFirst().setEnabled(false);
         optionsList.getLast().setEnabled(false);
         
        lifeline.setEnabled(false);
    }

    private void handleSubmit() {
        ans_given = 1;
        if (groupoptions.getSelection() == null) {
            useranswers.add(count, "");
        } else {
            useranswers.add(count, groupoptions.getSelection().getActionCommand());
        }
        // Calculate the score
        for (int i = 0; i < useranswers.size(); i++) {
            if (useranswers.get(i).equals(questions.get(i)[5])) {
                score += 10;
            }
        }

        // Record the end time when quiz is submitted
        endTime = System.currentTimeMillis(); 
        timeTaken = (int) ((endTime - startTime) / 1000); // Convert milliseconds to seconds

        setVisible(false);
        new Score(name, score, timeTaken);
    }
 
    
    
    public void paint(Graphics g) {
        super.paint(g);
        
        String time = "Time left - " + timer + " seconds"; // 15
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.BOLD, 25));
        
        if (timer > 0) { 
            g.drawString(time, 1100, 500);
        } else {
            g.drawString("Times up!!", 1100, 500);
        }
        
        timer--; // 14
        
        try {
            Thread.sleep(1000);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (ans_given == 1) {
            ans_given = 0;
            timer = 15;
        } else if (timer < 0) {
            timer = 15;
            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);
            int currentQuestion = count + 2;
            if (currentQuestion == maxQuestions) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            if (count == maxQuestions -1) { // submit button
                if (groupoptions.getSelection() == null) {
                   useranswers.add(count, "");
                } else {
                    useranswers.add(count, groupoptions.getSelection().getActionCommand());
                }
                
                for (int i = 0; i < useranswers.size(); i++) {
                    if (useranswers.get(i).equals(questions.get(i)[5])) {
                        score += 10;
                    } else {
                        score += 0;
                    }
                }
                setVisible(false);
                new Score(name,score,timeTaken);
            } else { // next button
                if (groupoptions.getSelection() == null) {
                   useranswers.add(count, "");
                } else {
                    useranswers.add(count, groupoptions.getSelection().getActionCommand());
                }
                count++; // 0 // 1
                start(count);
            }
        }
        
    }
    
    public void start(int count) {
        qno.setText("" + (count + 1) + ". ");
        question.setText(questions.get(count)[0]);
 
        opt1.setText(questions.get(count)[1]);
        opt1.setActionCommand(questions.get(count)[1]);
        
        opt2.setText(questions.get(count)[2]);
        opt2.setActionCommand(questions.get(count)[2]);
        
         opt3.setText(questions.get(count)[3]);
        opt3.setActionCommand(questions.get(count)[3]);
        
         opt4.setText(questions.get(count)[4]);
        opt4.setActionCommand(questions.get(count)[4]);
        
        
        groupoptions.clearSelection();
    }
    public static void main(String[] args) {
        new Quiz("User");
    }
    
    
}
    
