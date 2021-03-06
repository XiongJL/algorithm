package lucky.xiong.basic;

/**
 * @author XiongJl
 * @date 2021/1/23 10:10
 */
public class 商与余数 {
    public static void main(String[] args) {
        int remainder = 27000%4;
        int merchant = 27000/4;
        System.out.println("10/3 -> remainder: "+remainder+" merchant : " + merchant);

        int remainder2 = 0%4;
        int merchant2 = 0/3;
        System.out.println("remainder: "+remainder2+" merchant : " + merchant2);


    }
}
