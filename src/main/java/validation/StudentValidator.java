package validation;

import domain.Student;

import java.util.regex.Pattern;

public class StudentValidator implements Validator<Student> {
    public void validate(Student student) throws ValidationException {
        if (student.getID() == null || student.getID() <= 0) {
            throw new ValidationException("ID invalid! \n");
        }
        if (student.getNume() == null || student.getNume().length() < 2 || student.getNume().length() > 100) {
            throw new ValidationException("Nume invalid! \n");
        }
        if (student.getGrupa() <= 0) {
            throw new ValidationException("Grupa invalida! \n");
        }
        if (student.getTeacher() == null || student.getTeacher().length() < 2 || student.getTeacher().length() > 100) {
            throw new ValidationException("Teacher invalid! \n");
        }
        if (!Pattern.compile("[a-zA-Z0-9_-]+@[a-z]+\\.[a-z]+").matcher(student.getEmail()).matches()) {
            throw new ValidationException("Email invalid");
        }
    }
}

