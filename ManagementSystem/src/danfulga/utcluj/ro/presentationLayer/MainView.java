package danfulga.utcluj.ro.presentationLayer;

import danfulga.utcluj.ro.businessLayer.StudentBLL;
import danfulga.utcluj.ro.businessLayer.TeacherBLL;
import danfulga.utcluj.ro.dataLayer.connection.ConnectionFactory;
import danfulga.utcluj.ro.dataLayer.models.Student;
import danfulga.utcluj.ro.dataLayer.models.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainView extends JFrame {

    private JFrame jFrame;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JFrame frmLoginSystem;
    private JCheckBox jCheckBox;
    private TeacherBLL teacherBLL;
    private StudentBLL studentBLL;

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    MainView mainView = new MainView();
                    mainView.jFrame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public MainView(){
        initialize();
    }

    private void initialize(){
        jFrame = new JFrame();
        jFrame.setBounds(200,200,650,400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Login module");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD,50 ));
        lblNewLabel.setBounds(28,11,468,66);
        jFrame.getContentPane().add(lblNewLabel);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblUsername.setBounds(59,104,172,37);
        jFrame.getContentPane().add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblPassword.setBounds(59,164,172,43);
        jFrame.getContentPane().add(lblPassword);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Tahoma", Font.BOLD, 30));
        txtUsername.setBounds(241,101,210,40);
        jFrame.getContentPane().add(txtUsername);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Tahoma", Font.BOLD, 30));
        txtPassword.setBounds(241,164,210,46);
        jFrame.getContentPane().add(txtPassword);

        jCheckBox = new JCheckBox("Login as teacher");
        jCheckBox.setBounds(241, 200, 210,46);
        jFrame.getContentPane().add(jCheckBox);


        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = txtUsername.getText();
                String password = txtPassword.getText();
                //Student student = studentBLL.findByUsername(txtUsername.getText());
                //Teacher teacher = teacherBLL.findByUsername(txtUsername.getText());
                if (!jCheckBox.isSelected()) {
                    try {
                        Student student = studentBLL.findByUsername(txtUsername.getText());
                        String query = "SELECT * FROM students WHERE username = ? AND password = ?";
                        Connection connection = ConnectionFactory.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, password);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            JOptionPane.showMessageDialog(null, "succesfull");
                            SecondView secondView = new SecondView(student);
                            secondView.main(null);

                        } else {
                            JOptionPane.showMessageDialog(null, "not succesfull");
                            txtUsername.setText("");
                            txtPassword.setText("");
                        }
                        connection.close();


                    } catch (SQLException ex) {
                        ex.printStackTrace();

                    }
                }
                else{
                    try {
                        Teacher teacher = teacherBLL.findByUsername(txtUsername.getText());
                        String query = "SELECT * FROM teachers WHERE username = ? AND password = ?";
                        Connection connection = ConnectionFactory.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, password);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            JOptionPane.showMessageDialog(null, "succesfull");
                            //Teacher teacher = teacherBLL.
                            ThirdView thirdView = new ThirdView(teacher);
                            thirdView.main(null);


                        } else {
                            JOptionPane.showMessageDialog(null, "not succesfull");
                            txtUsername.setText("");
                            txtPassword.setText("");
                        }
                        connection.close();


                    } catch (SQLException ex) {
                        ex.printStackTrace();

                    }
                }
            }
        });
        btnLogin.setBounds(59,258,137,42);
        jFrame.getContentPane().add(btnLogin);

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUsername.setText(null);
                txtPassword.setText(null);
            }
        });
        btnReset.setBounds(206,258,124,42);
        jFrame.getContentPane().add(btnReset);

        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmLoginSystem = new JFrame("Exit");
                if(JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to exit", "Login System",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
                    System.exit(0);
                }
            }
        });
        btnExit.setBounds(353,258,111,42);
        jFrame.getContentPane().add(btnExit);

        JSeparator jSeparator = new JSeparator();
        jSeparator.setBounds(50,242,570,5);
        jFrame.getContentPane().add(jSeparator);

    }


}
