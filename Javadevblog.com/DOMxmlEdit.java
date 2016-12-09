import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import java.util.*;
 
public class DOMxmlEdit {

    static final String[] Languages = {"JAVA", "C++", "PYTHON", "PASCAL"};

    public static void main(String[] args) {
        File xmlFile = new File("languages.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // update the value of the elements name
            updateElementValueName(doc);

            // adding a new element
            addElement(doc);

            // output to a file and in console
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File("languagesUpdated.xml"));
            StreamResult console = new StreamResult(System.out);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, file);
            transformer.transform(source, console);
            System.out.println("XML was successfully changed.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // adding element paradigm
    private static void addElement(Document doc) {
        NodeList languages = doc.getElementsByTagName("Language");
        Element lang = null;

        // to get each element Language
        for(int i = 0; i < languages.getLength(); i++){
            lang = (Element) languages.item(i);
            String name = lang.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
            if (Arrays.asList(Languages).contains(name)) {
                Element paradigmElement = doc.createElement("paradigm");
                paradigmElement.appendChild(doc.createTextNode("oop"));
                lang.appendChild(paradigmElement); // addition
            }
        }
    }

    // change the name element
    private static void updateElementValueName(Document doc) {
        NodeList languages = doc.getElementsByTagName("Language");
        Element lang = null;

        // update the value of the elements name
        for(int i = 0; i < languages.getLength(); i++){
            lang = (Element) languages.item(i);
            Node name = lang.getElementsByTagName("name").item(0).getFirstChild();
            name.setNodeValue(name.getNodeValue().toUpperCase()); // update
        }
    } 
}