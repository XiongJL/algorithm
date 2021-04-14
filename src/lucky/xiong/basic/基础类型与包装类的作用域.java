package lucky.xiong.basic;

/**
 * @author XiongJl
 * @date 2021/4/14 13:24
 */
public class 基础类型与包装类的作用域 {
    public static void main(String[] args) {
        //基础类型定义在栈中，只在当前方法生效
        boolean a = true;
        int b = 2;
        Boolean aa = true;
        // Boolean中的 boolean值使用final定义，不可修改。

        System.out.println("初始结果");
        System.out.println(a);
        System.out.println(b);
        setBoolean(a,b);
        // a 不会再方法外被变更
        System.out.println("最终结果");
        System.out.println(a);
        System.out.println(b);
    }

    public static void setBoolean(boolean c,int b ){
        c = false;
        b=10;
        System.out.println("尝试变更");
        System.out.println(c);
        System.out.println(b);
    }





}
