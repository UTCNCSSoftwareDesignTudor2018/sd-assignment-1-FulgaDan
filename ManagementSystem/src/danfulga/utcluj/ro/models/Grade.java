package danfulga.utcluj.ro.models;

public class Grade {

    private int gradeID;
    private int enrollmentID;
    private int mark;

    public Grade(int mark, int enrollmentID) {
        this.mark = mark;
        this.enrollmentID = enrollmentID;
    }

    public Grade(){};


    public int getGradeID() {
        return gradeID;
    }

    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeID=" + gradeID +
                ", enrollmentID=" + enrollmentID +
                ", mark=" + mark +
                '}';
    }
}
