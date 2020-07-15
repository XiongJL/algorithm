package lucky.xiong;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 示例 1:
 *
 * 输入:
 * "tree"
 *
 * 输出:
 * "eert"
 *
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 *
 * 输入:
 * "cccaaa"
 *
 * 输出:
 * "cccaaa"
 *
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 *
 * 输入:
 * "Aabb"
 *
 * 输出:
 * "bbAa"
 *
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author XiongJl
 * @date 2020/6/23 10:23
 *
 * @url 个人博客 https://sqhl.xyz/article?id=12
 */
public class 根据字符串出现频率排序 {

    public static void main(String[] args) {
        String str = "cxzvsfsczxczxcZXcx";
        System.out.println(frequencySort(str));
        System.out.println(frequencySortV2(str));
        System.out.println(frequencySortV3(str));
    }

    /**
     * 利用Map去存储每个Character，然后根据value排序，最后迭代Map，
     * 使用StringBuild.append(Char s) 重新输出结果。
     * @param s
     * @return
     */
    public static String frequencySort(String s){
        long startTime = System.currentTimeMillis();
        if (s.length()<=2){
            long endTime = System.currentTimeMillis();
            System.out.println("frequencySort运行时间： " + (endTime - startTime) + "ms");
            return s;
        }
        int mapSize = (int)Math.rint(s.length()/0.75);
        Map<Character,Integer> map = new HashMap<>(mapSize);
        // 循环保存所有字符出现的次数
        for (char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        // 排序map
        // 转换成list
        List<Map.Entry<Character,Integer>> sortList = new ArrayList<>(map.entrySet());
        sortList.sort(new Comparator<Map.Entry<Character, Integer>>() {
                          @Override
                          public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                              return o2.getValue().compareTo(o1.getValue());
                          }
                      });

        System.out.println(sortList);

        StringBuilder sbuilder = new StringBuilder();
        sortList.forEach(e ->{
            for(int i=0;i<e.getValue();i++){
                sbuilder.append(e.getKey());
            }
        });
        long endTime = System.currentTimeMillis();
        System.out.println("frequencySort运行时间： " + (endTime - startTime) + "ms");
        return sbuilder.toString();
    }
    /**
     * 效率无太大变更，主要是压缩代码量 ， 以及显得更专业， 在leetcode中的运行时间和内存消耗都比让上面的方法略微超出。
     * 利用Map去存储每个Character，然后根据value排序，最后迭代Map，
     * 使用StringBuild.append(Char s) 重新输出结果。
     * @param s
     * @return
     */
    public static String frequencySortV2(String s){
        long startTime = System.currentTimeMillis();
        if (s.length()<=2){
            long endTime = System.currentTimeMillis();
            System.out.println("frequencySort运行时间： " + (endTime - startTime) + "ms");
            return s;
        }
        int mapSize = (int)Math.rint(s.length()/0.75);
        Map<Character,Integer> map = new HashMap<>(mapSize);
        // 循环保存所有字符出现的次数
        for (char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        StringBuilder sb = new StringBuilder();
        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue,Comparator.reverseOrder()))
                .forEach(entry -> IntStream.range(0,entry.getValue()).forEach(i -> sb.append(entry.getKey())));
        long endTime = System.currentTimeMillis();
        System.out.println("frequencySortV2： " + (endTime - startTime) + "ms");
        return sb.toString();
    }

    /**
     * Jason Lee
     * 发布于 2020-02-07
     * 解题思路
     * Java 大顶堆
     * 执行用时 :4 ms, 在所有 Java 提交中击败了99.71%的用户
     * 内存消耗 :37.3 MB, 在所有 Java 提交中击败了98.86%的用户
     *
     * 首先这里采用常规方法统计字符频率 这里固定申请了256个空间完全够常用字符集了，当然这里也是可优化的点。
     * 当我们统计到字符频率的时候，就可以利用堆排序将频率高的字符进行“上浮”，最后我们在遍历的时候就是频率最高字符在首位，然后再一次打印即可。
     *
     * 作者：PM-LEE
     * 链接：https://leetcode-cn.com/problems/sort-characters-by-frequency/solution/java-da-ding-dui-by-pm-lee/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public static String frequencySortV3(String s){
        long startTime = System.currentTimeMillis();
        //初始化字母数组
        int[] latters = new int[256];
        //填充数组
        for(char c:s.toCharArray()){
            latters[c]++;
        }
        //排序
        PriorityQueue<Latter> queue = new PriorityQueue<>();

        for (int i = 0;i<latters.length;i++){
            if (latters[i]!=0){
                queue.add(new Latter((char) i,latters[i]));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();

        while (!queue.isEmpty()){
            Latter latter = queue.poll();
            for (int i =0;i<latter.count;i++) {
                stringBuilder.append(latter.latter);
            }
        }



        long endTime = System.currentTimeMillis();
        System.out.println("借鉴 大堆顶 frequencySortV3： " + (endTime - startTime) + "ms");
        return stringBuilder.toString();

    }

    public static class Latter implements Comparable<Latter>{
        public char latter = '0';
        public int count = 0;

        public Latter(char latter, int count) {
            this.latter = latter;
            this.count = count;
        }

        @Override
        public int compareTo(Latter o) {
            return o.count - this.count;
        }
    }



}
