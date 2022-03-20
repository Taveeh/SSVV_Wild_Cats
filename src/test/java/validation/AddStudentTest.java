package validation;

import domain.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import repository.StudentRepository;
import repository.StudentXMLRepository;

import static org.junit.jupiter.api.Assertions.*;

public class AddStudentTest {
    private StudentRepository studentRepository = new StudentRepository(new StudentValidator());

    @Test
    void testValidEntity_shouldWork() {
        var student = new Student(1, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testInvalidNameLengthMin_shouldThrowError() {
        var student = new Student(1, "a", 932, "george@ubbcluj.ro", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    void testInvalidNameLengthMax_shouldThrowError() {
        var student = new Student(
                1,
                new String(new char[101]).replace('\0', 'a'),
                932,
                "george@ubbcluj.ro",
                "Marcel"
        );

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    void testInvalidTeacherLengthMin_shouldThrowError() {
        var student = new Student(1, "George", 932, "george@ubbcluj.ro", "a");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    void testInvalidTeacherLengthMax_shouldThrowError() {
        var student = new Student(1, "George", 932, "george@ubbcluj.ro", new String(new char[101]).replace('\0', 'a'));

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    void testDuplicateEntity_shouldThrowError() {
        var student = new Student(1, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertThrows(ValidationException.class, () -> studentRepository.save(student));
    }

    @Test
    void testInvalidEmail_shouldThrowError() {
        var student = new Student(1, "George", 932, "george", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    void testId0() {
        var student = new Student(0, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    void testId2() {
        var student = new Student(2, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testIdMaxMinus1() {
        var student = new Student(Integer.MAX_VALUE - 1, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testIdMax() {
        var student = new Student(Integer.MAX_VALUE, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testIdMaxPlusOne() {
        var student = new Student(Integer.MAX_VALUE + 1, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    void testNameAb() {
        var student = new Student(1, "ab", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testNameAbc() {
        var student = new Student(1, "abc", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testNameA99() {
        var student = new Student(
                1,
                new String(new char[99]).replace('\0', 'a'),
                932,
                "george@ubbcluj.ro",
                "Marcel"
        );

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testName100() {
        var student = new Student(
                1,
                new String(new char[100]).replace('\0', 'a'),
                932,
                "george@ubbcluj.ro",
                "Marcel"
        );

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testTeacherAb() {
        var student = new Student(1, "George", 932, "george@ubbcluj.ro", "ab");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testTeacherAbc() {
        var student = new Student(1, "George", 932, "george@ubbcluj.ro", "abc");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testTeacherA99() {
        var student = new Student(
                1,
                "George",
                932,
                "george@ubbcluj.ro",
                new String(new char[99]).replace('\0', 'a')
        );

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testTeacher100() {
        var student = new Student(
                1,
                "George",
                932,
                "george@ubbcluj.ro",
                new String(new char[100]).replace('\0', 'a')
        );

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }


    @Test
    void testGroup0() {
        var student = new Student(1, "George", 0, "george@ubbcluj.ro", "Marcel");

        assertThrows(ValidationException.class, () -> studentRepository.save(student));
    }

    @Test
    void testGroup1() {
        var student = new Student(1, "George", 1, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testGroup2() {
        var student = new Student(1, "George", 2, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testGroupMaxMinus1() {
        var student = new Student(1, "George", Integer.MAX_VALUE - 1, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testGroupMax() {
        var student = new Student(1, "George", Integer.MAX_VALUE, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testGroupMaxPlus1() {
        var student = new Student(1, "George", Integer.MAX_VALUE + 1, "george@ubbcluj.ro", "Marcel");

        assertThrows(ValidationException.class, () -> studentRepository.save(student));
    }
}
