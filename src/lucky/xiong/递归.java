package lucky.xiong;

/**
 * @author XiongJl
 * @date 2020/9/24 14:56
 */
public class 递归 {
    static int test(int n,int k){
        if (k==0||k==n) return 1;
        return test(n-1,k-1) + test(n-1,k);
    }
    public static void main(String[] args) {
        System.out.println(test(7,2));
    }
}
