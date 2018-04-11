package danfulga.utcluj.ro.daos;

import danfulga.utcluj.ro.connection.ConnectionFactory;
import danfulga.utcluj.ro.models.Enrollment;
import net.proteanit.sql.DbUtils;

import javax.swing.table.TableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableDAO {

    private static final String selectAllStudents = "SELECT * FROM students";
    private static final String selectAllTeachers = "SELECT * FROM teachers";
    private static final String selectAllCourses = "SELECT * FROM courses";
    private static final String selectAllGrades = "SELECT * FROM grades";
    private static final String selectAllEnrolments = "SELECT * FROM enrollments";
    private static final String selectEnrollmentsForStudent = "SELECT * FROM enrollments WHERE studentID = ?";
    private static final String selectAllStudentsForStudent = "SELECT studentID, name, address, username, currentYear FROM students";
    private static final String selectAllTeachersForStudent = "SELECT teacherID, name, phoneNo FROM teachers ";

    public static TableModel getEnrollmentsTableForStudent(int studentID){

        //Enrollment enrollment = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectEnrollmentsForStudent);
            preparedStatement.setInt(1, studentID);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            //int enrollmentID = resultSet.getInt("enrollmentID");
            //int courseID = resultSet.getInt("courseID");

            //enrollment = new Enrollment(enrollmentID, courseID);

            return DbUtils.resultSetToTableModel(resultSet);
        }catch (SQLException e) {
        e.printStackTrace();
        }finally{
        if(resultSet != null){
            try{
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(preparedStatement != null){
            try{
                preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(connection != null){
            try{
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
        return null;
    }

    public static TableModel getStudentsTableForStudent() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectAllStudentsForStudent);
            resultSet = preparedStatement.executeQuery();

            return DbUtils.resultSetToTableModel(resultSet);

        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static TableModel getTeachersTableForStudent() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectAllTeachersForStudent);
            resultSet = preparedStatement.executeQuery();

            return DbUtils.resultSetToTableModel(resultSet);

        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static TableModel getStudentsTable() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectAllStudents);
            resultSet = preparedStatement.executeQuery();

            return DbUtils.resultSetToTableModel(resultSet);

        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static TableModel getTeachersTable() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectAllTeachers);
            resultSet = preparedStatement.executeQuery();

            return DbUtils.resultSetToTableModel(resultSet);

        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public static TableModel getEnrollmentsTable() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectAllEnrolments);
            resultSet = preparedStatement.executeQuery();

            return DbUtils.resultSetToTableModel(resultSet);

        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static TableModel getGradesTable() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectAllGrades);
            resultSet = preparedStatement.executeQuery();

            return DbUtils.resultSetToTableModel(resultSet);

        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static TableModel getCoursesTable() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectAllCourses);
            resultSet = preparedStatement.executeQuery();

            return DbUtils.resultSetToTableModel(resultSet);

        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null){
                try{
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
