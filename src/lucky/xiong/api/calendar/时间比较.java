package lucky.xiong.api.calendar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author XiongJl
 * @date 2021/4/15 13:26
 */
public class 时间比较 {
    public static void main(String[] args) throws ParseException {
        String DateStr1 = "2011-10-12 10:20:16";
        String DateStr2 = "2011-10-07 15:50:35";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime1 = dateFormat.parse(DateStr1);
        Date dateTime2 = dateFormat.parse(DateStr2);
        int i = dateTime1.compareTo(dateTime2);
        System.out.println(i);
    }
}
