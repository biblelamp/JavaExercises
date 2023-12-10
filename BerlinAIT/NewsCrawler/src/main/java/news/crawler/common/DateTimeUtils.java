package news.crawler.common;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
public class DateTimeUtils {

    private final static String[] MONTHS_RU = {"января","февраля","марта","апреля","мая","июня","июля","августа",
            "сентября","октября","ноября","декабря"};
    private final static String[] MONTHS_CZ = {"ledna","února","března","dubna","května","června","července","srpna",
            "září","října","listopadu","prosince"};

    private final static String[] MONTHS_UA = {"січня","лютого","березня","квітня","травня","червня","липня","серпня",
            "вересня","жовтня","листопада","грудня"};

    /**
     * Convert date-time in String to LocalDateTime
     *
     * @param strDateTime
     * @return LocalDateTime
     */
    public static LocalDateTime convertDateTime(String strDateTime) {
        LocalDateTime dateTime = null;
        String[] dt = strDateTime.split("[ ,\\.]+");
        if (dt.length == 5) {
            dt = new String[]{dt[1], dt[2], dt[3], dt[4]};
        }

        int idx = Arrays.asList(MONTHS_RU).indexOf(dt[1].toLowerCase());
        if (idx < 0) {
            idx = Arrays.asList(MONTHS_CZ).indexOf(dt[1].toLowerCase());
            if (idx < 0) {
                idx = Arrays.asList(MONTHS_UA).indexOf(dt[1].toLowerCase());
            }
        }
        dt[1] = String.valueOf(idx + 1);

        try {
            dateTime = LocalDateTime.parse(String.join(" ", dt), DateTimeFormatter.ofPattern("d M yyyy HH:mm"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return dateTime;
    }

    /**
     * Convert date and time in String to LocalDateTime
     *
     * @param date
     * @param time
     * @return LocalDateTime
     */
    public static LocalDateTime convertDateTime(String date, String time) {
        LocalDateTime dateTime = null;
        try {
            dateTime = LocalDateTime.parse(date + " " + time, DateTimeFormatter.ofPattern("d.M.yyyy HH:mm"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return dateTime;
    }

    public static void main(String[] args) {
        String dt = "вторник, 17. listopadu, 2023 15:39";
        System.out.println(convertDateTime(dt));
    }
}
