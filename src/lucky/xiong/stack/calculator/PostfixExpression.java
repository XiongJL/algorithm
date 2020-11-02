package lucky.xiong.stack.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 网络收集的解法
 */
public class PostfixExpression {
    public static void main(String[] args) {
        //完成中缀表达式向后缀表达式的转换
        String expression = "1+((2+3)*4)-10";
        List<String> ls1 = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的list: " +ls1); //=> ArrayList [1, +, (, (, 2, +, 3, ), *, 4, ), -, 2]
        List<String> suffixExpressionList = parseSuffixExpreesionList(ls1);
        System.out.println("后缀表达式对应的list: " + suffixExpressionList);
    }

    //获取中缀表达式的数组
    public static List<String> toInfixExpressionList(String s){
        List<String> ls = new ArrayList<>();
        int i = 0; // 遍历的指针
        StringBuilder str; // 用于多位数的拼接
        char c; //存放遍历到的字符
        do{
            //如果c是一个非数字，就需要加入到ls中去
            if ((c = s.charAt(i)) < 48 || (c=s.charAt(i)) > 57){
                ls.add("" + c);
                i++;
            }else {
                //如果是一个数字 需要考虑多位数的问题
                str = new StringBuilder();
                while (i < s.length() && (c=s.charAt(i))>=48 && (c=s.charAt(i))<=57){
                    str.append(c); //拼接
                    i++;
                }
                ls.add(str.toString());
            }
        }while (i < s.length());

        return ls;
    }

    //将中缀表达式转换为对应的后缀表达式
    public static List<String> parseSuffixExpreesionList(List<String> ls){
        //定义两个栈
        Stack<String> s1 = new Stack<>(); //符号栈
        //因为s2的栈在整个转换过程中没有pop操作 而且后边要逆序输出
        //因此直接使用List来替代
        List<String> s2 = new ArrayList<>(); //中间结果栈

        //遍历ls
        for(String item : ls){
            //如果是一个数，加入到s2；
            if (item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //如果是右括号')' 则一次弹出s1栈顶的运算符，并压入s2 直到遇到左括号位置,然后将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop(); //将 ( 弹出s1栈 消除小括号
            }else {
                //当item小于等于s1栈顶运算符的优先级的时候 将s1栈顶的运算符弹出 加入到s2中
                while (s1.size() != 0 && Operation2.getValue(s1.peek()) >= Operation2.getValue(item)){
                    s2.add(s1.pop());
                }
                //将item压入栈顶
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次压入s2
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2; //因为是存放到一个list中，因此按顺序输出就是对应的逆波兰表达式
    }
}

//编写一个类 用于比较运算符的优先级
class Operation2{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    //写一个方法 返回对应的优先级
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不能存在该运算符");
                break;
        }

        return result;
    }

}