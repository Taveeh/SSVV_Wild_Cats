package validation;

import domain.Student;
import org.junit.jupiter.api.Test;
import repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;

public class AddStudentTest {
    private StudentRepository studentRepository = new StudentRepository(new StudentValidator());

    @Test
    void testValidEntity_shouldWork() {
        Student student = new Student(1, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testInvalidNameLengthMin_shouldThrowError() {
        Student student = new Student(1, "a", 932, "george@ubbcluj.ro", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    void testInvalidNameLengthMax_shouldThrowError() {
        Student student = new Student(
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
        Student student = new Student(1, "George", 932, "george@ubbcluj.ro", "a");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    void testInvalidTeacherLengthMax_shouldThrowError() {
        Student student = new Student(1, "George", 932, "george@ubbcluj.ro", new String(new char[101]).replace('\0', 'a'));

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    void testDuplicateEntity_shouldThrowError() {
        Student student = new Student(1, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertThrows(ValidationException.class, () -> studentRepository.save(student));
    }

    @Test
    void testInvalidEmail_shouldThrowError() {
        Student student = new Student(1, "George", 932, "george", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    void testId0() {
        Student student = new Student(0, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    void testId2() {
        Student student = new Student(2, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testIdMaxMinus1() {
        Student student = new Student(Integer.MAX_VALUE - 1, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testIdMax() {
        Student student = new Student(Integer.MAX_VALUE, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testIdMaxPlusOne() {
        Student student = new Student(Integer.MAX_VALUE + 1, "George", 932, "george@ubbcluj.ro", "Marcel");

        assertThrows(Exception.class, () -> studentRepository.save(student));
    }

    @Test
    void testNameAb() {
        Student student = new Student(1, "ab", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testNameAbc() {
        Student student = new Student(1, "abc", 932, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testNameA99() {
        Student student = new Student(
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
        Student student = new Student(
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
        Student student = new Student(1, "George", 932, "george@ubbcluj.ro", "ab");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testTeacherAbc() {
        Student student = new Student(1, "George", 932, "george@ubbcluj.ro", "abc");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testTeacherA99() {
        Student student = new Student(
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
        Student student = new Student(
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
        Student student = new Student(1, "George", 0, "george@ubbcluj.ro", "Marcel");

        assertThrows(ValidationException.class, () -> studentRepository.save(student));
    }

    @Test
    void testGroup1() {
        Student student = new Student(1, "George", 1, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testGroup2() {
        Student student = new Student(1, "George", 2, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testGroupMaxMinus1() {
        Student student = new Student(1, "George", Integer.MAX_VALUE - 1, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testGroupMax() {
        Student student = new Student(1, "George", Integer.MAX_VALUE, "george@ubbcluj.ro", "Marcel");

        assertDoesNotThrow(() -> studentRepository.save(student));

        assertEquals(1, studentRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testGroupMaxPlus1() {
        Student student = new Student(1, "George", Integer.MAX_VALUE + 1, "george@ubbcluj.ro", "Marcel");

        assertThrows(ValidationException.class, () -> studentRepository.save(student));
    }
}
