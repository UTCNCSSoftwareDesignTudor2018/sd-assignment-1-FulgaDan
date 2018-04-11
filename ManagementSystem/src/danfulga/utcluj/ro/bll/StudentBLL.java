package danfulga.utcluj.ro.bll;

import danfulga.utcluj.ro.bll.validators.StudentValidator;
import danfulga.utcluj.ro.bll.validators.Validator;
import danfulga.utcluj.ro.daos.StudentDAO;
import danfulga.utcluj.ro.models.Student;

import javax.swing.table.TableModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StudentBLL {
    private static List<Validator<Student>> validators;

    public StudentBLL(){
        validators = new ArrayList<Validator<Student>>();
        validators.add(new StudentValidator());
    }

    public Student findById(int studentID){
        Student student = StudentDAO.findById(studentID);
        if(student == null){
            throw new NoSuchElementException("The student with the inserted ID doesn't exist");
        }
        return StudentDAO.findById(studentID);
    }

    public static int insert(Student student){
        for(Validator<Student> studentValidator: validators) {
            studentValidator.validate(student);
        }
        return StudentDAO.insert(student);
    }

    public void delete(int studentID){
        Student student = StudentDAO.findById(studentID);
        if(student == null){
            throw new NoSuchElementException("The student with this ID doesn't exist");
        }
        else StudentDAO.deleteById(studentID);
    }

    public void update(Student student, int studentID){
        student = StudentDAO.findById(studentID);
        if(student == null){
            throw new NoSuchElementException("The student with this ID doesn't exist");
        }
        for(Validator<Student> studentValidator : validators){
            studentValidator.validate(student);
        }
        StudentDAO.update(student, studentID);
    }

    public ArrayList<Student> findAll(){
        ArrayList<Student> students = new ArrayList<Student>();
        for(Student student : students){
            for(Validator<Student> studentValidator : validators){
                studentValidator.validate(student);
            }
            students.add(student);
        }
        return StudentDAO.findAll();
    }

    public Student validateAccount(int studentID, String username, String password) {
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.findById(studentID);
        if (student == null) {
            throw new NoSuchElementException("The student with this id doesn't exist");
        }
        if (!(student.getUsername().equals(username)) && student.getPassword().equals(password)) {
                throw new NoSuchElementException("Wrong password");
        }
        return student;
    }

    public static Student findByUsername(String username){
        Student student = StudentDAO.findByUsername(username);
        if(student == null){
            throw new NoSuchElementException("The student with this username doesn't exist");
        }
        return StudentDAO.findByUsername(username);
    }

    /*public static TableModel readStudentsTable(){
        return StudentDAO.getStudentsTable();
    }*/
}
