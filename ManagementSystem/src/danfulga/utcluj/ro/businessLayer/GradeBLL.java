package danfulga.utcluj.ro.businessLayer;

import danfulga.utcluj.ro.businessLayer.validators.GradeValidator;
import danfulga.utcluj.ro.businessLayer.validators.Validator;
import danfulga.utcluj.ro.dataLayer.daos.GradeDAO;
import danfulga.utcluj.ro.dataLayer.models.Grade;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class GradeBLL {
    private static List<Validator<Grade>> validators;

    public GradeBLL(){
        validators = new ArrayList<>();
        validators.add(new GradeValidator());
    }
    public Grade findById(int gradeID){
        Grade grade = GradeDAO.findById(gradeID);
        if(grade == null){
            throw new NoSuchElementException("The grade with the inserted ID doesn't exist");
        }
        return GradeDAO.findById(gradeID);
    }

    public static int insert(Grade grade){
        /*for(Validator<Grade> gradeValidator: validators) {
            gradeValidator.validate(grade);
        }*/
        return GradeDAO.insert(grade);
    }

    public void delete(int gradeID){
        Grade grade = GradeDAO.findById(gradeID);
        if(grade == null){
            throw new NoSuchElementException("The grade with this ID doesn't exist");
        }
        else GradeDAO.deleteById(gradeID);
    }

    public static void update(Grade grade, int gradeID){
        /*grade = GradeDAO.findById(gradeID);
        if(grade == null){
            throw new NoSuchElementException("The grade with this ID doesn't exist");
        }*/
        /*for(Validator<Grade> gradeValidator : validators){
            gradeValidator.validate(grade);
        }*/
        GradeDAO.update(grade, gradeID);
    }

    public ArrayList<Grade> findAll(){
        ArrayList<Grade> grades = new ArrayList<Grade>();
        for(Grade grade : grades){
            for(Validator<Grade> gradeValidator : validators){
                gradeValidator.validate(grade);
            }
            grades.add(grade);
        }
        return GradeDAO.findAll();
    }
}
