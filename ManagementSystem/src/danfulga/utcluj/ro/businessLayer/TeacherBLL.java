package danfulga.utcluj.ro.businessLayer;

import danfulga.utcluj.ro.businessLayer.validators.TeacherValidator;
import danfulga.utcluj.ro.businessLayer.validators.Validator;
import danfulga.utcluj.ro.dataLayer.daos.TeacherDAO;
import danfulga.utcluj.ro.dataLayer.models.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class TeacherBLL {
    private List<Validator<Teacher>> validators;

    public TeacherBLL(){
        validators = new ArrayList<Validator<Teacher>>();
        validators.add(new TeacherValidator());
    }
    public Teacher findById(int teacherID){
        Teacher teacher = TeacherDAO.findById(teacherID);
        if(teacher == null){
            throw new NoSuchElementException("The teacher with the inserted ID doesn't exist");
        }
        return TeacherDAO.findById(teacherID);
    }

    public static int insert(Teacher teacher){
        /*for(Validator<Teacher> teacherValidator: validators) {
            teacherValidator.validate(teacher);
        }*/
        return TeacherDAO.insert(teacher);
    }

    public void delete(int teacherID){
        Teacher teacher = TeacherDAO.findById(teacherID);
        if(teacher == null){
            throw new NoSuchElementException("The teacher with this ID doesn't exist");
        }
        else TeacherDAO.deleteById(teacherID);
    }

    public static void update(Teacher teacher, int teacherID){
        /*teacher = TeacherDAO.findById(teacherID);
        if(teacher == null){
            throw new NoSuchElementException("The teacher with this ID doesn't exist");
        }*/
        /*for(Validator<Teacher> teacherValidator : validators){
            teacherValidator.validate(teacher);
        }*/
        TeacherDAO.update(teacher, teacherID);
    }

    public ArrayList<Teacher> findAll(){
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        for(Teacher teacher : teachers){
            for(Validator<Teacher> teacherValidator : validators){
                teacherValidator.validate(teacher);
            }
            teachers.add(teacher);
        }
        return TeacherDAO.findAll();
    }

    public static Teacher findByUsername(String username){
        Teacher teacher = TeacherDAO.findByUsername(username);
        if(teacher == null){
            throw new NoSuchElementException("The teacher with this username doesn't exist");
        }
        return TeacherDAO.findByUsername(username);
    }
}
