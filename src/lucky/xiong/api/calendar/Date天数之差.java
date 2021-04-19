package lucky.xiong.api.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author XiongJl
 * @date 2021/4/19 10:40
 */
public class Date天数之差 {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date d1 = sdf.parse("2012-02-01 23:10:10");

        Date d2 = sdf.parse("2012-01-31 01:00:00");

        System.out.println(differentDaysByMillisecond(d1, d2));

        System.out.println(daysBetween("2012-09-08 10:10:10", "2012-09-15 00:00:00"));
    }

    public static long differentDaysByMillisecond(Date date1,Date date2)
    {
        long days = Math.abs((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        smdate = sdf.parse(sdf.format(smdate));

        bdate = sdf.parse(sdf.format(bdate));

        Calendar cal = Calendar.getInstance();

        cal.setTime(smdate);

        long time1 = cal.getTimeInMillis();

        cal.setTime(bdate);

        long time2 = cal.getTimeInMillis();

        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));

    }

    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();

        cal.setTime(sdf.parse(smdate));

        long time1 = cal.getTimeInMillis();

        cal.setTime(sdf.parse(bdate));

        long time2 = cal.getTimeInMillis();

        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));

    }

}



