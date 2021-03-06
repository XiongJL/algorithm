package lucky.xiong.api.string;

/**
 * @author XiongJl
 * @date 2021/1/23 11:46
 */
public class StringHandle {

    public static void main(String[] args) {

        String origin = "YMDDX23";

        System.out.println(origin.substring(4));


        // 判断char和String是否相等
        System.out.println("A".equals(String.valueOf('A'))); // true
        System.out.println("A".equals('A')); //false
        System.out.println("A"==String.valueOf('A')); // false
    }
}
