package danfulga.utcluj.ro.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import danfulga.utcluj.ro.dataLayer.daos.TeacherDAO;
import danfulga.utcluj.ro.dataLayer.models.Teacher;
import org.junit.jupiter.api.Test;

public class TeacherDAOJUnitTest {

    @Test
    void jUnitTest(){
        //TODO : test the findByID function

        Teacher teacher = TeacherDAO.findById(48);
        assertEquals(teacher.getAddress(),"9 Burebista");
    }
}
