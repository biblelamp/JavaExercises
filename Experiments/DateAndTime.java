import java.sql.Date;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.TimeZone;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.DatatypeConfigurationException;

class DateAndTime {

    public static void main(String[] args) throws DatatypeConfigurationException, ParseException {
        String strDate = "3333-03-03 00:00:00";
        Date d1 = Date.valueOf(strDate.substring(0, 10));
        //System.out.println(d1 + 1);
        System.out.println(String.format("%tY", d1));
        System.out.println(String.format("%tY/%tm/%td", d1, d1, d1));

        //Date currentDate = new Date();
        //String isoDate = currentDate.toISOString();
        //System.out.println(currentDate, isoDate);

        Instant yourInstant = Instant.now();
        String dateTimeString = yourInstant.toString();
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeString);
        System.out.println(date2);

        //long offsetDateTime = OffsetDateTime.now().toEpochSecond();
        long offsetDateTime = OffsetDateTime.of(2019, 9, 10, 10, 53, 0, 0, ZoneOffset.UTC).toEpochSecond();
        OffsetDateTime startDateTime = OffsetDateTime
                    .now(Clock.fixed(Instant.ofEpochSecond(offsetDateTime), ZoneOffset.UTC));
        System.out.println(offsetDateTime);
        System.out.println(startDateTime);

        // LocalDate -> sql.Date using timeZone
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(2019, 9 - 1, 23); // months count from 0
        Date sqlDate = new Date(calendar.getTimeInMillis());
        System.out.println(sqlDate);

        // String -> OffsetDateTime
        String dateOd = "1968+01:00";
        OffsetDateTime date = OffsetDateTime.now();
        System.out.println(date);
        date = OffsetDateTime.of(Integer.valueOf(dateOd.substring(0, 4)), 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        System.out.println(date);

        String creationDate = "2019-11-17T19:11:37" + "Z";
        Instant instant = Instant.parse(creationDate);
        System.out.println(instant);

        String dateTime = "1966-12-01T00:00:00";
        Timestamp ts = Timestamp.from(Instant.parse(dateTime + "Z"));
        System.out.println(ts);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date(sdf.parse(dateTime).getTime());
        System.out.println(dt);

        Date dateNow = new Date(Instant.now().toEpochMilli());
        System.out.println(dateNow);
    }
}