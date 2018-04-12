package danfulga.utcluj.ro.businessLayer.validators;

import danfulga.utcluj.ro.dataLayer.models.Course;

public  class CourseValidator implements Validator<Course> {

    public static boolean validateCourseName(String courseName){
        return courseName.matches( "[A-Z][a-zA-Z]*" );
    }

    public static boolean validateCredit(int credit){
        if(credit >= 1 && credit <= 12) return true;
        else return false;
    }


    @Override
    public boolean validate(Course course) {
        if(!validateCourseName(course.getName())){
            throw new IllegalArgumentException("Course name is not valid");
        }
        if(!validateCredit(course.getCredits())){
            throw new IllegalArgumentException("The amount of credit is out of range(greater or equal to 1, smaller than 12");
        }
        else return true;
    }
}
