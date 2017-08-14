/**
 * Reading Zip files
 * get from: https://metanit.com/java/tutorial/6.12.php
 * see also:
 *  http://java-code.ru/java-dlya-nachinayushhix/zip-arxivy-v-yazyke-java/
 */
import java.io.*;
import java.util.*;
import java.util.zip.*;
 
class ReadZipFile {
 
    public static void main(String[] args) {

        try (ZipInputStream zin = new ZipInputStream(
                new FileInputStream("example.zip"))) {
            ZipEntry entry;
            String name;
            long size;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName(); // get file name
                size = entry.getSize(); // get file size in bytes
                System.out.printf("Name: %s \t size: %d \n", name, size);
                if (name.endsWith(".java")) {
                    Scanner in = new Scanner(zin);
                    while (in.hasNextLine())
                        System.out.println(in.nextLine());
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}