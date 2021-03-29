package lucky.xiong.basic;

import java.util.Random;

/**
 * @author XiongJl
 * @date 2021/3/18 14:54
 */
public class 随机数 {
    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println(rand.nextInt(5));
        System.out.println(rand.nextInt(5));
        System.out.println(rand.nextInt(5));
        System.out.println(rand.nextInt(5));
    }
}
