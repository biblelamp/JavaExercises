import java.io.*;
import java.util.*;

import javax.xml.namespace.*;
import javax.xml.stream.*;
import javax.xml.stream.events.*;

public class XMLstaxParser {

    public static void main(String[] args) {
        List<Student> studentsList = parseXMLfile("students.xml");
        for (Student student : studentsList) {
            System.out.println(student.toString());
        }
    }

    private static List<Student> parseXMLfile(String fileName) {
        List<Student> studentsList = new ArrayList<>();
        Student student = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            // to init reader
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            // to get all the elements of the xml file
            while (reader.hasNext()) {
                // get event and analize it
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("Student")) {
                        student = new Student();
                        // get id for each element of Student
                        Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                        if (idAttr != null) {
                            student.setId(Integer.parseInt(idAttr.getValue()));
                        }
                    } else if (startElement.getName().getLocalPart().equals("age")) {
                        xmlEvent = reader.nextEvent();
                        student.setAge(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equals("name")) {
                        xmlEvent = reader.nextEvent();
                        student.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("language")) {
                        xmlEvent = reader.nextEvent();
                        student.setLanguage(xmlEvent.asCharacters().getData());
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Student")) {
                        studentsList.add(student);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }
        return studentsList;
    }
}