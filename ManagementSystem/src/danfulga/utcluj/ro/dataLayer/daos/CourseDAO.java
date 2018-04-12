package danfulga.utcluj.ro.dataLayer.daos;
//TODO : MERGE TOT
import danfulga.utcluj.ro.dataLayer.connection.ConnectionFactory;
import danfulga.utcluj.ro.dataLayer.models.Course;

import java.sql.*;
import java.util.ArrayList;

public class CourseDAO {

    private static final String create = "CREATE TABLE IF NOT EXISTS courses ( courseID int NOT NULL PRIMARY KEY, " +
            "name varchar(45), credits int, teacherID int FOREIGN KEY REFERENCES teachers(teacherID)";
    public static final String insert = "INSERT INTO courses (name, credits, teacherID) VALUES (?, ?, ?)";
    private final static String selectById = "SELECT * FROM courses WHERE courseID = ?";
    private final static String selectAll = "SELECT * FROM courses";
    private final static String delete = "DELETE FROM courses WHERE courseID = ?";
    private final static String update = "UPDATE courses SET name = ?, credits = ?, teacherID = ? WHERE courseID = ?";

    public void createCoursesTable() {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(create);

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(statement != null) {
                try {
                    statement.close();
                }catch (SQLException e){
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
    public static int insert(Course course){ //TODO : WORKS
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement preparedStatement = null;
        int insertedID = -1;
        try {
            preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, course.getName());
            preparedStatement.setInt(2, course.getCredits());
            preparedStatement.setInt(3, course.getTeacherID());

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

    public static void update(Course course, int courseID) { //TODO : WORKS
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(update);

            preparedStatement.setString(1, course.getName());
            preparedStatement.setInt(2, course.getCredits());
            preparedStatement.setInt(3, course.getTeacherID());
            preparedStatement.setInt(4, courseID);

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
    public static Course findById(int courseID) { //TODO : WORKS
        Course courseToReturn = null;

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement(selectById);
            preparedStatement.setInt(1, courseID);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String name = resultSet.getString("name");
            int credits = resultSet.getInt("credits");
            int teacherID = resultSet.getInt("teacherID");


            courseToReturn = new Course(name, credits, teacherID);

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
        System.out.println(courseToReturn.getCredits());
        return courseToReturn;
    }

    public static ArrayList<Course> findAll(){ //TODO : workS
        ArrayList<Course> courses = new ArrayList<Course>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectAll);

            while(resultSet.next()) {
                Course course = new Course();
                course.setCourseID(resultSet.getInt("courseID"));
                course.setName(resultSet.getString("name"));
                course.setCredits(resultSet.getInt("credits"));
                course.setTeacherID(resultSet.getInt("teacherID"));

                courses.add(course);
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
        System.out.println(courses);
        return courses;
    }

    public static void deleteById(int courseID){ // TODO : WORKS
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, courseID);
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
