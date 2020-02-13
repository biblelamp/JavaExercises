import java.util.Date;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/*
 * Parsing different type od date
 * see https://stackoverflow.com/questions/4496359/how-to-parse-date-string-to-date
 */
class DateAndTimeParsing {

    public static void main(String[] args) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy:mm:dd");
        Date date = df.parse("2012:02:22 11:12-13");

        System.out.println(date);
        System.out.println(String.format("%tY-%tm-%tdT00:00:00Z", date, date, date));
    }
}