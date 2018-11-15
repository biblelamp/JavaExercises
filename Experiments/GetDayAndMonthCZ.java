import java.util.Date;
import java.util.Calendar;

class GetDayAndMonthCZ {
    final static String[] months = {"ledna", "února", "března", "dubna", "května", "června",
        "července", "srpna", "září", "října", "listopadu", "prosince"};
    static Calendar calendar;

    public static void main(String[] args) {
        System.out.println(getDayAndMonthCZ());
        System.out.println(getDayAndMonthCZ(new Date(2018, 10, 23)));
    }

    public static String getDayAndMonthCZ() {
        return getDayAndMonthCZ(new Date());
    }

    public static String getDayAndMonthCZ(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        return Integer.toString(day) + ". " + months[month];
    }
}
