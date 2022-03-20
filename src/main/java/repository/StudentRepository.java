package repository;

import domain.Student;
import validation.*;

public class StudentRepository extends AbstractCRUDRepository<Integer, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}

