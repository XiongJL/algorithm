package lucky.xiong.api.calendar;

import java.util.Calendar;
import java.util.Date;

/**
 * @author XiongJl
 * @date 2021/1/22 14:02
 */
public class CalendarTest {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(year +" - "+month+" - "+day);



    }
}
