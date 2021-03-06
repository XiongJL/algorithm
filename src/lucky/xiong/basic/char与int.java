package lucky.xiong.basic;

/**
 * @author XiongJl
 * @date 2021/1/22 14:20
 */
public class char与int {


    public static void main(String[] args) {
        int A_YEAR = 1;
        char c2 = (char) ('A' + A_YEAR);
        int num2 = 'a' + 1;
        System.out.println("c2:          " + c2);
        System.out.println("num2:        " + num2);
        System.out.println("(char) num2: " + (char) num2);
    }
}
