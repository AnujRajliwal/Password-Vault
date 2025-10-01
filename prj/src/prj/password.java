package prj;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class password{
    private static final String DB_URL = "jdbc:sqlite:password_vault.db";
    private static String currentUser;
    public static void main(String[] args) {
        createTables();
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
    private static void createTables() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String sqlUsers = "CREATE TABLE IF NOT EXISTS users (" +
                              "username TEXT PRIMARY KEY, " +
                              "password TEXT NOT NULL)";
            stmt.execute(sqlUsers);

            String sqlVault = "CREATE TABLE IF NOT EXISTS vault (" +
                              "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                              "username TEXT, " +
                              "platform TEXT, " +
                              "siteUser TEXT, " +
                              "sitePass TEXT)";
            stmt.execute(sqlVault);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean registerUser(String user, String pass) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO users(username, password) VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public static boolean loginUser(String user, String pass) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                currentUser = user;
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void saveVault(String platform, String siteUser, String sitePass) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO vault(username, platform, siteUser, sitePass) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, currentUser);
            ps.setString(2, platform);
            ps.setString(3, siteUser);
            ps.setString(4, sitePass);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static String loadVault() {
        StringBuilder sb = new StringBuilder();
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT platform, siteUser, sitePass FROM vault WHERE username=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, currentUser);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sb.append("Platform: ").append(rs.getString("platform"))
                  .append("\nUsername: ").append(rs.getString("siteUser"))
                  .append("\nPassword: ").append(rs.getString("sitePass"))
                  .append("\n---------------------\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Login");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelUser = new JLabel("Username:");
        JTextField textUser = new JTextField();
        JLabel labelPass = new JLabel("Password:");
        JPasswordField textPass = new JPasswordField();
        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        labelUser.setBounds(30, 30, 80, 25);
        textUser.setBounds(120, 30, 120, 25);
        labelPass.setBounds(30, 70, 80, 25);
        textPass.setBounds(120, 70, 120, 25);
        loginBtn.setBounds(40, 110, 80, 30);
        registerBtn.setBounds(150, 110, 90, 30);

        add(labelUser); add(textUser);
        add(labelPass); add(textPass);
        add(loginBtn); add(registerBtn);

        loginBtn.addActionListener(e -> {
            String user = textUser.getText();
            String pass = new String(textPass.getPassword());
            if (password.loginUser(user, pass)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                new VaultFrame();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login!");
            }
        });

        registerBtn.addActionListener(e -> {
            new RegisterFrame();
            dispose();
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class RegisterFrame extends JFrame {
    public RegisterFrame() {
        setTitle("Register");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelUser = new JLabel("Username:");
        JTextField textUser = new JTextField();
        JLabel labelPass = new JLabel("Password:");
        JPasswordField textPass = new JPasswordField();
        JButton registerBtn = new JButton("Register");

        labelUser.setBounds(30, 30, 80, 25);
        textUser.setBounds(120, 30, 120, 25);
        labelPass.setBounds(30, 70, 80, 25);
        textPass.setBounds(120, 70, 120, 25);
        registerBtn.setBounds(90, 110, 100, 30);

        add(labelUser); add(textUser);
        add(labelPass); add(textPass);
        add(registerBtn);

        registerBtn.addActionListener(e -> {
            String user = textUser.getText();
            String pass = new String(textPass.getPassword());
            if (password.registerUser(user, pass)) {
                JOptionPane.showMessageDialog(this, "Registration Successful!");
                dispose();
                new LoginFrame();
            } else {
                JOptionPane.showMessageDialog(this, "Username already exists!");
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class VaultFrame extends JFrame {
    private final JTextArea display;

    public VaultFrame() {
        setTitle("Password Vault");
        setSize(400, 450);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelPlatform = new JLabel("Platform:");
        JTextField textPlatform = new JTextField();
        JLabel labelUser = new JLabel("Site Username:");
        JTextField textUser = new JTextField();
        JLabel labelPass = new JLabel("Site Password:");
        JPasswordField textPass = new JPasswordField();

        JButton saveBtn = new JButton("Save");
        JButton showBtn = new JButton("Show All");

        display = new JTextArea();
        display.setEditable(false);

        labelPlatform.setBounds(20, 20, 100, 25);
        textPlatform.setBounds(140, 20, 200, 25);
        labelUser.setBounds(20, 60, 100, 25);
        textUser.setBounds(140, 60, 200, 25);
        labelPass.setBounds(20, 100, 100, 25);
        textPass.setBounds(140, 100, 200, 25);
        saveBtn.setBounds(40, 140, 120, 30);
        showBtn.setBounds(200, 140, 120, 30);
        display.setBounds(20, 190, 340, 200);

        add(labelPlatform); add(textPlatform);
        add(labelUser); add(textUser);
        add(labelPass); add(textPass);
        add(saveBtn); add(showBtn); add(display);

        saveBtn.addActionListener(e -> {
            password.saveVault(textPlatform.getText(), textUser.getText(), new String(textPass.getPassword()));
            JOptionPane.showMessageDialog(this, "Saved!");
            textPlatform.setText(""); textUser.setText(""); textPass.setText("");
        });

        showBtn.addActionListener(e -> {
            display.setText(password.loadVault());
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
