package danfulga.utcluj.ro.presentationLayer;

import danfulga.utcluj.ro.businessLayer.StudentBLL;
import danfulga.utcluj.ro.businessLayer.TableBLL;
import danfulga.utcluj.ro.businessLayer.validators.StudentValidator;
import danfulga.utcluj.ro.dataLayer.models.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

public class UpdateStudent extends JFrame {
    private JPanel contentPane;
    private JTextField studentID;
    private JTextField name;
    private JTextField username;
    private JTextField password;
    private JTextField address;
    private JTextField phoneNo;
    private JTextField PNC;
    private JTextField ICN;
    private JTextField currentYear;
    private JTable table;
    private Student student;
    private JFrame exitFrame;

    public UpdateStudent(Student student){
        this.student = student;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1400, 600);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        name = new JTextField();
        name.setBounds(105, 100, 265, 35);
        contentPane.add(name);
        JLabel lblName2 = new JLabel("Name");
        lblName2.setBounds(35,100,265,35);
        contentPane.add(lblName2);

        address = new JTextField();
        address.setBounds(105,150,265,35);
        contentPane.add(address);
        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(35,150,265,35);
        contentPane.add(lblAddress);

        phoneNo = new JTextField();
        phoneNo.setBounds(105,200,265,35);
        contentPane.add(phoneNo);
        JLabel lblPhoneNo = new JLabel("PhoneNo");
        lblPhoneNo.setBounds(35,200,265,35);
        contentPane.add(lblPhoneNo);

        PNC = new JTextField();
        PNC.setBounds(105,250,265,35);
        contentPane.add(PNC);
        JLabel lblPNC = new JLabel("PNC");
        lblPNC.setBounds(35,250,265,35);
        contentPane.add(lblPNC);

        ICN = new JTextField();
        ICN.setBounds(105,300,265,35);
        contentPane.add(ICN);
        JLabel lblICN = new JLabel("ICN");
        lblICN.setBounds(35,300,265,35);
        contentPane.add(lblICN);

        username = new JTextField();
        username.setBounds(105, 350, 265, 35);
        contentPane.add(username);
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(35,350,265,35);
        contentPane.add(lblUsername);

        password = new JTextField();
        password.setBounds(105, 400, 265, 35);
        contentPane.add(password);
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(35,400,265,35);
        contentPane.add(lblPassword);

        currentYear = new JTextField();
        currentYear.setBounds(105,450,265,35);
        contentPane.add(currentYear);
        JLabel lblCurrentYear = new JLabel("CurrentYear");
        lblCurrentYear.setBounds(35,450,265,35);
        contentPane.add(lblCurrentYear);

        studentID = new JTextField(10);
        studentID.setBounds(105,500,265,35);
        contentPane.add(studentID);
        JLabel lblStudentID = new JLabel("studentID");
        lblStudentID.setBounds(35,500,265,35);
        contentPane.add(lblStudentID);

        JLabel lblName = new JLabel("Update Student Info using studentID");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblName.setBounds(20, 25, 500, 35);
        contentPane.add(lblName);

        JButton btnUpdateStudent = new JButton("Update");
        btnUpdateStudent.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnUpdateStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameText = name.getText();
                String usernameText = username.getText();
                String passwordText = password.getText();
                String addressText = address.getText();
                String phoneNoText = phoneNo.getText();
                String PNCText = PNC.getText();
                String ICNText = ICN.getText();
                String currentYearText = currentYear.getText();

                int s = Integer.parseInt(studentID.getText());
                Student student1 = new Student(nameText, addressText, phoneNoText, Integer.parseInt(PNCText), Integer.parseInt(ICNText),
                        usernameText, passwordText, Integer.parseInt(currentYearText));
                StudentValidator studentValidator = new StudentValidator();
                if(studentValidator.validate(student1)){
                    StudentBLL.update(student1, s);
                }
                else throw new NoSuchElementException("??");

                //StudentDAO.update(new Student(nameText, addressText, phoneNoText, Integer.parseInt(PNCText), Integer.parseInt(ICNText),
                        //usernameText, passwordText, Integer.parseInt(currentYearText)), s);

                try {
                    table.setModel(TableBLL.showStudentsTable());
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        btnUpdateStudent.setBounds(450, 150, 165, 35);
        contentPane.add(btnUpdateStudent);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(700, 140, 600, 300);
        contentPane.add(scrollPane);
        table = new JTable();
        scrollPane.setViewportView(table);

        JButton btnShowStudents = new JButton("Show Students");
        btnShowStudents.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnShowStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    table.setModel(TableBLL.showStudentsTable());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        btnShowStudents.setBounds(450,250,165,35);
        getContentPane().add(btnShowStudents);

        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitFrame = new JFrame("Exit");
                if(JOptionPane.showConfirmDialog(exitFrame, "Confirm if you want to exit", "Teacher - Delete Student Module",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
                    System.exit(0);
                }
            }
        });
        btnExit.setBounds(450,300,165,35);
        contentPane.add(btnExit);

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.setText(null);
                address.setText(null);
                phoneNo.setText(null);
                PNC.setText(null);
                ICN.setText(null);
                username.setText(null);
                password.setText(null);
                currentYear.setText(null);
                studentID.setText(null);
            }
        });
        btnReset.setBounds(450,200,165,35);
        contentPane.add(btnReset);
    }
}
