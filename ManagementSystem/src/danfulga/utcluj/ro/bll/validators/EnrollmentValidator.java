package danfulga.utcluj.ro.bll.validators;

import danfulga.utcluj.ro.models.Enrollment;

public  class EnrollmentValidator implements Validator<Enrollment> {

    public static boolean validateStudentID(int studentID){
        if(studentID > 0) return true;
        else return false;
    }

    public static boolean validateCourseID(int courseID){
        if(courseID > 0) return true;
        else return false;
    }

    @Override
    public void validate(Enrollment enrollment) {
        if(!validateCourseID(enrollment.getCourseID())){
            throw new IllegalArgumentException("CourseID is not valid");
        }
        if(!validateStudentID(enrollment.getStudentID())){
            throw new IllegalArgumentException("StudentID is not valid");
        }
    }
}
