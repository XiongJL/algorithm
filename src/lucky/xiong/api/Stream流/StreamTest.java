package lucky.xiong.api.Stream流;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 学习
 * https://www.jianshu.com/p/eb331282f2f3
 * @author XiongJl
 * @date 2021/1/18 16:24
 */
public class StreamTest {

    public static void main(String[] args) {
        learnStream();
    }
    private static void learnStream(){
        // 创建一个乱序的List
        List<Integer> lists = new ArrayList<>();
        lists.add(4);
        lists.add(2);
        lists.add(1);
        lists.add(3);
        lists.add(7);
        lists.add(9);
        lists.add(6);

        System.out.println("原始List");
        for (Integer list : lists) {
            //4213796
            System.out.print(list+ " ");
        }
        System.out.println();

        // 最小值
        System.out.println("List中最小值为：");
        Stream<Integer> sream = lists.stream();
        Optional<Integer> min = sream.min(Integer::compareTo);
        min.ifPresent(System.out::println);

        // 最大值
        System.out.println("List中最大值为：");
        // 9
        lists.stream().max(Integer::compareTo).ifPresent(System.out::println);

        // 排序
        System.out.println("List流排序");
        // 1 2 3 4 6 7 9
        lists.stream().sorted().forEach(e-> System.out.print(e + " "));

        System.out.println();

        // 过滤
        System.out.println("对List过滤，只保留大于3的元素：");
        // 4 7 9 6
        lists.stream()
                .filter(e -> e > 3)
                .forEach(e-> System.out.print(e + " "));

        System.out.println();

        // 过滤
        System.out.println("过滤List，只保留大于2并小于7的元素：");
        // 346
        lists.stream()
                .filter(e -> e > 2)
                .filter(e -> e < 7)
                .sorted(Integer::compareTo)
                .forEach(System.out::print);
        System.out.println();

        // 原List 数据并无变化
        System.out.println("原List数据");
        // 4 2 1 3 7 9 6
        for (Integer list : lists) {
            System.out.print(list + " ");
        }
    }
}
