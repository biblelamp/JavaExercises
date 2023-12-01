package news.crawler;

import news.crawler.common.DateTimeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtilsTest {

    @Test
    public void testConvertDateTime() {
        String strDateTime = "17 октября, 2023 15:39";
        LocalDateTime dateTime = LocalDateTime.parse("17.10.2023 15:39", DateTimeFormatter.ofPattern("d.M.yyyy HH:mm"));
        Assertions.assertEquals(dateTime, DateTimeUtils.convertDateTime(strDateTime));

        strDateTime = "Čtvrtek, 30. listopadu 2023 08:32";
        dateTime = LocalDateTime.parse("30.11.2023 08:32", DateTimeFormatter.ofPattern("d.M.yyyy HH:mm"));
        Assertions.assertEquals(dateTime, DateTimeUtils.convertDateTime(strDateTime));

        String strDate = "15.9.2023";
        String strTime = "12:25";
        dateTime = LocalDateTime.parse(strDate + " " + strTime, DateTimeFormatter.ofPattern("d.M.yyyy HH:mm"));
        Assertions.assertEquals(dateTime, DateTimeUtils.convertDateTime(strDate, strTime));
    }
}
