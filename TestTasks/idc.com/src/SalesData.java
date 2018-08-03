/**
 * Java. Test task from idc.com
 * Class for processing sales data
 *
 * @author Sergey Iryupin
 * @version dated Aug 03, 2018
 */
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SalesData {
    private Map<String, Map<String, List<Record>>> data;

    public SalesData(String fileName) {
        data = new HashMap<>();
        Map<String, List<Record>> country;
        List<Record> quarter;
        Record record;
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(fileName),
                    StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
        for (int i = 1; i < lines.size(); i++) {

            System.out.println(lines.get(i));

            String[] fields = lines.get(i).split(",");
            record = new Record(fields[2], Double.parseDouble(fields[3]));
            country = data.getOrDefault(fields[0], null);
            if (country != null) {
                quarter = country.getOrDefault(fields[1], null);
                if (quarter != null)
                    quarter.add(record);
                else
                    quarter = new ArrayList<>(Arrays.asList(record));
                country.put(fields[1], quarter);
            } else {
                country = new HashMap<>();
                country.put(fields[1], Arrays.asList(record));
            }
            data.put(fields[0], country);
        }
        System.out.println(data);
    }

    /**
     * The class for each record in the table
     */
    private class Record {
        private String vendor;
        private double units;
        private double percent;

        Record(String vendor, double units) {
            this.vendor = vendor;
            this.units = units;
        }

        String getVendor() {
            return vendor;
        }

        double getUnits() {
            return units;
        }
    }
}