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
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

public class SalesData {
    private Map<String, Map<String, List<Record>>> data;

    /**
     * Read sales data from csv file
     * @param fileName
     */
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
            String[] fields = lines.get(i).split(",");
            record = new Record(fields[2], Double.parseDouble(fields[3]));
            country = data.computeIfAbsent(fields[0], x -> new HashMap<>());
            quarter = country.computeIfAbsent(fields[1], x -> new ArrayList<>());
            quarter.add(record);
            country.put(fields[1], quarter);
            data.put(fields[0], country);
        }
    }

    /**
     * Get set of country from data
     * @return
     */
    public Set<String> getCountries() {
        return data.keySet();
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

        @Override
        public String toString() {
            return "[" + vendor + ", " + units + "]";
        }
    }
}