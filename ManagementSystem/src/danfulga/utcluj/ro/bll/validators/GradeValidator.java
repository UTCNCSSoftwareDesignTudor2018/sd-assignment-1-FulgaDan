package danfulga.utcluj.ro.bll.validators;

import danfulga.utcluj.ro.models.Grade;

public  class GradeValidator implements Validator<Grade> {

    public static boolean validateEnrollmentID(int enrollmentID){
        if(enrollmentID > 0) return true;
        else return false;
    }

    public static boolean validateGrade(int grade){
        if(grade >= 1 && grade <= 10) return true;
        else return false;
    }

    @Override
    public void validate(Grade grade) {
        if(!validateEnrollmentID(grade.getEnrollmentID())){
            throw new IllegalArgumentException("EnrollmentID is not valid");
        }
        if(!validateGrade(grade.getMark())){
            throw new IllegalArgumentException("Mark must be at least 1/ at most 10");
        }
    }
}
