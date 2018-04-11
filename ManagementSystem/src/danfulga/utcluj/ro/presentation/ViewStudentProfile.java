package danfulga.utcluj.ro.presentation;

import danfulga.utcluj.ro.bll.StudentBLL;

import danfulga.utcluj.ro.daos.StudentDAO;
import danfulga.utcluj.ro.models.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ViewStudentProfile extends JFrame {
    private JLabel lblName;
    private JLabel lblAddress;
    private JLabel lblPhoneNo;
    private JLabel lblPNC;
    private JLabel lblICN;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblCurrentYear;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtICN;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTextField txtCurrentYear;
    private JTextField txtPhoneNo;
    private JTextField txtPNC;
    private Student student;
    private StudentBLL studentBLL;
    private JButton editButton;
    private JButton backButton;
    private JButton exitButton;
    private StudentDAO studentDAO;
    private JFrame exitFrame;

    public ViewStudentProfile(Student student) {
        super("Welcome  " + student.getName());
        //construct components
        this.student = student;
        this.studentBLL = new StudentBLL();
        lblName = new JLabel ("Name");
        lblAddress = new JLabel ("Address");
        lblPhoneNo = new JLabel ("PhoneNo");
        lblPNC = new JLabel ("PNC");
        lblICN = new JLabel ("ICN");
        lblUsername = new JLabel ("Username");
        lblPassword = new JLabel ("Password");
        lblCurrentYear = new JLabel ("CurrentYear");
        txtName = new JTextField (0);
        txtAddress = new JTextField (0);
        txtICN = new JTextField (0);
        txtUsername = new JTextField (0);
        txtPassword = new JTextField (0);
        txtCurrentYear = new JTextField (0);
        txtPhoneNo = new JTextField (0);
        txtPNC = new JTextField (0);
        editButton = new JButton("Save changes");
        backButton  = new JButton("Go back to previous page");
        exitButton = new JButton("Exit");
        //adjust size and set layout
        setPreferredSize (new Dimension (700, 450));
        setLayout (null);

        //add components
        add (lblName);
        add (lblAddress);
        add (lblPhoneNo);
        add (lblPNC);
        add (lblICN);
        add (lblUsername);
        add (lblPassword);
        add (lblCurrentYear);
        add (txtName);
        add (txtAddress);
        add (txtICN);
        add (txtUsername);
        add (txtPassword);
        add (txtCurrentYear);
        add (txtPhoneNo);
        add (txtPNC);
        add (editButton);
        add (backButton);
        add (exitButton);

        //set component bounds (only needed by Absolute Positioning)
        lblName.setBounds (55, 65, 100, 25);
        lblAddress.setBounds (55, 100, 100, 25);
        lblPhoneNo.setBounds (55, 130, 100, 25);
        lblPNC.setBounds (55, 170, 100, 25);
        lblICN.setBounds (55, 205, 100, 25);
        lblUsername.setBounds (55, 240, 100, 25);
        lblPassword.setBounds (55, 275, 100, 25);
        lblCurrentYear.setBounds (55, 310, 100, 25);
        txtName.setBounds (145, 65, 100, 25);
        txtName.setText(student.getName());
        txtAddress.setBounds (145, 100, 100, 25);
        txtAddress.setText(student.getAddress());
        txtICN.setBounds (145, 205, 100, 25);
        txtICN.setText(String.valueOf(student.getICN()));
        txtUsername.setBounds (145, 240, 100, 25);
        txtUsername.setText(student.getUsername());
        txtPassword.setBounds (145, 275, 100, 25);
        txtPassword.setText(student.getPassword());
        txtCurrentYear.setBounds (145, 310, 100, 25);
        txtCurrentYear.setText(String.valueOf(student.getCurrentYear()));
        txtPhoneNo.setBounds (145, 130, 100, 25);
        txtPhoneNo.setText(student.getPhoneNo());
        txtPNC.setBounds (145, 170, 100, 25);
        txtPNC.setText(String.valueOf(student.getPNC()));
        editButton.setBounds(430,65,155,30);
        backButton.setBounds(395,100,230,30);
        exitButton.setBounds(430,135,155,30);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Student student1 = new Student(student.getStudentID(), txtName.getText(),txtAddress.getText(),
                            txtPhoneNo.getText(),student.getPNC(), student.getICN(), student.getUsername(),
                            student.getPassword(), student.getCurrentYear());
                    studentDAO.update(student1, student.getStudentID());
                    JOptionPane.showMessageDialog(null, "Information updated!", "", JOptionPane.INFORMATION_MESSAGE);
                    //TODO : AICI TREBUIE MODIFICAT CU studentBLL.update.... sa vad doar sa pastrez validatorii

                }catch (IllegalArgumentException el){
                    el.printStackTrace();
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitFrame = new JFrame("Exit");
                if(JOptionPane.showConfirmDialog(exitFrame, "Confirm if you want to exit", "Student info",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
                    System.exit(0);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SecondView secondView = new SecondView(student);
                secondView.main(null);
                dispose();
            }
        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
