package lucky.xiong.stack.calculator;

/**
 * @author XiongJl
 * @date 2020/8/17 10:12
 */
public enum Operation {
    /**
     * 加法 ，权重为0
     */
    ADD("+",0),
    /**
     * 减法，权重为0
     */
    SUBTRACTION("-",0),
    /**
     * 乘法
     */
    MULTIPLICATION("*",1),
    /**
     * 除法
     */
    DIVISION("/",1),
    /**
     * 左括号
     */
    LEFT("(",0),
    /**
     * 右括号
     */
    RIGHT(")",0);

    public String op;
    public int weight;
    Operation(String op,int weight){this.op = op;this.weight = weight;}
    Operation(){}
    public String getOp(){return op;}
    public float calculation(float a ,float b , Operation operation){
        switch (operation.op){
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            case "/":
                return a/b;
        }
        return -1;
    }
}
