package lucky.xiong.实际工作问题;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author XiongJl
 * @date 2020/8/19 13:32
 */
public class 实体类名转换为驼峰 {

    public static void main(String[] args) {
        System.out.println(humpToLine2("eventStationIdDescr"));
    }
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    public static String humpToLine2(String string) {
        Matcher matcher = humpPattern.matcher(string);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
