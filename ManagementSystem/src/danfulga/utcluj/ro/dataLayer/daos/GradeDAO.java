package danfulga.utcluj.ro.dataLayer.daos;
//TODO : MERGE TOT
import danfulga.utcluj.ro.dataLayer.connection.ConnectionFactory;
import danfulga.utcluj.ro.dataLayer.models.Grade;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class GradeDAO {
    protected static final Logger LOGGER = Logger.getLogger(GradeDAO.class.getName());

    private static final String create = "CREATE TABLE IF NOT EXISTS grades ( gradeID int NOT NULL PRIMARY KEY, " +
            "mark int, enrollmentID int FOREIGN KEY REFERENCES enrollments(enrollmentID) )";

    public static final String insert = "INSERT INTO grades (mark, enrollmentID) VALUES (?, ?)";
    private final static String selectById = "SELECT * FROM grades WHERE gradeID = ?";
    private final static String selectAll = "SELECT * FROM grades";
    private final static String delete = "DELETE FROM grades WHERE gradeID = ?";
    private final static String update = "UPDATE grades SET mark = ?,enrollmentID = ? WHERE gradeID = ?";

    public static void createGradesTable(){
        Connection connection = null;
        Statement statement = null;
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(create);

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(statement != null){
                try{
                    statement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static int insert(Grade grade){ //TODO : WORKS
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement preparedStatement = null;
        int insertedID = -1;
        try {
            preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, grade.getMark());
            preparedStatement.setInt(2, grade.getEnrollmentID());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                insertedID = resultSet.getInt(1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return insertedID;
    }

    public static void update(Grade grade, int gradeID) { //TODO : WORKS
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(update);

            preparedStatement.setInt(1, grade.getMark());
            preparedStatement.setInt(2, grade.getEnrollmentID());
            preparedStatement.setInt(3, gradeID);


            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static Grade findById(int gradeID) { //TODO : WORKS
        Grade gradeToReturn = null;

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement(selectById);
            preparedStatement.setInt(1, gradeID);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int mark = resultSet.getInt("mark");
            int enrollmentID = resultSet.getInt("enrollmentID");

            gradeToReturn = new Grade(mark, enrollmentID);

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(resultSet != null) {
                try{
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(gradeToReturn.getMark());
        return gradeToReturn;
    }

    public static ArrayList<Grade> findAll(){ //TODO : WORKS
        ArrayList<Grade> grades = new ArrayList<Grade>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectAll);

            while(resultSet.next()) {
                Grade grade = new Grade();
                grade.setGradeID(resultSet.getInt("gradeID"));
                grade.setMark(resultSet.getInt("mark"));
                grade.setEnrollmentID(resultSet.getInt("enrollmentID"));

                grades.add(grade);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try{
                    statement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(grades);
        return grades;
    }

    public static void deleteById(int gradeID){ //TODO : WORKS
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, gradeID);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(preparedStatement != null) {
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
    }
}
