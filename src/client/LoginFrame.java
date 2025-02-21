package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class LoginFrame extends JFrame {
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;

    public LoginFrame(PrintWriter out, BufferedReader in, Socket socket) {
        this.out = out;
        this.in = in;
        this.socket = socket;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Create a custom panel with background image
        BackgroundPanel panel = new BackgroundPanel("E:\\6TH Sem Aca\\EE6253 Operating Systems\\project\\BankApplication\\src\\client\\Bank image.png");
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Welcome to the Bank");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 23));
        titleLabel.setForeground(Color.WHITE);

        JLabel accountLabel = new JLabel("Account Number :");
        accountLabel.setForeground(Color.WHITE);
        accountLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Changed font size to 16
        JTextField accountField = new JTextField(15);

        JLabel pinLabel = new JLabel("Password          :");
        pinLabel.setForeground(Color.WHITE);
        pinLabel.setFont(new Font("Arial", Font.PLAIN, 16));; // Changed font size to 16
        JPasswordField pinField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(51, 153, 255));
        loginButton.setForeground(Color.WHITE);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setBackground(new Color(51, 204, 51));
        createAccountButton.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(accountLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(accountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(pinLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(pinField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(createAccountButton, gbc);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountField.getText();
                String pin = new String(pinField.getPassword());
                out.println("LOGIN:" + accountNumber + ":" + pin);
                try {
                    String response = in.readLine();
                    if (response.equals("LOGIN_SUCCESS")) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Login successful!");
                        openDashboard();
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Login failed!");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Error: " + ex.getMessage());
                }
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountField.getText();
                String pin = new String(pinField.getPassword());
                String initialBalance = JOptionPane.showInputDialog("Enter initial balance:");
                out.println("CREATE_ACCOUNT:" + accountNumber + ":" + pin + ":" + initialBalance);
                try {
                    String response = in.readLine();
                    if (response.equals("CREATE_ACCOUNT_SUCCESS")) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Account created successfully!");
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Account creation failed!");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    private void openDashboard() {
        // Hide the current frame
        this.setVisible(false);

        // Open the dashboard frame
        new DashboardFrame(out, in, socket);
    }

    // Custom JPanel class to draw the background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            this.backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}