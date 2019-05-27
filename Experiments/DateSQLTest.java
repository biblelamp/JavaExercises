import java.sql.Date;

class DateSQLTest {

    public static void main(String[] args) {
        Date d1 = Date.valueOf("1805-01-01");
        System.out.println(d1 + 1);
        System.out.println(String.format("%tY", d1));

        var currentDate = new Date();
        var isoDate = currentDate.toISOString();
        System.out.println(currentDate, isoDate);
    }
}