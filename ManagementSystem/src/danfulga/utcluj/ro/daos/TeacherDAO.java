package danfulga.utcluj.ro.daos;

import danfulga.utcluj.ro.connection.ConnectionFactory;
import danfulga.utcluj.ro.models.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Logger;
//TODO : MERGE TOT
public class TeacherDAO {
    protected static final Logger LOGGER = Logger.getLogger(TeacherDAO.class.getName());

    private static final String create = "CREATE TABLE IF NOT EXISTS teachers ( teachersID int NOT NULL PRIMARY KEY, " +
            "name varchar(45), address varchar(45), phoneNo varchar(45), PNC int(13), ICN int(5), username varchar(45)" +
            "password varchar(45))";

    public static final String insert = "INSERT INTO teachers (name, address, phoneNo, PNC, ICN, username, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String selectById = "SELECT * FROM teachers WHERE teacherID = ?";
    private final static String selectAll = "SELECT * FROM teachers";
    private final static String delete = "DELETE FROM teachers WHERE teacherID = ?";
    private final static String update = "UPDATE teachers SET name = ?,address = ?, phoneNo = ?, PNC = ?, ICN =?, username = ?, password = ? WHERE teacherID = ?";
    private final static String selectByUsername = "SELECT * FROM teachers WHERE username = ?";

    public static void createTeachersTable(){
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

    public static int insert(Teacher teacher){ //todo : works
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement preparedStatement = null;
        int insertedID = -1;
        try {
            preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getAddress());
            preparedStatement.setString(3, teacher.getPhoneNo());
            preparedStatement.setInt(4, teacher.getPNC());
            preparedStatement.setInt(5, teacher.getICN());
            preparedStatement.setString(6, teacher.getUsername());
            preparedStatement.setString(7, teacher.getPassword());


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

    public static void update(Teacher teacher, int teacherID) { //TODO : WORKS
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(update);

            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getAddress());
            preparedStatement.setString(3, teacher.getPhoneNo());
            preparedStatement.setInt(4, teacher.getPNC());
            preparedStatement.setInt(5, teacher.getICN());
            preparedStatement.setString(6, teacher.getUsername());
            preparedStatement.setString(7,teacher.getPassword());
            preparedStatement.setInt(8,teacherID);

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
    public static Teacher findById(int teacherID) { //TODO : WORKS
        Teacher teacherToReturn = null;

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement(selectById);
            preparedStatement.setInt(1, teacherID);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String phoneNo = resultSet.getString("phoneNo");
            int PNC = resultSet.getInt("PNC");
            int ICN = resultSet.getInt("ICN");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            teacherToReturn = new Teacher(name, address, phoneNo, PNC, ICN, username, password);

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
        System.out.println(teacherToReturn.getAddress() + teacherToReturn.getICN());
        return teacherToReturn;

    }
    public static ArrayList<Teacher> findAll(){ //TODO : WORKS
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectAll);

            while(resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setTeacherID(resultSet.getInt("teacherID"));
                teacher.setName(resultSet.getString("name"));
                teacher.setAddress(resultSet.getString("address"));
                teacher.setPhoneNo(resultSet.getString("phoneNo"));
                teacher.setPNC(resultSet.getInt("PNC"));
                teacher.setICN(resultSet.getInt("ICN"));
                teacher.setUsername(resultSet.getString("username"));
                teacher.setPassword(resultSet.getString("password"));

                teachers.add(teacher);
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
        System.out.println(teachers);
        return teachers;
    }

    public static void deleteById(int teacherID){ //TODO : WORKS
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, teacherID);
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

    public static Teacher findByUsername(String username){
        Teacher teacherToReturn = null;

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            preparedStatement = connection.prepareStatement(selectByUsername);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int teacherID = resultSet.getInt("teacherID");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String phoneNo = resultSet.getString("phoneNo");
            int PNC = resultSet.getInt("PNC");
            int ICN = resultSet.getInt("ICN");
            String password = resultSet.getString("password");

            teacherToReturn = new Teacher(teacherID, name, address, phoneNo, PNC, ICN, username, password);

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
        System.out.println(teacherToReturn.getName());
        return teacherToReturn;

    }
}
