// @see http://technojeeves.com/index.php/54-serialize-a-java-collection-with-the-streaming-api
import javax.xml.stream.*;
import java.io.*;
import java.util.*;

public class Map2Xml {
    public static void main(String[] args) {
        Map<String, Integer> m = Map2Xml.createRandomMap();
        System.out.println("Starting map:");
        System.out.println(m);
        // Now write it as xml
        try {
            Map2Xml.toXml(m, new FileWriter("map2xml.xml"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> createRandomMap() {
        Map<String, Integer> m = new HashMap<String, Integer>(5);
        for(char i = 'a';i < 'f';i++) {
            m.put(String.valueOf(i), (int)(Math.random() * 99));
        }
        return m;
    }

    public static <K,V> void toXml(Map<K, V> map, Writer out) throws IOException, XMLStreamException {
        XMLStreamWriter xsw = null;
        try {
            try {
                XMLOutputFactory xof = XMLOutputFactory.newInstance();
                // If you want pretty-printing, you can use:
                //xsw = new javanet.staxutils.IndentingXMLStreamWriter(xof.createXMLStreamWriter(out));
                xsw = xof.createXMLStreamWriter(out);
                //xsw.writeStartDocument("utf-8", "1.0");
                xsw.writeStartElement("entries");

                // Do the Collection
                for(Map.Entry<K, V> e : map.entrySet()) {
                    xsw.writeStartElement("entry");
                    xsw.writeAttribute("key", e.getKey().toString());
                    xsw.writeAttribute("value", e.getValue().toString());
                    xsw.writeEndElement();
                }
                xsw.writeEndElement();
                xsw.writeEndDocument();
            }
            finally {
                if (out != null) {
                    try { out.close() ; } catch(IOException e) { /* ignore */ }      
                }
            }// end inner finally
        }
        finally {
            if (xsw != null) {
                try { xsw.close() ; } catch(XMLStreamException e) { /* ignore */ }    
            }
        }
    }
}