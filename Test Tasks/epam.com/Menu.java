/**
 * Class Menu
 * The class provides work with the menu list
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Oct 10, 2017
 */
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.*;

class Menu {
    private Map<String, MenuRecord> menu;

    Menu(String xmlFile) {
        menu = new HashMap<>();
        try {
            DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            NodeList nodeList =
                document.getElementsByTagName(
                    document.getDocumentElement().getChildNodes().item(1).
                    getChildNodes().item(1).getNodeName());
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element element = (Element) node;
                String id = element.getAttribute("id");
                String name =
                    element.getElementsByTagName("name").item(0).getTextContent();
                int weight =
                    Integer.parseInt(element.getElementsByTagName("weight").
                        item(0).getTextContent());
                int price =
                    Integer.parseInt(element.getElementsByTagName("price").
                        item(0).getTextContent());
                menu.put(id, new MenuRecord(name, weight, price));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return menu.toString();
    }
}