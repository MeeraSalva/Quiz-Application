/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Quiz;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leaderboard extends JFrame {

    JTable table;  // Declare JTable as a class member so we can update it after clearing the data

    Leaderboard() {
        setBounds(400, 150, 750, 550);
        getContentPane().setBackground(Color.white);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Leaderboard", JLabel.CENTER);
        heading.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        add(heading, BorderLayout.NORTH);

        // Updated column names
        String[] columnNames = {"Rank", "Name", "Score", "Time"};

        // Load leaderboard data and sort by score, then by time
        List<Object[]> data = loadLeaderboard();
        data.sort(Comparator.comparingInt((Object[] entry) -> (Integer) entry[2])
                .reversed().thenComparingInt(entry -> (Integer) entry[3]));

        // Add rank to the data
        for (int i = 0; i < data.size(); i++) {
            data.get(i)[0] = i + 1; // Rank is assigned based on position in the sorted list
        }

        // Create the table with updated data
        table = new JTable(data.toArray(new Object[0][]), columnNames);
        table.setFont(new Font("Monospaced", Font.PLAIN, 18));
        table.setRowHeight(30);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Panel to hold buttons (Back and Clear Leaderboard)
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(30, 144, 255));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            setVisible(false);
            new Score("User", 0, 0); // Replace with logic to return to the previous screen
        });
        buttonPanel.add(backButton);

        // Clear Leaderboard Button
        JButton clearButton = new JButton("Clear Leaderboard");
        clearButton.setBackground(new Color(255, 69, 0)); // Red background
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(e -> {
            clearLeaderboard();  // Clear the leaderboard
            JOptionPane.showMessageDialog(null, "Leaderboard Cleared!");
        });
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to load leaderboard data from file
    private List<Object[]> loadLeaderboard() {
        List<Object[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    int time = Integer.parseInt(parts[2]); // Assuming time is stored in seconds
                    data.add(new Object[]{0, name, score, time}); // Rank placeholder (will be updated later)
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    // Method to clear leaderboard data from the file
    private void clearLeaderboard() {
        try {
            // Clear the content of the leaderboard file
            FileWriter fw = new FileWriter("leaderboard.txt", false);
            fw.write("");  // Write empty content to clear the file
            fw.close();

            // Clear the JTable
            String[] columnNames = {"Rank", "Name", "Score", "Time"};
            table.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{}, columnNames  // Set empty data
            ));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Leaderboard();
    }
}