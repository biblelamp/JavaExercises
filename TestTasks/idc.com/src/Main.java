public class Main {
    public static void main(String[] args) {
        SalesData data = new SalesData("data.csv");
        for (String country : data.getCountries()) {
            System.out.println(country);
            for (String quarter : data.getQuartersByCountry(country)) {
                System.out.println(quarter);
                System.out.println(
                        data.sortTableByUnit(data.getQuarterTable(country, quarter)));
            }
        }
        System.out.println(data.exportToHTML("Czech Republic", "2010 Q3", true, false));
        System.out.println(data.getUnitsByVendor(
                data.getQuarterTable("Czech Republic", "2010 Q3"), "Dell"));
        System.out.println(data.getRowByVendor(
                data.getQuarterTable("Czech Republic", "2010 Q3"), "Dell"));
    }
}