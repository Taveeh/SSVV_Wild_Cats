package validation;

import domain.Tema;
import org.junit.jupiter.api.Test;
import repository.TemaRepository;

import static org.junit.jupiter.api.Assertions.*;

public class AddAssignmentTest {
    private TemaRepository temaRepository = new TemaRepository(new TemaValidator());

    @Test
    void testValidAssignment_ShouldWork() {
        Tema tema = new Tema("1", "test", 12, 4);

        assertDoesNotThrow(() ->  temaRepository.save(tema));
        assertEquals(1, temaRepository.findAll().spliterator().estimateSize());
    }

    @Test
    void testInvalidAssignment_DuplicateId_ShouldThrowError() {
        Tema tema = new Tema("1", "test", 12, 4);

        assertDoesNotThrow(() ->  temaRepository.save(tema));
        assertThrows(ValidationException.class, () -> temaRepository.save(tema));
    }
}
