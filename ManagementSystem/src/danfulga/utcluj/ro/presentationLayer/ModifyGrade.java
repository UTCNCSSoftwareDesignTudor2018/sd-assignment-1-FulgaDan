package danfulga.utcluj.ro.presentationLayer;

import danfulga.utcluj.ro.businessLayer.GradeBLL;
import danfulga.utcluj.ro.businessLayer.TableBLL;
import danfulga.utcluj.ro.businessLayer.validators.GradeValidator;
import danfulga.utcluj.ro.dataLayer.models.Grade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyGrade extends JFrame {
    private JPanel contentPane;
    private JTextField gradeID;
    private JTextField mark;
    private JTextField enrollmentID;
    private JTable jTable;
    private JFrame exitFrame;

    public ModifyGrade(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 500);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        gradeID = new JTextField();
        gradeID.setBounds(105, 100, 265, 35);
        contentPane.add(gradeID);
        JLabel lblGradeID = new JLabel("gradeID");
        lblGradeID.setBounds(35,100,265,35);
        contentPane.add(lblGradeID);

        mark = new JTextField();
        mark.setBounds(105,150,265,35);
        contentPane.add(mark);
        JLabel lblMark = new JLabel("Mark");
        lblMark.setBounds(35,150,265,35);
        contentPane.add(lblMark);

        enrollmentID = new JTextField();
        enrollmentID.setBounds(105,200,265,35);
        contentPane.add(enrollmentID);
        JLabel lblEnrollmentID = new JLabel("enrollmentID");
        lblEnrollmentID.setBounds(35,200,265,35);
        contentPane.add(lblEnrollmentID);

        JLabel lblName = new JLabel("Modify Grade Module");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblName.setBounds(20, 25, 300, 35);
        contentPane.add(lblName);

        JButton btnShowGrades = new JButton("Show Grades");
        btnShowGrades.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnShowGrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    jTable.setModel(TableBLL.showGradesTable());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        btnShowGrades.setBounds(450,100,165,35);
        contentPane.add(btnShowGrades);

        JButton btnModifyGrade = new JButton("Save Changes");
        btnModifyGrade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String txtMark = mark.getText();
                String txtEnrollmentID = enrollmentID.getText();

                int s = Integer.parseInt(gradeID.getText());
                Grade grade = new Grade(Integer.parseInt(txtMark), Integer.parseInt(txtEnrollmentID));
                GradeValidator gradeValidator = new GradeValidator();
                if(gradeValidator.validate(grade)){
                    GradeBLL.update(grade,s);
                }

                try {
                    jTable.setModel(TableBLL.showGradesTable());
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        btnModifyGrade.setBounds(450, 150, 165, 35);
        btnModifyGrade.setFont(new Font("Tahoma", Font.BOLD, 15));
        contentPane.add(btnModifyGrade);
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
                if(JOptionPane.showConfirmDialog(exitFrame, "Confirm if you want to exit", "Teacher - Remove" +
                                " Grade Module",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
                    System.exit(0);
                }
            }
        });
        btnExit.setBounds(450,250,165,35);
        contentPane.add(btnExit);

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gradeID.setText(null);
                mark.setText(null);
                enrollmentID.setText(null);

            }
        });
        btnReset.setBounds(450,200,165,36);
        contentPane.add(btnReset);
    }
}
