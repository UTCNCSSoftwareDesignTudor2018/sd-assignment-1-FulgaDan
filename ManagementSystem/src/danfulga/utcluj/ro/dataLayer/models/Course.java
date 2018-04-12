package danfulga.utcluj.ro.dataLayer.models;

public class Course {

    private int courseID;
    private String name;
    private int credits;
    private int teacherID;

    public Course(String name, int credits, int teacherID) {
        this.name = name;
        this.credits = credits;
        this.teacherID = teacherID;
    }
    public Course(String name, int credits){
        this.name = name;
        this.credits = credits;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }


    public Course() {
    }


    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", name='" + name + '\'' +
                ", credits=" + credits +
                ", teacherID=" + teacherID +
                '}';
    }

}
