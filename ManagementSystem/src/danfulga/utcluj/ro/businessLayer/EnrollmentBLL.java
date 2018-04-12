package danfulga.utcluj.ro.businessLayer;

import danfulga.utcluj.ro.businessLayer.validators.EnrollmentValidator;
import danfulga.utcluj.ro.businessLayer.validators.Validator;
import danfulga.utcluj.ro.dataLayer.daos.EnrollmentDAO;
import danfulga.utcluj.ro.dataLayer.models.Enrollment;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EnrollmentBLL {
    private static List<Validator<Enrollment>> validators;

    public EnrollmentBLL(){
        validators = new ArrayList<Validator<Enrollment>>();
        validators.add(new EnrollmentValidator());
    }
    public Enrollment findById(int enrollmentID){
        Enrollment enrollment = EnrollmentDAO.findById(enrollmentID);
        if(enrollment == null){
            throw new NoSuchElementException("The enrollment with the inserted ID doesn't exist");
        }
        return EnrollmentDAO.findById(enrollmentID);
    }

    public Enrollment findByStudentID(int studentID){
        Enrollment enrollment = EnrollmentDAO.findByStudentID(studentID);
        if(enrollment == null){
            throw new NoSuchElementException("This student does not exist or he has no active enrollments");
        }
        return EnrollmentDAO.findByStudentID(studentID);
    }

    public static int insert(Enrollment enrollment){
        /*for(Validator<Enrollment> enrollmentValidator: validators) {
            enrollmentValidator.validate(enrollment);
        }*/
        return EnrollmentDAO.insert(enrollment);
    }

    public void delete(int enrollmentID){
        Enrollment enrollment = EnrollmentDAO.findById(enrollmentID);
        if(enrollment == null){
            throw new NoSuchElementException("The enrollment with this ID doesn't exist");
        }
        else EnrollmentDAO.deleteById(enrollmentID);
    }

    public static void update(Enrollment enrollment, int enrollmentID){
        /*enrollment = EnrollmentDAO.findById(enrollmentID);
        if(enrollment == null){
            throw new NoSuchElementException("The enrollment with this ID doesn't exist");
        }*/
        /*for(Validator<Enrollment> enrollmentValidator : validators){
            enrollmentValidator.validate(enrollment);
        }*/
        EnrollmentDAO.update(enrollment, enrollmentID);
    }

    public ArrayList<Enrollment> findAll(){
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();
        for(Enrollment enrollment : enrollments){
            for(Validator<Enrollment> enrollmentValidator : validators){
                enrollmentValidator.validate(enrollment);
            }
            enrollments.add(enrollment);
        }
        return EnrollmentDAO.findAll();
    }

}
