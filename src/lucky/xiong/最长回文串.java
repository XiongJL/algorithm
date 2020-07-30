package lucky.xiong;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * @author XiongJl
 * @date 2020/7/15 13:43
 * @url https://sqhl.xyz/article?id=26 博客
 *
 */
public class 最长回文串 {

    public static void main(String[] args) {

    }


    class Solution{
        /**
         * 动态规划
         * @param s
         * @return
         */
        public String longestPalindrome(String s){
            // 长度小于2 直接返回
            int len = s.length();
            if (len<2){
                return s;
            }

            int maxLen = 1;
            int begin = 0;

            // 1. 状态定义
            // dp[i][j] 表示 s[i...j]是否是回文串

            // 2. 初始化 , 将每个元素本身确定为回文
            boolean [][] dp = new boolean[len][len];
            for (int i = 0 ; i< len;i++){
                dp[i][i] = true;
            }

            char[] chars = s.toCharArray();
            // 3. 状态转移
            // 注意： 先填左下角
            // 填表规则： 先一列一列的填写，再一行一行的填，保证左下方的单元格先进行计算
            for (int j = 1; j< len; j++){
                for (int i = 0; i< j;i++){
                    // 头尾字符不相等， 不是回文串
                    if (chars[i] != chars[j]){
                        dp[i][j] = false;
                    }else{
                        // 头尾相等的情况
                        // 考虑头尾去掉以后没有字符串剩余，或者剩下一个字符串的时候，肯定是回文串
                        if (j-i < 3 ){
                            dp[i][j] = true;
                        }else {
                            // 状态转移
                            dp[i][j] = dp[i+1][j-1];
                        }
                    }

                    // 只要dp[i][j] == true 成立，表示 s[i...j] 是否是回文串
                    // 此时更新记录回文长度和其实位置
                    if (dp[i][j] && j - i + 1 > maxLen){
                        maxLen = j - i + 1;
                        begin = i;
                    }

                }
            }
            // 返回值
            return s.substring(begin,begin + maxLen);
        }


    }
}
