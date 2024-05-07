package lesson45.homework;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #45
 *
 * @author Sergey Iryupin
 * @version 06-May-24
 */
public class HomeWork45 {
    public static void main(String[] args) {
        List<LocalDate> dates = new ArrayList<>(List.of(
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                LocalDate.now().plusDays(3),
                LocalDate.now().plusDays(1)
        ));
        System.out.println(dates);
        System.out.println(getIntervalOfDay(dates));
        System.out.println(dates);
    }

    static long getIntervalOfDay(final List<LocalDate> dates) {
        List<LocalDate> copyDates = new ArrayList<>(dates);
        Collections.sort(copyDates);
        return copyDates.get(0).until(copyDates.get(copyDates.size() - 1), ChronoUnit.DAYS);
    }
}
