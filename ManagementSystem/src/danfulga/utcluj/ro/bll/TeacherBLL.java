package danfulga.utcluj.ro.bll;

import danfulga.utcluj.ro.bll.validators.TeacherValidator;
import danfulga.utcluj.ro.bll.validators.Validator;
import danfulga.utcluj.ro.daos.TeacherDAO;
import danfulga.utcluj.ro.models.Teacher;

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

    public int insert(Teacher teacher){
        for(Validator<Teacher> teacherValidator: validators) {
            teacherValidator.validate(teacher);
        }
        return TeacherDAO.insert(teacher);
    }

    public void delete(int teacherID){
        Teacher teacher = TeacherDAO.findById(teacherID);
        if(teacher == null){
            throw new NoSuchElementException("The teacher with this ID doesn't exist");
        }
        else TeacherDAO.deleteById(teacherID);
    }

    public void update(Teacher teacher, int teacherID){
        teacher = TeacherDAO.findById(teacherID);
        if(teacher == null){
            throw new NoSuchElementException("The teacher with this ID doesn't exist");
        }
        for(Validator<Teacher> teacherValidator : validators){
            teacherValidator.validate(teacher);
        }
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
