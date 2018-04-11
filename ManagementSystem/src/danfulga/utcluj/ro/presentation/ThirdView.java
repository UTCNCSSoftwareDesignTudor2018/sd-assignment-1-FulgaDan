package danfulga.utcluj.ro.presentation;

import danfulga.utcluj.ro.bll.EnrollmentBLL;
import danfulga.utcluj.ro.bll.TableBLL;
import danfulga.utcluj.ro.models.Student;
import danfulga.utcluj.ro.models.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThirdView {
    private JFrame jFrame;
    private Teacher teacher;
    private JFrame exitFrame;
    private Student student;
    private JTable jTable;

    public void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    ThirdView thirdView = new ThirdView(teacher);
                    thirdView.jFrame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public ThirdView(Teacher teacher){
        this.teacher = teacher;
        initialize();
    }

    public void initialize(){
        jFrame = new JFrame("Teacher Module");
        jFrame.setBounds(200,200,1400,600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Logged in as : " + teacher.getUsername());
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD,20 ));
        lblNewLabel.setBounds(30,505,330,45);
        jFrame.getContentPane().add(lblNewLabel);


        JLabel lblAccountSettings = new JLabel("Account Settings");
        lblAccountSettings.setFont(new Font("Tahoma", Font.BOLD,20 ));
        lblAccountSettings.setBounds (60, 55, 200, 35);
        jFrame.getContentPane().add(lblAccountSettings);

        JLabel lblStudentActions = new JLabel("Student Actions");
        lblStudentActions.setFont(new Font("Tahoma", Font.BOLD,20 ));
        lblStudentActions.setBounds (400, 55, 200, 35);
        jFrame.getContentPane().add(lblStudentActions);

        JLabel lblCourses = new JLabel("Courses");
        lblCourses.setFont(new Font("Tahoma", Font.BOLD,20 ));
        lblCourses.setBounds (60, 250, 200, 35);
        jFrame.getContentPane().add(lblCourses);

        JLabel lblEnrollments = new JLabel("Enrollments");
        lblEnrollments.setFont(new Font("Tahoma", Font.BOLD,20 ));
        lblEnrollments.setBounds (420, 250, 200, 35);
        jFrame.getContentPane().add(lblEnrollments);

        JLabel lblGrades = new JLabel("Grades");
        lblGrades.setFont(new Font("Tahoma", Font.BOLD,20 ));
        lblGrades.setBounds (895, 55, 200, 35);
        jFrame.getContentPane().add(lblGrades);

        /*JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(750, 255, 460, 295);
        jFrame.getContentPane().add(scrollPane);
        jTable = new JTable();
        scrollPane.setViewportView(jTable);*/




        JButton btnViewProfile = new JButton("View/Update Account Information ");
        //btnViewProfile.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnViewProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ViewTeacherAccount viewTeacherAccount = new ViewTeacherAccount(teacher);
                viewTeacherAccount.setVisible(true);

            }
        });
        btnViewProfile.setBounds(30,100,265,35);
        jFrame.getContentPane().add(btnViewProfile);

        JButton btnExit = new JButton("Exit");
        //btnExit.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitFrame = new JFrame("Exit");
                if(JOptionPane.showConfirmDialog(exitFrame, "Confirm if you want to exit", "Teacher",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
                    System.exit(0);
                }
            }
        });
        btnExit.setBounds(30,140,265,35);
        jFrame.getContentPane().add(btnExit);

        JButton btnAddStudent = new JButton("Add Student");
        btnAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewStudent addNewStudent = new AddNewStudent();
                addNewStudent.setVisible(true);
            }
        });

        btnAddStudent.setBounds(400,100,265,35);
        jFrame.getContentPane().add(btnAddStudent);


        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainView mainView = new MainView();
                mainView.setVisible(true);
                jFrame.dispose();
            }
        });
        btnLogout.setBounds(30,180,265,35);
        jFrame.getContentPane().add(btnLogout);

        JButton btnDeleteStudent = new JButton("Delete Student");
        //btnDeleteStudent.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnDeleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteStudent deleteStudent = new DeleteStudent();
                deleteStudent.setVisible(true);
            }
        });
        btnDeleteStudent.setBounds(400,180,265,35);
        jFrame.getContentPane().add(btnDeleteStudent);

        JButton btnUpdate = new JButton("Update Student Info");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateStudent updateStudent = new UpdateStudent(student);
                updateStudent.setVisible(true);
            }
        });
        btnUpdate.setBounds(400, 140, 265, 35);
        jFrame.getContentPane().add(btnUpdate);

        JButton btnAddCourse = new JButton("Add Course");
        btnAddCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCourse addCourse = new AddCourse();
                addCourse.setVisible(true);
            }
        });
        btnAddCourse.setBounds(30,290,265,35);
        jFrame.getContentPane().add(btnAddCourse);

        JButton btnUpdateCourse = new JButton("Update Course");
        btnUpdateCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateCourse updateCourse = new UpdateCourse();
                updateCourse.setVisible(true);
            }
        });
        btnUpdateCourse.setBounds(30,330,265,35);
        jFrame.getContentPane().add(btnUpdateCourse);

        JButton btnDeleteCourse = new JButton("Remove Course");
        btnDeleteCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteCourse deleteCourse = new DeleteCourse();
                deleteCourse.setVisible(true);
            }
        });
        btnDeleteCourse.setBounds(30,370,265,35);
        jFrame.getContentPane().add(btnDeleteCourse);

        JButton btnAssignGrade = new JButton("Assign Grade");
        btnAssignGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AssignGrade assignGrade = new AssignGrade();
                assignGrade.setVisible(true);
            }
        });

        btnAssignGrade.setBounds(795,100,265,35);
        jFrame.getContentPane().add(btnAssignGrade);

        JButton btnModifyGrade = new JButton("Modify Grade");
        btnModifyGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyGrade modifyGrade = new ModifyGrade();
                modifyGrade.setVisible(true);
            }
        });
        btnModifyGrade.setBounds(795,140,265,35);
        jFrame.getContentPane().add(btnModifyGrade);

        JButton btnRemoveGrade = new JButton("Remove Grade");
        btnRemoveGrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveGrade removeGrade = new RemoveGrade();
                removeGrade.setVisible(true);
            }
        });
        btnRemoveGrade.setBounds(795,180,265,35);
        jFrame.getContentPane().add(btnRemoveGrade);

        JButton btnEnroll = new JButton("Enroll Student");
        btnEnroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnrollStudent enrollStudent = new EnrollStudent();
                enrollStudent.setVisible(true);
            }
        });
        btnEnroll.setBounds(400,290,265,35);
        jFrame.getContentPane().add(btnEnroll);

        JButton btnUnenroll = new JButton("Unenroll Student");
        btnUnenroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UnenrollStudent unenrollStudent = new UnenrollStudent();
                unenrollStudent.setVisible(true);
            }
        });
        btnUnenroll.setBounds(400,330,265,35);
        jFrame.getContentPane().add(btnUnenroll);

        JButton btnSeeAllEnrollments = new JButton("See Enrollments");
        btnSeeAllEnrollments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    jTable.setModel(TableBLL.showEnrollmentsTable());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
        }});
        btnSeeAllEnrollments.setBounds(400,370,265,35);
        jFrame.getContentPane().add(btnSeeAllEnrollments);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(750, 255, 460, 295);
        jFrame.getContentPane().add(scrollPane);
        jTable = new JTable();
        scrollPane.setViewportView(jTable);

    }
}

