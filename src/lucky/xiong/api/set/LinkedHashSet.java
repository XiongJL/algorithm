package lucky.xiong.api.set;

import java.util.Set;

/**
 * @author XiongJl
 * @date 2021/4/9 9:17
 */
public class LinkedHashSet {
    public static void main(String[] args) {
        Set<String> use = new java.util.LinkedHashSet<>();
        use.add("www");
        use.add("eq");
        use.add("wwwerw");
        use.add("www");
        /**
         * LinkedHashSet集合也是根据元素的hashCode值来决定元素的存储位置，但它同时使用链表维护元素的次序，
         * 这样使得元素看起来是以插入的顺序保存的，也就是说当遍历集合LinkedHashSet集合里的元素时，
         * 集合将会按元素的添加顺序来访问集合里的元素。
         *
         * 同时由于使用HashSet，所以也不能重复
         */
        System.out.println(use);
    }
}
