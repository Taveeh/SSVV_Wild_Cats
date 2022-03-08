package validation;

import domain.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentValidatorTest {
    private final StudentValidator studentValidator = new StudentValidator();

    @Test
    public void testStudentValidator_ValidGroup_ShouldSucceed() {
        var student = new Student("123", "Custura Octavian", 932);
        assertDoesNotThrow(() -> studentValidator.validate(student));
    }

    @Test
    public void testStudentValidator_InvalidGroup_ShouldThrowError() {
        var student = new Student("123", "Demian Ana-Maria", 1);
        assertThrows(ValidationException.class, () -> studentValidator.validate(student));
    }
}