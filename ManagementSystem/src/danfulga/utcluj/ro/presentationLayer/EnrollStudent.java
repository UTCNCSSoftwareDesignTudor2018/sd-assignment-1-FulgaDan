package danfulga.utcluj.ro.presentationLayer;

import danfulga.utcluj.ro.businessLayer.EnrollmentBLL;
import danfulga.utcluj.ro.businessLayer.TableBLL;
import danfulga.utcluj.ro.businessLayer.validators.EnrollmentValidator;
import danfulga.utcluj.ro.dataLayer.models.Enrollment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

public class EnrollStudent extends JFrame {
    private JPanel contentPane;
    private JTextField courseID;
    private JTextField studentID;
    private JTable table;
    private JFrame exitFrame;

    public EnrollStudent(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 500);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        studentID = new JTextField();
        studentID.setBounds(105, 100, 265, 35);
        contentPane.add(studentID);
        JLabel lblStudentID = new JLabel("studentID");
        lblStudentID.setBounds(35,100,265,35);
        contentPane.add(lblStudentID);

        courseID = new JTextField();
        courseID.setBounds(105,150,265,35);
        contentPane.add(courseID);
        JLabel lblCourseID = new JLabel("courseID");
        lblCourseID.setBounds(35,150,265,35);
        contentPane.add(lblCourseID);

        JLabel lblName = new JLabel("Enroll Student Module");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblName.setBounds(20, 25, 300, 35);
        contentPane.add(lblName);

        JButton btnShowEnrollments = new JButton("Show Enrollments");
        btnShowEnrollments.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnShowEnrollments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    table.setModel(TableBLL.showEnrollmentsTable());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        btnShowEnrollments.setBounds(450,250,200,35);
        getContentPane().add(btnShowEnrollments);


        JButton btnEnrollStudent = new JButton("Enroll");
        btnEnrollStudent.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnEnrollStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String txtStudentID = studentID.getText();
                String txtCourseID = courseID.getText();

                Enrollment enrollment = new Enrollment(Integer.parseInt(txtStudentID), Integer.parseInt(txtCourseID));
                EnrollmentValidator enrollmentValidator = new EnrollmentValidator();
                if(enrollmentValidator.validate(enrollment)){
                    EnrollmentBLL.insert(enrollment);
                }
                else throw new NoSuchElementException("ex");

                try {
                    table.setModel(TableBLL.showEnrollmentsTable());
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        btnEnrollStudent.setBounds(450, 150, 200, 35);
        contentPane.add(btnEnrollStudent);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(700, 100, 400, 300);
        contentPane.add(scrollPane);
        table = new JTable();
        scrollPane.setViewportView(table);

        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitFrame = new JFrame("Exit");
                if(JOptionPane.showConfirmDialog(exitFrame, "Confirm if you want to exit", "Teacher - Add Student Module",
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
                studentID.setText(null);
                courseID.setText(null);
            }
        });
        btnReset.setBounds(450,200,200,35);
        contentPane.add(btnReset);
    }
}
