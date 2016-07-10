import java.util.Date;
import java.util.Calendar;
public class TestDateAndCalendar {
    public static void main(String[] args) {

        System.out.println("Testing Date\n");
        
        Date today = new Date();
        System.out.println(String.format("%tc", today));
        System.out.println(String.format("%tr", today));
        System.out.println(String.format("%tA %tB %td", today, today, today));
        System.out.println(String.format("%tA %<tB %<td", today));

        System.out.println("\nTesting Calendar\n");
        
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 6, 10, 13, 12);
        long day1 = cal.getTimeInMillis();
        day1 += 1000 * 60 * 60;
        cal.setTimeInMillis(day1);
        System.out.println("new hour "+ cal.get(cal.HOUR_OF_DAY));
        
        cal.add(cal.DATE, 35) ;
        System.out.println("add 35 days " + cal.getTime());

        cal.roll(cal.DATE, 35);
        System.out.println("roll 35 days" + cal.getTime());
        
        cal.set(cal.DATE, 1);
        System.out.println("set to 1 " + cal.getTime());

    }
}