import java.util.Date;
import java.util.Calendar;

class GetDayAndMonthCZ {
    final String[] months = {"ledna", "února", "března", "dubna", "května", "června",
        "července", "srpna", "září", "října", "listopadu", "prosince"};
    Calendar calendar;

    public static void main(String[] args) {
        GetDayAndMonthCZ gdm = new GetDayAndMonthCZ();

        System.out.println(gdm.getDayAndMonthCZ());

        gdm.add(1);
        System.out.println(gdm.getDayAndMonthCZ());

        gdm.add(-2);
        System.out.println(gdm.getDayAndMonthCZ());
    }

    public GetDayAndMonthCZ() {
        calendar = Calendar.getInstance();
        calendar.setTime(new Date());
    }

    public void add(int day) {
        calendar.add(Calendar.DAY_OF_MONTH, day);
    }

    public String getDayAndMonthCZ() {
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        return Integer.toString(day) + ". " + months[month];
    }
}
