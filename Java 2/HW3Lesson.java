/**
 * Java. Level 2. Lesson 3. Example of homework
 *
 * @author Sergey Iryupin
 * @version 0.3.4 dated Dec 30, 2017
 */
import java.util.*;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import javax.xml.stream.*;
//import hw3.*; // old variant homework

class HW3Lesson {

    static final String TEXT = "If you really want to learn, and you want " +
        "to learn more quickly and more deeply, pay attention to how you " +
        "pay attention. Think about how you think. Learn how you learn.";
    static final String FILE_NAME = "lesson3text.txt";

    public static void main(String[] args) throws IOException {

        // 1 task
        System.out.println("1. Counting words:");
        сountWords(TEXT);

        // read file to list
        List<String> lines =
            Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);

        // convert List to Array and Array to string
        String[] strings = new String[lines.size()];
        strings = lines.toArray(strings);
        сountWords(Arrays.toString(strings));

        // 2 task
        System.out.println("\n2. Phone book:");
        testPhoneBook();
    }

    static void сountWords(String text) {
        Integer value;
        Map<String, Integer> hm = new TreeMap<>(); // HashMap better for speed
        String[] words = text.toLowerCase().split("\\W+");
        for (String word : words) {
            //value = hm.get(word);
            //hm.put(word, ((value == null)? 1 : value + 1));
            hm.put(word, hm.getOrDefault(word, 0) + 1);
        }
        System.out.println(hm);
        System.out.println("The phrase has " + words.length + " words and " +
            hm.size() + " non-repeated words.");
    }

    static void testPhoneBook() {
        SimplePhoneBook spb = new SimplePhoneBook();
        spb.add("Mathew", "863 233 3301");
        spb.add("Mark", "863 212 1102");
        spb.add("Luke", "863 243 2803");
        spb.add("John", "863 240 2704");
        spb.add("Luke", "863 248 4512");
        System.out.println("Phones of Luke's:");
        System.out.println(spb.get("Luke"));
        System.out.println(spb);
        spb.exportXMLManually("phonebook.xml");
        /*
        PhoneBook pb = new PhoneBook();
        pb.addRecord("John", new PhoneRecord("234-22-12", "john@mail.com"));
        pb.addRecord("Smith", new PhoneRecord("234-22-21", "smith@mail.com"));
        pb.addRecord("Mike", new PhoneRecord("233-12-12", "mike@mail.com"));
        pb.addPhone("Mike", "+1 234 567-89-00");
        System.out.println(pb.getPhonesByName("Mike")); // found
        System.out.println(pb.getPhonesByName("Bill")); // not found
        System.out.println(pb.getEmailsByName("Smith")); // found
        System.out.println(pb.getEmailsByName("Joseph")); // not found
        */
    }
}

class SimplePhoneBook {
    Map<String, String> pb;

    SimplePhoneBook() {
        pb = new HashMap<>();
    }

    void add(String name, String phone) {
        pb.put(phone, name);
    }

    List<String> get(String name) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : pb.entrySet())
            if (name.equals(entry.getValue()))
                list.add(entry.getKey());
        return list;
    }

    void export(String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // @see https://docs.oracle.com/cd/E13222_01/wls/docs100/xml/stax.html
    // @see http://technojeeves.com/index.php/54-serialize-a-java-collection-with-the-streaming-api
    // write using the Streaming API for XML (StAX)
    void exportXML(String fileName) {
        try (BufferedWriter file = new BufferedWriter(
                new OutputStreamWriter(
                new FileOutputStream(fileName), "UTF8"))) {
            XMLStreamWriter xsw = XMLOutputFactory
                .newInstance()
                .createXMLStreamWriter(file);

            // write the default XML declaration
            xsw.writeStartDocument("utf-8", "1.0");
            xsw.writeCharacters("\n");

            // write a comment
            xsw.writeComment("This is a phone book");
            xsw.writeCharacters("\n");

            // write the root element "phones"
            xsw.writeStartElement("phones");
            xsw.writeCharacters("\n");
            
            // write all records as list of entry
            for (Map.Entry<String, String> e : pb.entrySet()) {
                xsw.writeCharacters("\t");
                xsw.writeStartElement("phone");
                xsw.writeAttribute("name", e.getValue());
                //xsw.writeAttribute("key", e.getKey());
                xsw.writeCharacters(e.getKey());
                xsw.writeEndElement();
                xsw.writeCharacters("\n");
            }
            xsw.writeEndElement();
            xsw.writeCharacters("\n");
            xsw.writeEndDocument();
            xsw.close();
        } catch (XMLStreamException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // write to XML manually
    void exportXMLManually(String fileName) {
        try (BufferedWriter file = new BufferedWriter(
                new OutputStreamWriter(
                new FileOutputStream(fileName), "UTF8"))) {

            // write the default XML declaration, comment and root element
            file.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
            file.write("<!--This is a phone book-->\n");
            file.write("<phones>\n");
            // write all records as list of entry
            for (Map.Entry<String, String> e : pb.entrySet())
                file.write("\t<phone name=\"" + e.getValue() + "\">" +
                    e.getKey() + "</phone>\n");
            file.write("</phones>\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return pb.toString();
    }
}