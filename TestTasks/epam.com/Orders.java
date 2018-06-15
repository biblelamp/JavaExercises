/**
 * Class Orders
 * The class provides work with the orders list
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Oct 11, 2017
 */
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.*;

class Orders {
    private Map<String, String[]> orders;

    Orders(String xmlFile) {
        orders = new HashMap<>();
        try {
            DocumentBuilderFactory documentBuilderFactory =
                DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            NodeList nodeList =
                document.getElementsByTagName(
                    document.getDocumentElement().getChildNodes().item(1).
                    getNodeName());
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element element = (Element) node;
                String name =
                    element.getElementsByTagName("client").item(0).getTextContent();
                NodeList list = element.getElementsByTagName("item");
                String[] items = new String[list.getLength()];
                for (int j = 0; j < list.getLength(); j++)
                    items[j] = list.item(j).getTextContent();
                orders.put(name, items);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Map<String, Integer> getDishes(Menu menu) { // get list of dishes
        Map<String, Integer> dishes = new HashMap<>();
        for (Map.Entry<String, String[]> item : orders.entrySet())
            for (String id : item.getValue()) {
                Integer value = dishes.get(id);
                dishes.put(id, (value == null)? 1 : value + 1);
            }
        return dishes;
    }

    public Map<String, String[]> getOrders() { // get all orders
        return orders;
    }

    @Override
    public String toString() {
        String result = "{";
        for (Map.Entry<String, String[]> item : orders.entrySet())
            result += item.getKey() + "=" +
                Arrays.toString(item.getValue()) + ", ";
        return result.substring(0, result.length() - 2) + "}";
    }
}