package danfulga.utcluj.ro.testing;

import danfulga.utcluj.ro.dataLayer.daos.StudentDAO;
import danfulga.utcluj.ro.dataLayer.models.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnrollmentDAOJunitTest {

    @Test
    public void jUnitTest(){
        //TODO : test the update function
        Student student = StudentDAO.findById(149);
        student.setICN(1111);
        StudentDAO.update(student, 149);
        Student student1 = StudentDAO.findById(149);
        assertEquals(student.getICN(), student1.getICN());
    }
}
