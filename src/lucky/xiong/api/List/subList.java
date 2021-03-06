package lucky.xiong.api.List;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiongJl
 * @date 2021/1/24 11:10
 */
public class subList {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            list.add(i);
        }
        System.out.println("原数组为：");
        list.forEach(e-> System.out.print(e+" "));

        List<Integer> sub = list.subList(2, 2+5);
        System.out.println();
        System.out.println("截取的数组为：");
        sub.forEach(e-> System.out.print(e+" "));
        System.out.println();

        System.out.println("原数组为：");
        list.forEach(e-> System.out.print(e+" "));

        int index = 0;
        for (int j=0;j<3;j++){
            int count = 2 ;

            list.subList(index, index+count);
        }
    }
}
