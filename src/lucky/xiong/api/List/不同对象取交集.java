package lucky.xiong.api.List;


import java.util.*;
import java.util.stream.Collectors;

/**
 * @author XiongJl
 * @date 2021/4/9 10:38
 */
public class 不同对象取交集 {

    public static void main(String[] args) {
        List<A> as = new ArrayList<>();
        List<B> bs = new ArrayList<>();
        Set<B> bss = new HashSet<>();
        for (int i = 0; i < 5;i++){
            A a = new A(i,"xiong"+i,i+"");
            as.add(a);
            System.out.println("生成的a"+a);
            if (i%2==0) {continue;}
            B b = new B("xiong"+i,i+"",i);
            System.out.println("生成的b"+b);
            bs.add(b);
        }
        bs.add(new B("xiong"+3,3+"",3));
        // 方法1
        List<B> list = bs.stream().filter(b -> {
            List<Boolean> result = as.stream()
                    .map(a -> b.getName().equals(a.getName()))
                    .collect(Collectors.toList());
//            System.out.println(result);
            return result.indexOf(true) > -1;
        }).collect(Collectors.toList());
        System.err.println(list);

//
//        List<Integer> list1 = Arrays.asList(1,2,3,4,5);
//        List<Integer> list2 = Arrays.asList(5,6,7);
//        List<Integer> list = list1.stream().filter(one -> {
//            List<Boolean> result = list2.stream().map(two -> one == two).collect(Collectors.toList());
//            if (result.indexOf(true) > -1)
//                return true;
//            return false;
//        }).collect(Collectors.toList());
//        list.stream().forEach(System.out::println);
    }

}
class A {
    private int age;
    private String name;
    private String gender;

    public A(int age, String name, String gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "{"
                + "\"age\":"
                + age
                + ",\"name\":\""
                + name + '\"'
                + ",\"gender\":\""
                + gender + '\"'
                + "}";

    }
}

class B {
    private String name;
    private String birthday;
    private int mmm;

    public B(String name, String birthday, int mmm) {
        this.name = name;
        this.birthday = birthday;
        this.mmm = mmm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getMmm() {
        return mmm;
    }

    public void setMmm(int mmm) {
        this.mmm = mmm;
    }

    @Override
    public String toString() {
        return "{"
                + "\"name\":\""
                + name + '\"'
                + ",\"birthday\":\""
                + birthday + '\"'
                + ",\"mmm\":"
                + mmm
                + "}";

    }
}