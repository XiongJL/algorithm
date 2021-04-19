package lucky.xiong.basic;

/**
 * @author XiongJl
 * @date 2021/4/2 14:19
 */
public class while循环 {
    public static void main(String[] args) {
        long c = 1;
        do {
            System.out.println("doSomething" + c);
            c = c-1;
        }while (c>=0);

    }
}
