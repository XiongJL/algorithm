package lucky.xiong;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * @author XiongJl
 * @date 2020/6/10 14:20
 * @url 个人博客 https://sqhl.xyz/article?id=13
 *
 */
// FIXME : 直接用中文类名了，方便未来查找，但注意，这不符合Java命名规则
public class 无重复字符的最长子串 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));

        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("au"));
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] str = s.toCharArray();
        // 当只有一位长度，需要特殊处理
        if (str.length == 1){
            return 1;
        }
        // 返回的最大子串长度
        int max = 0;
        // 记录从第几位开始向后找
        int count = 0;
        Set<Character> set = new HashSet<>();
        while(count<=str.length){
            int innerLen = 1;
            set.clear();
            for (int i = count ; i < str.length ; i++){
                if (!set.add(str[i])){
                    // 查找下一个
                    if (max<innerLen-1){
                        max = innerLen-1;
                    }
                    break;
                }
                innerLen++;
            }
            if (max<innerLen-1){
                max = innerLen-1;
            }
            count ++;
            // 剩下的长度没有max长，就无须循环了
            if (max>=str.length-count){
                break;
            }
        }
        return max;
    }
}
