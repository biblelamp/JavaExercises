/**
 * Java. Test task from idc.com
 * Class for processing sales data
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Aug 04, 2018
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
import java.util.Comparator;

public class SalesData {
    private String[] titles;
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
        titles = lines.get(0).split(",");
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
     * Get list of countries from data
     * @return Set<String>
     */
    public Set<String> getCountries() {
        return data.keySet();
    }

    /**
     * Get list of quarters by country from data
     * @return Set<String>
     */
    public Set<String> getQuartersByCountry(String nameOfCountry) {
        Map<String, List<Record>> country;
        country = data.computeIfAbsent(nameOfCountry, x -> new HashMap<>());
        return country.keySet();
    }

    /**
     * Get list of units by country and quarter from data
     * @param nameOfCountry
     * @param nameOfQuarter
     * @return List<Record>
     */
    public List<Record> getQuarterTable(String nameOfCountry, String nameOfQuarter) {
        Map<String, List<Record>> country;
        List<Record> quarter;
        country = data.computeIfAbsent(nameOfCountry, x -> new HashMap<>());
        quarter = country.computeIfAbsent(nameOfQuarter, x -> new ArrayList<>());
        return quarter;
    }

    /**
     * Sorting table by Vendor
     * @param table of quarter
     * @return List<Record>
     */
    public List<Record> sortTableByVendor(List<Record> table) {
        table.sort(Comparator.comparing(Record::getVendor));
        return table;
    }

    /**
     * Sorting table by Unit
     * @param table of quarter
     * @return List<Record>
     */
    public List<Record> sortTableByUnit(List<Record> table) {
        table.sort(Comparator.comparing(Record::getUnit));
        return table;
    }

    /**
     * The class for each record in the table
     */
    private class Record {
        private String vendor;
        private double unit;
        private double percent;

        Record(String vendor, double unit) {
            this.vendor = vendor;
            this.unit = unit;
        }

        String getVendor() {
            return vendor;
        }

        double getUnit() {
            return unit;
        }

        @Override
        public String toString() {
            return "[" + vendor + ", " + unit + ", " + percent + "]";
        }
    }
}