package danfulga.utcluj.ro.testing;

import danfulga.utcluj.ro.dataLayer.daos.GradeDAO;
import danfulga.utcluj.ro.dataLayer.models.Grade;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradeDAOJUnitTest {

    //TODO : test the findAll function
    @Test
    public void jUnitTest(){
        Grade grade = new Grade(6,41);
        List<Grade> grades = new ArrayList<>();
        List<Grade> grades1 = new ArrayList<>();

        grades = GradeDAO.findAll();
        GradeDAO.insert(grade);
        grades1 = GradeDAO.findAll();
        assertEquals(grades1.size(), grades.size() + 1);

    }
}
