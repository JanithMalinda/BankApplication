package client;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class DashboardFrame extends JFrame {
    private JButton balanceButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;

    private JTextField amountField;
    private JTextField recipientField;
    private JTextArea logTextArea;

    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;

    public DashboardFrame(PrintWriter out, BufferedReader in, Socket socket) {
        this.out = out;
        this.in = in;
        this.socket = socket;

        setTitle("Dashboard");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        addComponentsToFrame();

        setVisible(true);
    }

    private void initComponents() {
        balanceButton = createStyledButton("Check Balance", new Color(0, 102, 204)); // Blue
        depositButton = createStyledButton("Deposit Money", new Color(0, 153, 51)); // Green
        withdrawButton = createStyledButton("Withdraw Money", new Color(204, 0, 0)); // Red
        transferButton = createStyledButton("Transfer Money", new Color(153, 0, 153)); // Purple

        amountField = createStyledTextField(10);
        recipientField = createStyledTextField(10);

        logTextArea = new JTextArea(10, 30);
        logTextArea.setEditable(false);
        logTextArea.setLineWrap(true);
        styleTextArea(logTextArea);

        balanceButton.addActionListener(e -> checkBalance());
        depositButton.addActionListener(e -> depositMoney());
        withdrawButton.addActionListener(e -> withdrawMoney());
        transferButton.addActionListener(e -> transferMoney());
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JTextField createStyledTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(240, 240, 240)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return textField;
    }

    private void styleTextArea(JTextArea textArea) {
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setBackground(new Color(211, 211, 211)); // Light gray background
        textArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
    }

    private void addComponentsToFrame() {
        // Create a main panel with a solid background color
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(60, 60, 63)); // Dark gray background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(balanceButton, gbc);

        gbc.gridx = 1;
        panel.add(depositButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(withdrawButton, gbc);

        gbc.gridx = 1;
        panel.add(transferButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(createLabel("Amount:"), gbc);

        gbc.gridx = 1;
        panel.add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(createLabel("Recipient Account Number:"), gbc);

        gbc.gridx = 1;
        panel.add(recipientField, gbc);

        // Add the log text area to a scroll pane
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Set the background color of the scroll pane
        scrollPane.getViewport().setBackground(new Color(240, 240, 240)); // Light gray background

        // Add the panel and scroll pane to the frame
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Set the background color of the frame
        getContentPane().setBackground(new Color(60, 63, 65)); // Dark gray background
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(Color.WHITE); // White text color
        return label;
    }

    private void checkBalance() {
        try {
            out.println("BALANCE");
            String response = in.readLine();
            logTextArea.append(response + "\n");
        } catch (IOException e) {
            logTextArea.append("Error occurred while checking balance: " + e.getMessage() + "\n");
        }
    }

    private void depositMoney() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            out.println("DEPOSIT:" + amount);
            String response = in.readLine();
            logTextArea.append(response + "\n");
        } catch (NumberFormatException e) {
            logTextArea.append("Invalid amount format.\n");
        } catch (IOException e) {
            logTextArea.append("Error occurred while depositing money: " + e.getMessage() + "\n");
        }
    }

    private void withdrawMoney() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            out.println("WITHDRAW:" + amount);
            String response = in.readLine();
            logTextArea.append(response + "\n");
        } catch (NumberFormatException e) {
            logTextArea.append("Invalid amount format.\n");
        } catch (IOException e) {
            logTextArea.append("Error occurred while withdrawing money: " + e.getMessage() + "\n");
        }
    }

    private void transferMoney() {
        try {
            String recipientAccountNumber = recipientField.getText();
            double amount = Double.parseDouble(amountField.getText());
            String transferRequest = "TRANSFER:" + recipientAccountNumber + ":" + amount;
            out.println(transferRequest);
            String response = in.readLine();
            logTextArea.append(response + "\n");
        } catch (NumberFormatException e) {
            logTextArea.append("Invalid amount format.\n");
        } catch (IOException e) {
            logTextArea.append("Error occurred while transferring money: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrintWriter out = null;
            BufferedReader in = null;
            Socket socket = null;

            new DashboardFrame(out, in, socket);
        });
    }
}