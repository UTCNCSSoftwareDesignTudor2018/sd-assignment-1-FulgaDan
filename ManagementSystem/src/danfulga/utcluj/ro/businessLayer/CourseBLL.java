package danfulga.utcluj.ro.businessLayer;

import danfulga.utcluj.ro.businessLayer.validators.CourseValidator;
import danfulga.utcluj.ro.businessLayer.validators.Validator;
import danfulga.utcluj.ro.dataLayer.daos.CourseDAO;
import danfulga.utcluj.ro.dataLayer.models.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CourseBLL {
    private static List<Validator<Course>> validators;

    public CourseBLL(){
        validators = new ArrayList<Validator<Course>>();
        validators.add(new CourseValidator());
    }
    public Course findById(int courseID){
        Course course = CourseDAO.findById(courseID);
        if(course == null){
            throw new NoSuchElementException("The course with the inserted ID doesn't exist");
        }
        return CourseDAO.findById(courseID);
    }

    public static int insert(Course course){
        /*for(Validator<Course> courseValidator : validators) {
            courseValidator.validate(course);
        }*/
        return CourseDAO.insert(course);
    }

    public void delete(int courseID){
        Course course = CourseDAO.findById(courseID);
        if(course == null){
            throw new NoSuchElementException("The course with this ID doesn't exist");
        }
        else CourseDAO.deleteById(courseID);
    }

    public static void update(Course course, int courseID){
        /*course = CourseDAO.findById(courseID);
        if(course == null){
            throw new NoSuchElementException("The course with this ID doesn't exist");
        }*/
        /*for(Validator<Course> courseValidator : validators){
            courseValidator.validate(course);
        }*/
        CourseDAO.update(course, courseID);
    }

    public ArrayList<Course> findAll(){
        ArrayList<Course> courses = new ArrayList<Course>();
        for(Course course : courses){
            for(Validator<Course> courseValidator : validators){
                courseValidator.validate(course);
            }
            courses.add(course);
        }
        return CourseDAO.findAll();
    }
}
