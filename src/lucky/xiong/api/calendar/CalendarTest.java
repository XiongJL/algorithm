package lucky.xiong.api.calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author XiongJl
 * @date 2021/1/22 14:02
 */
public class CalendarTest {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        Calendar calendar = Calendar.getInstance();
        // 不设置date就是当前时间
//        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(year +" - "+month+" - "+day);

        Date date1 = testSetTime("08:11:23");

        System.out.println(date1);
        Date date2 = testAdd(date1, 50);
        System.out.println(date2);

        // 获取当前周几
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        int i1 = i == 1 ? 7 : i - 1;
        System.out.println("今天周"+i1);
    }

    public  static Date testSetTime(String HHmmss){
        Calendar calendar = Calendar.getInstance();
        String[] split = HHmmss.split(":");
        if (split.length!=3){
            return null;
        }
        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(split[0]));
        calendar.set(Calendar.MINUTE,Integer.parseInt(split[1]));
        calendar.set(Calendar.SECOND,Integer.parseInt(split[2]));
        return calendar.getTime();
    }

    public static Date testAdd(Date startTime ,Integer minute){
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.MINUTE,minute);
        // roll 只是单位内的变化，不会影响到其他字段
//        calendar.roll(Calendar.MINUTE,minute);
        return calendar.getTime();
    }
}
