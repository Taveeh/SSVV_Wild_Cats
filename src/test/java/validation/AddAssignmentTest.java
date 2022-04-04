package validation;

import domain.Tema;
import org.junit.jupiter.api.Test;
import repository.TemaRepository;

import static org.junit.jupiter.api.Assertions.*;

public class AddAssignmentTest {
    private TemaRepository temaRepository = new TemaRepository(new TemaValidator());

    @Test
    void tc1_TestValidAssignment_ShouldWork() {
        Tema tema = new Tema("1", "test", 12, 10);

        assertDoesNotThrow(() ->  temaRepository.save(tema));
        assertEquals(1, temaRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void tc2_TestInvalidAssignment_NullId_ShouldThrowError() {
        Tema tema = new Tema(null, "test", 12, 10);

        assertThrows(ValidationException.class, () ->  temaRepository.save(tema));
    }

    @Test
    void tc3_TestInvalidAssignment_NullDescription_ShouldThrowError() {
        Tema tema = new Tema("1", null, 12, 10);

        assertThrows(ValidationException.class, () ->  temaRepository.save(tema));
    }

    @Test
    void tc4_TestInvalidAssignment_StartlineGreaterThanDeadline_ShouldThrowError() {
        Tema tema = new Tema("1", "test", 10, 12);

        assertThrows(ValidationException.class, () ->  temaRepository.save(tema));
    }

    @Test
    void tc5_TestInvalidAssignment_InvalidStartline_ShouldThrowError() {
        Tema tema = new Tema("1", "test", 10, -1);

        assertThrows(ValidationException.class, () ->  temaRepository.save(tema));
    }

    @Test
    void tc6_testInvalidAssignment_DuplicateId_ShouldThrowError() {
        Tema tema = new Tema("1", "test", 12, 4);

        assertDoesNotThrow(() ->  temaRepository.save(tema));
        assertThrows(ValidationException.class, () -> temaRepository.save(tema));
    }

    @Test
    void tc7_TestInvalidAssignment_EmptyId_ShouldThrowError() {
        Tema tema = new Tema("", "test", 12, 10);

        assertThrows(ValidationException.class, () ->  temaRepository.save(tema));
    }

    @Test
    void tc8_TestInvalidAssignment_EmptyDescription_ShouldThrowError() {
        Tema tema = new Tema("1", "", 12, 10);

        assertThrows(ValidationException.class, () ->  temaRepository.save(tema));
    }

    @Test
    void tc9_TestInvalidAssignment_InvalidDeadline_ShouldThrowError() {
        Tema tema = new Tema("1", "test", -1, 10);

        assertThrows(ValidationException.class, () ->  temaRepository.save(tema));
    }

    @Test
    void tc10_TestInvalidAssignment_InvalidDeadline_ShouldThrowError() {
        Tema tema = new Tema("1", "test", 15, 10);

        assertThrows(ValidationException.class, () ->  temaRepository.save(tema));
    }
}
