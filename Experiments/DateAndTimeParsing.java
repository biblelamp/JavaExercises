import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/*
 * Parsing different type od date
 * see https://stackoverflow.com/questions/4496359/how-to-parse-date-string-to-date
 */
class DateAndTimeParsing {

    public static void main(String[] args) throws ParseException {
        DateFormat df = new SimpleDateFormat("(HH:mm a EEE, MMM dd, yyyy)", Locale.ENGLISH);
        Date date = df.parse("(3:39 PM Friday, December 20, 2002)");

        System.out.println(date);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(date));

        DateFormat dateFormat = new SimpleDateFormat("d.M.yyy");
        System.out.println(dateFormat.format(java.sql.Date.valueOf(LocalDate.of(2821, 1, 1))));
    }
}