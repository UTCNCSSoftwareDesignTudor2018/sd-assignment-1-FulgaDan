package danfulga.utcluj.ro.bll;

import danfulga.utcluj.ro.daos.StudentDAO;
import danfulga.utcluj.ro.daos.TableDAO;
import danfulga.utcluj.ro.models.Student;
import javafx.scene.control.Tab;

import javax.swing.table.TableModel;
import java.util.NoSuchElementException;

public class TableBLL {
    public static TableModel showStudentsTable() {
        return TableDAO.getStudentsTable();
    }

    public static TableModel showTeachersTable() {
        return TableDAO.getTeachersTable();
    }

    public static TableModel showEnrollmentsTable() {
        return TableDAO.getEnrollmentsTable();
    }

    public static TableModel showCoursesTable() {
        return TableDAO.getCoursesTable();
    }

    public static TableModel showGradesTable() {
        return TableDAO.getGradesTable();
    }

    public static TableModel showStudentsTableForStudent(){
        return TableDAO.getStudentsTableForStudent();
    }

    public static TableModel showTeachersTableForStudent(){
        return TableDAO.getTeachersTableForStudent();
    }


}
