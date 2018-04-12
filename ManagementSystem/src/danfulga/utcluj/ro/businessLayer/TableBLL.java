package danfulga.utcluj.ro.businessLayer;

import danfulga.utcluj.ro.dataLayer.daos.TableDAO;

import javax.swing.table.TableModel;

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
