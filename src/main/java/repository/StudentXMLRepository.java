package repository;
import domain.Student;
import validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class StudentXMLRepository extends AbstractXMLRepository<Integer, Student> {

    public StudentXMLRepository(Validator<Student> validator, String XMLfilename) {
        super(validator, XMLfilename);
        loadFromXmlFile();
    }

    protected Element getElementFromEntity(Student student, Document XMLdocument) {
        Element element = XMLdocument.createElement("student");
        element.setAttribute("ID", student.getID().toString());

        element.appendChild(createElement(XMLdocument, "Nume", student.getNume()));
        element.appendChild(createElement(XMLdocument, "Grupa", String.valueOf(student.getGrupa())));
        element.appendChild(createElement(XMLdocument, "Teacher", student.getTeacher()));
        element.appendChild(createElement(XMLdocument, "Email", student.getEmail()));
        return element;
    }

    protected Student getEntityFromNode(Element node) {
        String ID = node.getAttributeNode("ID").getValue();
        String nume = node.getElementsByTagName("Nume").item(0).getTextContent();
        int grupa = Integer.parseInt(node.getElementsByTagName("Grupa").item(0).getTextContent());
        String teacher = node.getElementsByTagName("Teacher").item(0).getTextContent();
        String email = node.getElementsByTagName("Email").item(0).getTextContent();

        return new Student(Integer.valueOf(ID), nume, grupa, email, teacher);
    }
}
