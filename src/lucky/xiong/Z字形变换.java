package lucky.xiong;

import java.util.ArrayList;
import java.util.List;

/**
 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

 L   C   I   R
 E T O E S I I G
 E   D   H   N
 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

 请你实现这个将字符串进行指定行数变换的函数：

 string convert(string s, int numRows);
 示例 1:

 输入: s = "LEETCODEISHIRING", numRows = 3
 输出: "LCIRETOESIIGEDHN"
 示例 2:

 输入: s = "LEETCODEISHIRING", numRows = 4
 输出: "LDREOEIIECIHNTSG"
 解释:

 L     D     R
 E   O E   I I
 E C   I H   N
 T     S     G

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/zigzag-conversion


 * @author XiongJl
 * @date 2020/7/31 9:33
 *
 *
 */
public class Z字形变换 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        long start = System.currentTimeMillis();
        System.out.println(solution.convert("LEETCODsdfadffasfewdsfasfhkvxin" +
                "kjsdfhaklsfhklasnklxcnvnasidfasinfkasfdasfasdffadEISHIRING",10));
        System.out.println("耗时："+(System.currentTimeMillis()-start));
        long start2 = System.currentTimeMillis();
        System.out.println(solution.convert2("LEETCODsdfadffasfewdsfasfhkvxin" +
                "kjsdfhaklsfhklasnklxcnvnasidfasinfkasfdasfasdffadEISHIRING",10));
        System.out.println("耗时："+(System.currentTimeMillis()-start2));


    }

    static class Solution {
        /**
         *
         * 方法1：按行排序
         * 思路
         *
         * 通过从左向右迭代字符串，我们可以轻松地确定字符位于 Z 字形图案中的哪一行。
         *
         * 算法
         *
         * 我们可以使用 \text{min}( \text{numRows}, \text{len}(s))min(numRows,len(s)) 个列表来表示 Z 字形图案中的非空行。
         *
         * 从左到右迭代 ss，将每个字符添加到合适的行。可以使用当前行和当前方向这两个变量对合适的行进行跟踪。
         *
         * 只有当我们向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变
         *
         * 图解 图解：https://leetcode-cn.com/problems/zigzag-conversion/solution/hua-jie-suan-fa-6-z-zi-xing-bian-huan-by-guanpengc/
         *
         * 复杂度分析
         *
         * 时间复杂度：O(n)，其中 n == len(s)
         * 空间复杂度：O(n)
         *
         * @param s
         * @param numRows
         * @return
         */
        public  String convert(String s, int numRows) {
            if (numRows==1){
                return s;
            }

            List<StringBuilder> rows = new ArrayList<>();
            /**
             * Math.min(numRows,s.length())  , 可以求出非空的行数。
             *              1     1     = 1行
             *              1     2     = 规定了1行
             *              2     1     = 只有一个字符串，也只能一行
             */
            for (int i = 0; i < Math.min(numRows,s.length());i++){
                // 添加每行的输出流
                rows.add(new StringBuilder());
            }

            // 当前行数
            int curRow = 0;
            // 开始向下的flag标记
            boolean goingDown = false;

            for (char c:s.toCharArray()){
                // 把当前行的赋值到对应的输出流中
                rows.get(curRow).append(c);
                // 当当前行数是0 必然是转折点，当前行数等于总行数是，必然也是转折点
                if (curRow == 0 || curRow == numRows -1 ){
                    goingDown = !goingDown;
                }
                // 如果是向下，则操作行数+1 ，反之 -1
                curRow += goingDown ? 1 : -1;

            }

            StringBuilder res = new StringBuilder();
            for (StringBuilder row : rows){
                res.append(row);
            }

            return res.toString();
        }


        /**
         * 按行访问
         * @param s
         * @param numRows
         * @return
         */
        public String convert2(String s, int numRows){

            if (numRows == 1) return s;

            StringBuilder ret = new StringBuilder();
            int n = s.length();
            int cycleLen = 2 * numRows - 2;

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j + i < n; j += cycleLen) {
                    ret.append(s.charAt(j + i));
                    if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                        ret.append(s.charAt(j + cycleLen - i));
                }
            }
            return ret.toString();

        }
    }
}
