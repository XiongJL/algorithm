package lucky.xiong;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * @author XiongJl
 * @date 2020/8/6 14:15
 */

public class 整数反转 {
    /**
     * 最大整数
     * 2^31-1=2147483647,-2^31=-2147483648
     */
    static class IntegerReverse{
        public int reverse(int x) {
            int ans = 0;
            while (x != 0) {
                // 判断是否溢出
                // 当超出int范围时，会使用long类型暂存，增加空间消耗。
                if ((ans * 10) / 10 != ans) {
                    ans = 0;
                    break;
                }
                ans = ans * 10 + x % 10;
                x = x / 10;
            }
            return ans;

        }
    }

    public static void main(String[] args) {
        IntegerReverse integerReverse = new IntegerReverse();
        System.out.println(integerReverse.reverse(-2147483648));
    }
}
