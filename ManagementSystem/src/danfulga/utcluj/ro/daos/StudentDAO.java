package danfulga.utcluj.ro.daos;
//TODO : merge tot
import danfulga.utcluj.ro.connection.ConnectionFactory;
import danfulga.utcluj.ro.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;

import javax.swing.table.TableModel;

public class StudentDAO {

    protected static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

    private static final String create = "CREATE TABLE IF NOT EXISTS students ( studentID int NOT NULL PRIMARY KEY, " +
            "name varchar(45), address varchar(45), phoneNo varchar(45), PNC int(13), ICN int(5), username varchar(45)" +
            "password varchar(45), currentYear int)";

    public static final String insert = "INSERT INTO students (name, address, phoneNo, PNC, ICN, username, password," +
            "currentYear) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String selectById = "SELECT * FROM students WHERE studentID = ?";
    private final static String selectAll = "SELECT * FROM students";
    private final static String delete = "DELETE FROM students WHERE studentID = ?";
    private final static String updatee = "UPDATE students SET name = ?, address = ?, phoneNo = ?, PNC = ?, ICN =?, username = ?, password = ?, currentYear = ? WHERE studentID = ?";
    private final static String selectByUsername = "SELECT * FROM students WHERE username = ?";


    public static void createStudentsTable(){
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


    public static int insert(Student student){ //TODO : WORKS
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement preparedStatement = null;
        int insertedID = -1;
        try {
            preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getAddress());
            preparedStatement.setString(3, student.getPhoneNo());
            preparedStatement.setInt(4, student.getPNC());
            preparedStatement.setInt(5, student.getICN());
            preparedStatement.setString(6, student.getUsername());
            preparedStatement.setString(7, student.getPassword());
            preparedStatement.setInt(8, student.getCurrentYear());

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

    public static void update(Student student , int studentID) { //TODO : WORKS
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement update = null;

        try {

            update = connection.prepareStatement(updatee, Statement.RETURN_GENERATED_KEYS);

            update.setString(1,student.getName());
            update.setString(2,student.getAddress());
            update.setString(3,student.getPhoneNo());
            update.setInt(4, student.getPNC());
            update.setInt(5, student.getICN());
            update.setString(6,student.getUsername());
            update.setString(7,student.getPassword());
            update.setInt(8, student.getCurrentYear());
            update.setInt(9,studentID);

            update.executeUpdate();

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        } finally {
            if(update != null) {
                try{
                    update.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static Student findById(int studentID) { //TODO : WORKS
        Student studentToReturn = null;

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement(selectById);
            preparedStatement.setInt(1, studentID);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String phoneNo = resultSet.getString("phoneNo");
            int PNC = resultSet.getInt("PNC");
            int ICN = resultSet.getInt("ICN");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            int currentYear = resultSet.getInt("currentYear");

            studentToReturn = new Student(name, address, phoneNo, PNC, ICN, username, password, currentYear);

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
        System.out.println(studentToReturn.getStudentID() + studentToReturn.getICN());
        return studentToReturn;

    }

    public static ArrayList<Student> findAll(){ //TODO : works
        ArrayList<Student> students = new ArrayList<Student>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectAll);

            while(resultSet.next()) {
                Student student = new Student();
                student.setStudentID(resultSet.getInt("studentID"));
                student.setName(resultSet.getString("name"));
                student.setAddress(resultSet.getString("address"));
                student.setPhoneNo(resultSet.getString("phoneNo"));
                student.setPNC(resultSet.getInt("PNC"));
                student.setICN(resultSet.getInt("ICN"));
                student.setUsername(resultSet.getString("username"));
                student.setPassword(resultSet.getString("password"));
                student.setCurrentYear(resultSet.getInt("currentYear"));

                students.add(student);
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
        System.out.println(students);
        return students;
    }

    public static void deleteById(int studentID){ //TODO: WORKS
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, studentID);
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

    public static Student findByUsername(String username){
        Student studentToReturn = null;

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement(selectByUsername);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int studentID = resultSet.getInt("studentID");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String phoneNo = resultSet.getString("phoneNo");
            int PNC = resultSet.getInt("PNC");
            int ICN = resultSet.getInt("ICN");
            String password = resultSet.getString("password");
            int currentYear = resultSet.getInt("currentYear");

            studentToReturn = new Student(studentID, name, address, phoneNo, PNC, ICN, username, password, currentYear);

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
        System.out.println(studentToReturn.getName());
        return studentToReturn;

    }


}