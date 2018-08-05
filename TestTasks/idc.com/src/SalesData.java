/**
 * Java. Test task from idc.com
 * Class for processing sales data
 *
 * @author Sergey Iryupin
 * @version 0.3 dated Aug 05, 2018
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
        return calcPercent(quarter);
    }

    /**
     * Sorting quarterly table by Vendor
     * @param table of quarter
     * @return List<Record>
     */
    public List<Record> sortTableByVendor(List<Record> table) {
        table.sort(Comparator.comparing(Record::getVendor));
        return table;
    }

    /**
     * Sorting quarterly table by Unit
     * @param table of quarter
     * @return List<Record>
     */
    public List<Record> sortTableByUnit(List<Record> table) {
        table.sort(Comparator.comparing(Record::getUnit));
        return table;
    }

    /**
     * Get sales from quarterly table by Vendor
     * @param table of quarter
     * @param Vendor
     * @return Record (vendor, units, percent)
     */
    public Record getUnitsByVendor(List<Record> table, String Vendor) {
        for (Record record : table)
            if (record.getVendor().equals(Vendor))
                return record;
        return null;
    }

    /**
     * Get sales from quarterly table by Vendor
     * @param table of quarter
     * @param Vendor
     * @return int (number of row)
     */
    public int getRowByVendor(List<Record> table, String Vendor) {
        for (int i = 0; i < table.size(); i++)
            if (table.get(i).getVendor().equals(Vendor))
                return i;
        return -1;
    }

    /**
     * Export quarterly table as a string in HTML format
     * @param nameOfCountry
     * @param nameOfQuarter
     * @param sortByVendor
     * @param sortByUnit
     * @return String in HTML format
     */
    public String exportToHTML(String nameOfCountry, String nameOfQuarter,
                              boolean sortByVendor, boolean sortByUnit) {
        List<Record> table = getQuarterTable(nameOfCountry, nameOfQuarter);
        if (sortByVendor)
            table = sortTableByVendor(table);
        if (sortByUnit)
            table = sortTableByUnit(table);
        double total = 0;
        StringBuilder str = new StringBuilder(String.format(
                "<p>%s, %s</p><table>" +
                "<tr><td>%s</td><td>%s</td><td>%s</td></tr>",
                nameOfCountry, nameOfQuarter, titles[2], titles[3], "Share"));
        for (Record record : table) {
            str.append(String.format("<tr>%s</tr>", record.toHTML()));
            total += record.getUnit();
        }
        str.append(String.format(
                "<tr><td>Total</td><td>%.3f</td><td>100%s</td></tr></table>",
                total, "%"));
        return str.toString();
    }

    /**
     * Export quarterly table as a string in CSV format // only stub
     * @param nameOfCountry
     * @param nameOfQuarter
     * @return String in CSV format
     */
    public String exportToCSV(String nameOfCountry, String nameOfQuarter) {
        return null;
    }

    /**
     * Export quarterly table as a file in Excel format // only stub
     * @param nameOfCountry
     * @param nameOfQuarter
     * @param excelFileName
     */
    public void exportToExcel(String nameOfCountry, String nameOfQuarter,
                              String excelFileName) {
    }

    /**
     * Calculate the percentage
     * @param table of quarter
     * @return List<Record>
     */
    private List<Record> calcPercent(List<Record> table) {
        double total = 0;
        if (table.size() > 0 && table.get(0).getPercent() == 0) {
            for (Record record : table)
                total += record.getUnit();
            for (int i = 0; i < table.size(); i++)
                table.get(i).setPercent(table.get(i).getUnit() / total * 100);
        }
        return table;
    }

    /**
     * The class for each record in the table
     *  contains fields:
     *  (String) vendor; (double) unit, percent
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

        double getPercent() {
            return percent;
        }

        void setPercent(double percent) {
            this.percent = percent;
        }

        String toHTML() {
            return String.format("<td>%s</td><td>%.3f</td><td>%.1f%s</td>",
                    vendor, unit, percent, "%");
        }

        @Override
        public String toString() {
            return "[" + vendor + ", " + unit + ", " + percent + "]";
        }
    }
}