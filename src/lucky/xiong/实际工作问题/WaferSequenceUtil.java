package lucky.xiong.实际工作问题;


import java.util.Calendar;
import java.util.Date;

/**
 * 用于生成Wafer序列号的工具类
 * 格式：YMDDXXX
 *  Y:  年代码 A-Z
 * 	M: 月代码 1-9,A,B,C
 * 	DD: 日期  01-31 (固定两位数字)
 * 	XXX: 流水码
 *
 *
 * @author XiongJl
 * @date 2021/1/22 13:46
 */
public class WaferSequenceUtil {

    /**
     * 从A开始代表2020年，一直到Z，代表2045年。
     * 也就是说，极限为2045年。
     */
    public static final int A_YEAR = 2020;
    public static final int A_MONTH = 10;
    public static final int B_MONTH = 11;
    public static final int C_MONTH = 12;
    /**
     * 流水码忽略的数字与大写字母
     */
//    public static final String[] ignore = {"0","O","1","L","2","Z"};

    public static void main(String[] args) {
        System.out.println(fullCodeToIntCode("B124334"));
    }



    /**
     * 根据已有得完整流水码返回新的流水码
     * @param date 今日日期
     * @param fullCode volatile 的完整流水编码，若是今天第一份流水码，应为null
     * @return 当fullCode为负数，或其超过最大值27000返回null
     */
    public synchronized static String getNewYMDDSeq(Date date,String fullCode){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // 获取年
        StringBuffer code = new StringBuffer();
        if (year>=A_YEAR){
            int def = year - A_YEAR;
            char strYear = (char) ('A'+def);
            code.append(strYear) ;
        }else {
            // 异常情况，直接设置为year
            code.append(year) ;
        }
        // 获取月
        switch (month){
            case A_MONTH:
                code.append("A");
                break;
            case B_MONTH:
                code.append("B");
                break;
            case C_MONTH:
                code.append("C");
                break;
            default:
                code.append(month);
                break;
        }

        // 获取日
        String strDay = String.valueOf(day);
        if (strDay.length()<2){
            code.append("0").append(strDay);
        }else{
            code.append(strDay);
        }

        /**
         *
         * 获取流水码
         */
        // 定义后三位流水码
        String streamCode ;
        if (fullCode==null){
            // 第一次加载
            code.append(WaferStreamCodeEnums.THREE.getCode())
                    .append(WaferStreamCodeEnums.THREE.getCode())
                    .append(WaferStreamCodeEnums.FOUR.getCode());
        }else{
            // 已有今日流水码
            streamCode = fullCode.substring(4);
            int seq = convertToIntCode(streamCode);
            if (seq==-1){
                return null;
            }
            // 转换为数字后加1
            seq += seq ;
            // 转换为字符串code
            String newStreamCode = convertToStringCode(seq);
            if (newStreamCode == null){
                return null;
            }
            code.append(newStreamCode);
        }

        return code.toString();

    }


    /**
     * 数字 25123 的转换算法：
     * 25123/（30*30）商27 余823
     * 823/30商27 余13
     * 即 25123 = 27*30*30+27*30+13*1
     *
     * 所以流水码为 WWG
     *
     * 30 进制
     * XXX的取值范围为0 到 30^3-1 （26999）
     * 极限为：27000张Wafer /天
     *
     * @param intCode 例如 25800
     * @return 当intCodec超过27000,或为负数，返回Null ； 正常返回三位字符串流水
     *
     */
    public static String convertToStringCode(int intCode){
        // 变更序列为流水码
        int hundred = intCode/(30*30);
        WaferStreamCodeEnums hundredEnum = WaferStreamCodeEnums.getEnumByValue(hundred);
        // 超过最大值27000
        if (hundredEnum==null){return null;}
        int hundredRemainder = intCode%(30*30);
        int ten = hundredRemainder / 30;
        WaferStreamCodeEnums tenEnum = WaferStreamCodeEnums.getEnumByValue(ten);
        int unit = hundredRemainder % 30;
        WaferStreamCodeEnums unitEnum = WaferStreamCodeEnums.getEnumByValue(unit);
        return hundredEnum.getCode()+tenEnum.getCode()+unitEnum.getCode();

    }

    /**
     * 字符串流水码转换为10进制数
     * @param streamCode 字符串流水码，3位长度
     * @return 若字符串长度不为3返回-1,其余情况返回正整数
     */
    public static int convertToIntCode(String streamCode){
        char[] codes = streamCode.toCharArray();
        if (codes.length!=3){
            return -1;
        }
        int sum = 0;
        // 第一位为百位。
        WaferStreamCodeEnums hundredEnum = WaferStreamCodeEnums.getEnumByCode(String.valueOf(codes[0]));
        if (hundredEnum==null){
            return -1;
        }
        int hundred = hundredEnum.getValue();
        sum += hundred*30*30;
        // 第二位为十位
        WaferStreamCodeEnums tenEnum = WaferStreamCodeEnums.getEnumByCode(String.valueOf(codes[1]));
        if (tenEnum==null){
            return -1;
        }
        int ten = tenEnum.getValue();
        sum += ten*30;
        // 第三位为个位
        WaferStreamCodeEnums unitEnum = WaferStreamCodeEnums.getEnumByCode(String.valueOf(codes[2]));
        if (unitEnum==null){
            return -1;
        }
        int unit = unitEnum.getValue();
        sum += unit;
        return sum;
    }
    /**
     * 完整LotId转变为序列码
     * @param fullCode 完整LOT_ID
     * @return int
     */
    public static String fullCodeToIntCode(String fullCode){
        if (fullCode==null || fullCode.length()!=7){
            return null;
        }
        char[] chars = fullCode.toCharArray();
        int finalSeq = 0;
        StringBuffer res = new StringBuffer();

        // 处理年
        int yearAscii = chars[0];
        int def = yearAscii - 'A';
        int year = A_YEAR + def;
        res.append(year);
        // 处理月
        // 获取月
        switch (chars[1]){
            case 'A':
                res.append("10");
                break;
            case 'B':
                res.append("11");
                break;
            case 'C':
                res.append("12");
                break;
            default:
                res.append(0).append(chars[1]);

                break;
        }
        // 处理日
        res.append(chars[2]).append(chars[3]);
        res.append("-");
        // 处理后三位
        StringBuffer sb = new StringBuffer();
        sb.append(chars[4]).append(chars[5]).append(chars[6]);
        int steamInt = convertToIntCode(sb.toString());
        res.append(steamInt);
        return res.toString();
    }

}
