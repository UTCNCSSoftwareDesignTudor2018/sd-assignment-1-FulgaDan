package danfulga.utcluj.ro.testing;

import danfulga.utcluj.ro.dataLayer.daos.CourseDAO;
import danfulga.utcluj.ro.dataLayer.models.Course;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseDAOJUnitTest {

    @Test
    public void jUnitTest(){
        //TODO : test the findAll function

        List<Course> courses = new ArrayList<>();
        courses = CourseDAO.findAll();
        assertEquals(courses.size(), 2);
    }
}
