package danfulga.utcluj.ro.presentation;

import danfulga.utcluj.ro.bll.TableBLL;
import danfulga.utcluj.ro.daos.CourseDAO;
import danfulga.utcluj.ro.models.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteCourse extends JFrame {
    private JPanel contentPane;
    private JTextField name;
    private JTextField credits;
    private JTextField teacherID;
    private JTextField courseID;
    private JTable table;
    private JFrame exitFrame;

    public DeleteCourse(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 500);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        name = new JTextField();
        name.setBounds(105, 100, 265, 35);
        contentPane.add(name);
        JLabel lblName2 = new JLabel("Name");
        lblName2.setBounds(35,100,265,35);
        contentPane.add(lblName2);

        credits = new JTextField();
        credits.setBounds(105,150,265,35);
        contentPane.add(credits);
        JLabel lblCredits = new JLabel("Credits");
        lblCredits.setBounds(35,150,265,35);
        contentPane.add(lblCredits);

        teacherID = new JTextField();
        teacherID.setBounds(105,200,265,35);
        contentPane.add(teacherID);
        JLabel lblTeacherID = new JLabel("teacherID");
        lblTeacherID.setBounds(35,200,265,35);
        contentPane.add(lblTeacherID);

        courseID = new JTextField();
        courseID.setBounds(105,250,265,35);
        contentPane.add(courseID);
        JLabel lblCourseID = new JLabel("courseID");
        lblCourseID.setBounds(35,250,265,35);
        contentPane.add(lblCourseID);

        JLabel lblName = new JLabel("Only the courseID needs to be inserted!");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblName.setBounds(20, 25, 550, 35);
        contentPane.add(lblName);

        JButton btnShowCourses = new JButton("Show Courses");
        btnShowCourses.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnShowCourses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    table.setModel(TableBLL.showCoursesTable());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        btnShowCourses.setBounds(450,250,165,35);
        getContentPane().add(btnShowCourses);

        JButton btnDeleteCourse = new JButton("Delete");
        btnDeleteCourse.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnDeleteCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String txtCourseID = courseID.getText();
                CourseDAO.deleteById(Integer.parseInt(txtCourseID));
                try {
                    table.setModel(TableBLL.showCoursesTable());
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        btnDeleteCourse.setBounds(450, 150, 165, 35);
        contentPane.add(btnDeleteCourse);
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
                if(JOptionPane.showConfirmDialog(exitFrame, "Confirm if you want to exit", "Teacher - Delete Course Module",
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
                credits.setText(null);
                teacherID.setText(null);
                courseID.setText(null);

            }
        });
        btnReset.setBounds(450,200,165,36);
        contentPane.add(btnReset);
    }
}
