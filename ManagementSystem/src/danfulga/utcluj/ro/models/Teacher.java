package danfulga.utcluj.ro.models;

import java.util.Objects;

public class Teacher {

    private int teacherID;
    private String name;
    private String address;
    private String phoneNo;
    private int PNC;
    private int ICN;
    private String username;
    private String password;

    public Teacher(){};

    public Teacher(String name, String address, String phoneNo, int PNC, int ICN, String username, String password) {
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.PNC = PNC;
        this.ICN = ICN;
        this.username = username;
        this.password = password;
    }
    public Teacher(int teacherID,String name, String address, String phoneNo, int PNC, int ICN, String username, String password) {
        this.teacherID = teacherID;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.PNC = PNC;
        this.ICN = ICN;
        this.username = username;
        this.password = password;
    }


    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return getTeacherID() == teacher.getTeacherID() &&
                getPNC() == teacher.getPNC() &&
                getICN() == teacher.getICN() &&
                Objects.equals(getName(), teacher.getName()) &&
                Objects.equals(getAddress(), teacher.getAddress()) &&
                Objects.equals(getPhoneNo(), teacher.getPhoneNo()) &&
                Objects.equals(getUsername(), teacher.getUsername()) &&
                Objects.equals(getPassword(), teacher.getPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getTeacherID(), getName(), getAddress(), getPhoneNo(), getPNC(), getICN(), getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherID=" + teacherID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", PNC=" + PNC +
                ", ICN=" + ICN +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
