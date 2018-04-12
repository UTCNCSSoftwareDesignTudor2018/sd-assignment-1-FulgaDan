package danfulga.utcluj.ro.testing;

import danfulga.utcluj.ro.dataLayer.daos.StudentDAO;
import danfulga.utcluj.ro.dataLayer.models.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StudentDAOJUnitTest {

    //TODO : test the findByUsername function

    @Test
    void jUnitTest2(){
        Student student = StudentDAO.findByUsername("miron");
        assertEquals(student.getStudentID(),145);
    }
}
