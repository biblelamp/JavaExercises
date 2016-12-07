import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

class DOMxmlWriter {

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            // create an empty Document object
            Document doc = builder.newDocument();
            // create a root element
            Element rootElement = doc.createElementNS("language", "Languages");
            // add the root element in the Document object
            doc.appendChild(rootElement);

            // add child elements of the root element
            rootElement.appendChild(getLanguage(doc, "1", "Java", "21"));
            rootElement.appendChild(getLanguage(doc, "2", "Python", "25"));
            rootElement.appendChild(getLanguage(doc, "3", "C", "44"));
            rootElement.appendChild(getLanguage(doc, "4", "Pascal", "46"));

            // create an object for output to the console 
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // for pretty view in concole
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // for output to the console and to the file
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("languages.xml"));

            // write data
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.println("XML file created.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // creation of a new nodes for XML-file
    static Node getLanguage(Document doc, String id, String name, String age) {
        Element language = doc.createElement("Language");

        // set id
        language.setAttribute("id", id);

        // create an element name
        language.appendChild(getLanguageElements(doc, language, "name", name));

        // create an element age
        language.appendChild(getLanguageElements(doc, language, "age", age)); 
        return language;
    }

    // creation of a new node
    static Node getLanguageElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}