import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class DOMxmlReader {

    public static void main(String[] args) {
        String file = "languages.xml";
        File xmlFile = new File(file);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
            System.out.println("The root: " + document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName("Language");

            // create a list of objects Language
            List<Language> langList = new ArrayList<Language>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                langList.add(getLanguage(nodeList.item(i)));
            }

            // output to the console information on each object Language
            for (Language lang : langList) {
                System.out.println(lang.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // create from a node of the document object Language
    private static Language getLanguage(Node node) {
        Language lang = new Language();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            lang.setName(getTagValue("name", element));
            lang.setAge(Integer.parseInt(getTagValue("age", element)));
        }
        return lang;
    }

    // get the value from the tag element
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}