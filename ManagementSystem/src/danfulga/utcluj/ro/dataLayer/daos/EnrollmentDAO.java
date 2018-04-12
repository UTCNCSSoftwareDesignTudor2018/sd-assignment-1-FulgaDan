package danfulga.utcluj.ro.dataLayer.daos;
//TODO : MERGE TOT
import danfulga.utcluj.ro.dataLayer.connection.ConnectionFactory;
import danfulga.utcluj.ro.dataLayer.models.Enrollment;

import java.sql.*;
import java.util.ArrayList;

public class EnrollmentDAO {
    private static final String create = "CREATE TABLE IF NOT EXISTS enrollments ( enrollmentsID int NOT NULL PRIMARY KEY, " +
            "studentID int FOREIGN KEY REFERENCES students(studentID), courseID int FOREIGN KEY REFERENCES courses(courseID)";
    public static final String insert = "INSERT INTO enrollments (studentID, courseID) VALUES (?, ?)";
    private final static String selectById = "SELECT * FROM enrollments WHERE enrollmentID = ?";
    private final static String selectAll = "SELECT * FROM enrollments";
    private final static String delete = "DELETE FROM enrollments WHERE enrollmentID = ?";
    private final static String update = "UPDATE enrollments SET studentID = ?, courseID = ? WHERE enrollmentID = ?";
    private final static String findByStudentID = "SELECT * FROM enrollments WHERE studentID = ?";

    StudentDAO studentDAO = new StudentDAO();
    CourseDAO courseDAO = new CourseDAO();

    public static void createEnrollmentTable(){
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

    public static int insert(Enrollment enrollment){ //TODO : WORKS
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement preparedStatement = null;
        int insertedID = -1;
        try {
            preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, enrollment.getStudentID());
            preparedStatement.setInt(2, enrollment.getCourseID());

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

    public static void update(Enrollment enrollment, int enrollmentID) { //TODO : WORKS
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(update);

            preparedStatement.setInt(1, enrollment.getStudentID());
            preparedStatement.setInt(2, enrollment.getCourseID());
            preparedStatement.setInt(3, enrollmentID);


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
    public static Enrollment findByStudentID(int studentID){ //TODO : WORKS
        Enrollment enrollmentToReturn = null;

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement(findByStudentID);
            preparedStatement.setInt(1, studentID);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int enrollmentID = resultSet.getInt("enrollmentID");
            int courseID = resultSet.getInt("courseID");

            enrollmentToReturn = new Enrollment(enrollmentID, courseID);

        }catch (SQLException e){
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
        System.out.println(enrollmentToReturn.getCourseID());
        return enrollmentToReturn;
    }

    public static Enrollment findById(int enrollmentID) { //TODO : WORKS
        Enrollment enrollmentToReturn = null;

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement(selectById);
            preparedStatement.setInt(1, enrollmentID);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int studentID = resultSet.getInt("studentID");
            int courseID = resultSet.getInt("courseID");

            enrollmentToReturn = new Enrollment(studentID,courseID);

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
        System.out.println(enrollmentToReturn.getCourseID());
        return enrollmentToReturn;
    }

    public static ArrayList<Enrollment> findAll(){ //TODO : WORKS
        ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectAll);

            while(resultSet.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setEnrollmentID(resultSet.getInt("enrollmentID"));
                enrollment.setStudentID(resultSet.getInt("studentID"));
                enrollment.setCourseID(resultSet.getInt("courseID"));

                enrollments.add(enrollment);
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
        System.out.println(enrollments);
        return enrollments;
    }

    public static void deleteById(int enrollmentID){ //TODO : WORKS
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, enrollmentID);
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
