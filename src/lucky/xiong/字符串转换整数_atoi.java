package lucky.xiong;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * <p>
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 * <p>
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 * <p>
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 * <p>
 * 提示：
 * <p>
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 *
 * @author XiongJl
 * @date 2020/8/7 9:42
 */
public class 字符串转换整数_atoi {


    public static int myAtoi(String str) {
        /*根据String.trim源码，去除前导空格*/
        int len = str.length();
        // 起始下标
        int st = 0;
        char[] val = str.toCharArray();
        //  此处单引号 ‘ ’ 表示空格，在ASCII中，其值为20 ，小于20的都是特殊含义，不会存在String中。
        while ((st < len) && (val[st] <= ' ')) {
            // 下标前进
            st++;
        }
        // 当去除空格后下标到了末尾
        if (st == len) {
            return 0;
        }
        // 判断首位
        // 是否负数
        boolean negative = false;
        if (val[st] == '-') {
            st++;
            negative = true;
        } else if (val[st] == '+') {
            st++;
        } else if (!Character.isDigit(val[st])) {
            // 其他字符，同时不是数字的
            return 0;
        }
        // 处理数字
        int ans = 0;
        while (st < len && Character.isDigit(val[st])) {
            // 当下标小于数组长度，且当前字符是数字.转换为数字
            int that = val[st] - '0';
            // 判断转换后的数字是否溢出
            /**
             * 最大整数
             * 2^31-1 = 2147483647,-2^31 = -2147483648
             */
//            if (((ans * 10) + that )/ 10 != ans) {
            // 少一次乘法
            if (ans > (Integer.MAX_VALUE - that) / 10) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            // 加上下一个字符组成的数字。 进位
            ans = ans * 10 + that;
            // 下标前进
            st++;
        }
        return negative ? -ans : ans;
    }

    /**
     * 简单的数字判断
     * @param c
     * @return
     */
    public boolean isDigit(char c){
        return c>='0' && c<='9';
    }
    public static void main(String[] args) {
        /**
         *  ASCII 中，字符 1 的 10 进制值是 49
         *  字符 0 的 10 进制值是 48
         *  也就是说，char 的加减实际上是对应的十进制数加减。
         */
        char a = '1';
        int b = a;
        int c = a - '0';
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        char s = 'e' - ' ';
        System.out.println(s);

        System.out.println(myAtoi("2147483648"));

    }
}
