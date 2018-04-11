package danfulga.utcluj.ro.presentation;

import danfulga.utcluj.ro.bll.TableBLL;
import danfulga.utcluj.ro.daos.EnrollmentDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnenrollStudent extends JFrame {
    private JPanel contentPane;
    private JTextField enrollmentID;
    private JTextField courseID;
    private JTextField studentID;
    private JTable jTable;
    private JFrame exitFrame;

    public UnenrollStudent(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 500);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        enrollmentID = new JTextField();
        enrollmentID.setBounds(105, 100, 265, 35);
        contentPane.add(enrollmentID);
        JLabel lblEnrollmentID = new JLabel("enrollmentID");
        lblEnrollmentID.setBounds(35,100,265,35);
        contentPane.add(lblEnrollmentID);

        courseID = new JTextField();
        courseID.setBounds(105,150,265,35);
        contentPane.add(courseID);
        JLabel lblCourseID = new JLabel("courseID");
        lblCourseID.setBounds(35,150,265,35);
        contentPane.add(lblCourseID);

        studentID = new JTextField();
        studentID.setBounds(105,200,265,35);
        contentPane.add(studentID);
        JLabel lblStudentID = new JLabel("studentID");
        lblStudentID.setBounds(35,200,265,35);
        contentPane.add(lblStudentID);

        JLabel lblName = new JLabel("Unenroll Student Module");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblName.setBounds(20, 25, 450, 35);
        contentPane.add(lblName);

        JButton btnShowEnrollments = new JButton("Show Enrollments");
        btnShowEnrollments.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnShowEnrollments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    jTable.setModel(TableBLL.showEnrollmentsTable());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        btnShowEnrollments.setBounds(450,250,200,35);
        getContentPane().add(btnShowEnrollments);

        JButton btnUnenrollStudent = new JButton("Unenroll");
        btnUnenrollStudent.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnUnenrollStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String txtEnrollmentID = enrollmentID.getText();
                EnrollmentDAO.deleteById(Integer.parseInt(txtEnrollmentID));
                try {
                    jTable.setModel(TableBLL.showEnrollmentsTable());
                } catch (Exception ex) {
                    // TODO Auto-generated catch block
                    ex.printStackTrace();
                }

            }
        });
        btnUnenrollStudent.setBounds(450, 150, 200, 35);
        contentPane.add(btnUnenrollStudent);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(700, 100, 400, 300);
        contentPane.add(scrollPane);
        jTable = new JTable();
        scrollPane.setViewportView(jTable);

        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitFrame = new JFrame("Exit");
                if(JOptionPane.showConfirmDialog(exitFrame, "Confirm if you want to exit", "Teacher - Delete Course Module",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
                    System.exit(0);
                }
            }
        });
        btnExit.setBounds(450,300,200,35);
        contentPane.add(btnExit);

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enrollmentID.setText(null);
                studentID.setText(null);
                courseID.setText(null);
            }
        });
        btnReset.setBounds(450,200,200,36);
        contentPane.add(btnReset);
    }
        //TODO : TABLEBLL TREBUIE VALIDAT!!!!!
}
