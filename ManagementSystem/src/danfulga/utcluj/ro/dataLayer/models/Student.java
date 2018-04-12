package danfulga.utcluj.ro.dataLayer.models;


import java.util.Objects;

public class Student {

    private int studentID;
    private String name;
    private String address;
    private String phoneNo;
    private int PNC;
    private int ICN;
    private String username;
    private String password;
    private int currentYear;

    public Student(){};
    public Student(int studentID, String name, String address, String phoneNo, int PNC, int ICN, String username, String password, int currentYear) {
        this.studentID = studentID;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.PNC = PNC;
        this.ICN = ICN;
        this.username = username;
        this.password = password;
        this.currentYear = currentYear;
    }

    public Student(String name, String address, String phoneNo, int PNC, int ICN, String username, String password, int currentYear) {
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.PNC = PNC;
        this.ICN = ICN;
        this.username = username;
        this.password = password;
        this.currentYear = currentYear;
    }





    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getPNC() {
        return PNC;
    }

    public void setPNC(int PNC) {
        this.PNC = PNC;
    }

    public int getICN() {
        return ICN;
    }

    public void setICN(int ICN) {
        this.ICN = ICN;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", PNC=" + PNC +
                ", ICN=" + ICN +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", currentYear=" + currentYear +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getStudentID() == student.getStudentID() &&
                getPNC() == student.getPNC() &&
                getICN() == student.getICN() &&
                getCurrentYear() == student.getCurrentYear() &&
                Objects.equals(getName(), student.getName()) &&
                Objects.equals(getAddress(), student.getAddress()) &&
                Objects.equals(getPhoneNo(), student.getPhoneNo()) &&
                Objects.equals(getUsername(), student.getUsername()) &&
                Objects.equals(getPassword(), student.getPassword());
    }


}
