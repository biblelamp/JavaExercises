package news.crawler.common;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
public class DateTimeUtils {

    private static String[] monthsRu = {"января","февраля","марта","апреля","мая","июня",
            "июля","августа","сентября","октября","ноября","декабря"};

    public static LocalDateTime convertDateTime(String dateTime) {
        String[] dt = dateTime.split("[ ,]+");
        if (dt[0].length() == 1) {
            dt[0] = "0" + dt[0];
        }

        int idx = Arrays.asList(monthsRu).indexOf(dt[1].toLowerCase());
        dt[1] = String.valueOf(idx + 1);
        if (dt[1].length() == 1) {
            dt[1] = "0" + dt[1];
        }

        return LocalDateTime.parse(String.join(" ", dt), DateTimeFormatter.ofPattern("dd MM yyyy HH:mm"));
    }

    public static LocalDateTime convertDateTime(String date, String time) {
        LocalDateTime dateTime = null;
        try {
            dateTime = LocalDateTime.parse(date + " " + time, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return dateTime;
    }

    public static void main(String[] args) {
        String dt = "7 Сентября, 2023 15:39";
        System.out.println(convertDateTime(dt));
    }
}
