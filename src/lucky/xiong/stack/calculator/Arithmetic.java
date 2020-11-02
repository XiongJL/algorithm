package lucky.xiong.stack.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 利用栈实现四则运算计算器
 *
 * step1. 讲中缀运算方式改成计算机容易处理的后缀运算方式。
 * step2. 计算剑耀九州
 * 1，2步都利用栈进行处理
 * @author XiongJl
 * @date 2020/8/13 14:33
 */
public class Arithmetic {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(arithmetic("9+(3-1)*3+10/2"));
        System.out.println(arithmetic("((10-10)/6+1)/2"));
        System.out.println(arithmetic("50*50*50/((100+25)-100)/2"));
    }

    /**
     *
     * 字符串方程式
     * @param equation
     * @return
     */
    public static float arithmetic(String equation) throws ClassNotFoundException {
        // 去除方式中的空格
        equation = equation.replaceAll(" ","");
        // 转换为后缀
        List<String> suffix = changeToSuffix(equation);
        /*开始计算*/
        /**
         * 对后缀方程式进行计算
         * 例如 9 3 1 - 3 * + 10 2 / +
         * step1. 初始化空栈，用于对要运算的数字进出使用
         * step2. 后缀表达式中前三个都是数字，所以9、3、1进栈
         * step3. 接下来是”-“，所以将栈中的1出站作为简述，3出战作为被减数，并运算3-2得到2，
         *      再将2进栈
         * step4. 数字3进栈
         * step5. ”*“，3和2出站，相乘，得到6，并将6进栈
         * step6. ”+“，6和9出站，相加，得到15，15进栈
         * step7. 10 , 2 进栈
         * step8. ”/“， 栈顶的 2 与 10 出栈，10 与 2 相除，得到5，5进栈
         * step9. ”+“ ， 15 与 5 出栈，相加，得到20，20进栈
         * step10. 无其他操作，出栈 20 为结果
         */
        Stack<Float> stack = new Stack<>();
        for (String str : suffix){
            if (Character.isDigit(str.charAt(0))){
                stack.push(Float.valueOf(str));
            }else {
                // 去除栈顶两个数字进行运算
                float b = stack.pop();
                float a = stack.pop();
                Operation operation = who(str.charAt(0));
                float res = operation.calculation(a,b,operation);
                stack.push(res);
            }
        }
        return stack.pop();
    }

    /**
     *  将中缀改为后缀
     *  例如： 中缀 9+(3-1)* 3+10/2
     *  改为后缀： 9 3 1 -3 * +10 2 / +
     *  栈的处理过程：
     *  1. 初始化栈，用于对符号进出栈使用。
     *  2. 第一个字符是数字9，输出9，后面是符号 "+"，进栈。
     *  3. 第三个字符是 "("，依然是符号，因其只是左括号，还未配对，故进栈。
     *  4. 第四个字符是数字3，输出，总表达式为 9 3，接着是"-"，进栈。
     *  5. 接下来是数字1，输出，总表达式为9 3 1，后面是符号 ")"，此时，我们需要去匹配此前的"("，所以栈顶一次出栈
     *      并输出，直到"("出栈为止。此时左括号上方只有"-"，因此输出"-"。总的表达式为 9 3 1 -
     *  6. 接着是数字3，输出总的表达式为 9 3 1 - 3 。紧接着是符号 "×"，因为此时的栈顶符号位 "+"号，优先级低于
     *      "×" ，因此不输出， " * "进栈。
     *  7. 之后是符号 "+" ,此时当前栈顶元素 "*" 比这个 "+" 的优先级高，因此栈中元素出栈并输出（没有比 "+"号
     *      更低的优先级，所以全部出栈），总输出表达式为 9 3 1 - 3 * +
     *      然后将当前这个符号 "+" 进栈。
     *  8. 紧接着数字 10，输出，总表达式变为 9 3 1 - 3 * + 10 。后面是符号 "÷"，所以 "/" 进栈。
     *  9. 最后一个数字2，输出，总的表达式为 9 3 1 - 3 * + 10 2
     *  10. 因已经到最后，所以将栈中符号全部出栈并输出。最终输出的后缀表达式结果为
     *      9 3 1 - 3 * + 10 2 / +
     * @return
     */
    private static List<String> changeToSuffix(String equation) throws ClassNotFoundException {
        // 初始化栈
        Stack<String> stack = new Stack<>();
        // 初始化返回结果
        StringBuilder sb = new StringBuilder();
        // toCharArray 方法将会把 10 之类的转换为Ascii 中的空格。
//        char[] chars = equation.toCharArray();
        char[] chars = new char[equation.length()];
        for (int i = 0; i< equation.length();i++){
            chars[i] = equation.charAt(i);
        }
        for (int j=0; j<chars.length;j++){
            char c = chars[j];
            // 如过匹配到右括号，一直弹出内容直到匹配到"("
            if (')'==c){
                while(!stack.empty()){
                    String s = stack.pop();
                    if (s.equals("(")){
                        break;
                    }
                    else{
                        sb.append(s).append(" ");
                    }
                }
                continue;
            }
            if ('('==c){
                stack.push(String.valueOf(c));
                continue;
            }
            if (Character.isDigit(c)){
                // 如果下一字符也是数字，置为true
                if (j+1<chars.length && Character.isDigit(chars[j+1])){
                    sb.append(c);
                    continue;
                }
                sb.append(c).append(" ");

            }else {
                // 判断栈是否为空
                if (stack.empty()){
                    stack.push(String.valueOf(c));
                }else {
                    Operation who = who(c);
                    // 判断栈顶元素的权重
                    Operation that = who(stack.peek().charAt(0));
                    if (who.weight < that.weight){
                        // 栈顶元素的权重大于下一个操作符，此时，栈内全部输出,除了"("
                        while(!stack.empty()){
                            String s = stack.peek();
                            if (!s.equals("(")){
                                sb.append(stack.pop()).append(" ");
                            }else {
                                break;
                            }
                        }
                    }
                    // 当前元素进栈
                    stack.push(who.op);
                }
            }

        }

        while(!stack.empty()){
            String s = stack.pop();
            sb.append(s).append(" ");

        }
        String[] s = sb.toString().split(" ");

        return Arrays.asList(s);
    }






    /**
     * 判断字符是否属于某个操作符
     * @param c
     * @return
     */
    public static Operation who(char c) throws ClassNotFoundException {
        String s = String.valueOf(c);
        Class<Operation> clazz = (Class<Operation>) Class.forName("lucky.xiong.stack.calculator.Operation");
        Operation[] enumConstants = clazz.getEnumConstants();
        for (Operation operation : enumConstants){
            if (operation.getOp().equals(s)){
                return operation;
            }
        }

        throw new RuntimeException("方程含有未定义字符串");
    }
}
