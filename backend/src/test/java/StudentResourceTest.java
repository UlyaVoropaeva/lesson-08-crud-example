import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.gb.persist.Student;
import ru.gb.repository.StudentRepository;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class StudentResourceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testFindAllUsers() throws Exception {
       Student student = new Student(
               "test_lastname",
                "test_firstname");
        student = studentRepository.save(student);

        mvc.perform(MockMvcRequestBuilders
                .get("/student/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstname", is(student.getFirstname())));
    }

    @Test
    public void testFindByIdUsers() throws Exception {
        Student student = new Student(
                "test_lastname",
                "test_firstname"
        );
        student = studentRepository.save(student);

        mvc.perform(MockMvcRequestBuilders
                        .get("/student/" + student.getId() +  "/id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", is(student.getFirstname())));
    }
}
