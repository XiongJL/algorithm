package lucky.xiong.basic;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @author XiongJl
 * @date 2021/1/23 10:30
 */
public class TestEnums {

    public static void main(String[] args) {

        System.out.println("B".equals(OneValueEnums.B.toString()));

        System.out.println(OneValueEnums.B.toString());
        System.out.println(OneValueEnums.B.name());


        // 构造枚举类。
        TwoValueEnums b = TwoValueEnums.valueOf("B");
        System.out.println(b.getCode(8));

        // 获取所有常量
        for (TwoValueEnums value : TwoValueEnums.values()) {
            System.out.println(value);
        }



    }
}
