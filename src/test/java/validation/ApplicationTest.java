package validation;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaRepository;
import repository.StudentRepository;
import repository.TemaRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest {
    private StudentRepository studentRepository;
    private TemaRepository temaRepository;
    private NotaRepository notaRepository;

    @BeforeEach
    public void setUp() {
        studentRepository = new StudentRepository(new StudentValidator());
        temaRepository = new TemaRepository(new TemaValidator());
        notaRepository = new NotaRepository(new NotaValidator());
    }

    @Test
    void testAddStudent() {
        Student student = new Student(1, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));
        List<Student> studentList = StreamSupport
                .stream(
                        studentRepository
                                .findAll()
                                .spliterator(),
                        false
                ).collect(
                        Collectors.toList()
                );
        assertEquals(1, studentList.size());
    }

    @Test
    void testAddAssignment() {
        Tema tema = new Tema("1", "test", 12, 10);

        assertDoesNotThrow(() -> temaRepository.save(tema));

        List<Tema> assignmentList = StreamSupport
                .stream(
                        temaRepository
                                .findAll()
                                .spliterator(),
                        false
                ).collect(
                        Collectors.toList()
                );
        assertEquals(1, assignmentList.size());
    }

    @Test
    void testAddGrade() {
        Nota grade = new Nota(new Pair<>("1", "1"), 9.5, 11, "Almost perfect, great job");

        assertDoesNotThrow(() -> notaRepository.save(grade));

        List<Nota> gradesList = StreamSupport
                .stream(
                        notaRepository
                                .findAll()
                                .spliterator(),
                        false
                ).collect(
                        Collectors.toList()
                );
        assertEquals(1, gradesList.size());
    }

    @Test
    void testAllAtOnce() {
        testAddStudent();
        testAddAssignment();
        testAddGrade();
    }
}
