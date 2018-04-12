package danfulga.utcluj.ro.presentationLayer;

import danfulga.utcluj.ro.businessLayer.StudentBLL;
import danfulga.utcluj.ro.businessLayer.TableBLL;
import danfulga.utcluj.ro.dataLayer.models.Student;
import danfulga.utcluj.ro.dataLayer.models.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondView {
    private JFrame jFrame;
    private JFrame exitFrame;
    private Student student;
    private Teacher teacher;
    private StudentBLL studentBLL;
    private JTextField studentID;
    private JTable jTable;
     public void main(String[] args){
         EventQueue.invokeLater(new Runnable() {
             @Override
             public void run() {
                 try{
                     SecondView secondView = new SecondView(student);
                     secondView.jFrame.setVisible(true);
                 }catch (Exception e){
                     e.printStackTrace();
                 }
             }
         });
     }
    public SecondView(Student student){
         this.student = student;
         initialize();
    }


    public void initialize(){
        jFrame = new JFrame();
        jFrame.setBounds(200,200,1200,500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Logged in as : " + student.getUsername());
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD,20 ));
        lblNewLabel.setBounds(28,11,545,66);
        jFrame.getContentPane().add(lblNewLabel);

        JButton btnViewProfile = new JButton("View/Update Account Information ");
        btnViewProfile.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnViewProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ViewStudentProfile viewStudentProfile = new ViewStudentProfile(student);
                viewStudentProfile.setVisible(true);

            }
        });
        btnViewProfile.setBounds(60,135,350,30);
        jFrame.getContentPane().add(btnViewProfile);

        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitFrame = new JFrame("Exit");
                if(JOptionPane.showConfirmDialog(exitFrame, "Confirm if you want to exit", "Student",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
                    System.exit(0);
                }
            }
        });
        btnExit.setBounds(60,335,350,30);
        jFrame.getContentPane().add(btnExit);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView mainView = new MainView();
                mainView.setVisible(true);
                jFrame.dispose();
            }
        });
        btnLogout.setBounds(60,295,350,30);
        jFrame.getContentPane().add(btnLogout);


        JButton btnSeeAllTeachers = new JButton("See All Teachers");
        btnSeeAllTeachers.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSeeAllTeachers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    jTable.setModel(TableBLL.showTeachersTableForStudent());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        btnSeeAllTeachers.setBounds(60,215,350,30);
        jFrame.getContentPane().add(btnSeeAllTeachers);

        JButton btnSeeAllCourses = new JButton("See All Courses");
        btnSeeAllCourses.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSeeAllCourses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    jTable.setModel(TableBLL.showCoursesTable());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        btnSeeAllCourses.setBounds(60,175,350,30);
        jFrame.getContentPane().add(btnSeeAllCourses);


        JButton btnSeeAllStudents = new JButton("See All Students");
        btnSeeAllStudents.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSeeAllStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    jTable.setModel(TableBLL.showStudentsTableForStudent());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        btnSeeAllStudents.setBounds(60,255,350,30);
        jFrame.getContentPane().add(btnSeeAllStudents);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(500, 135, 500, 300);
        //btnViewProfile.setBounds(60,135,350,30);
        jFrame.getContentPane().add(scrollPane);
        jTable = new JTable();
        scrollPane.setViewportView(jTable);
    }
}
