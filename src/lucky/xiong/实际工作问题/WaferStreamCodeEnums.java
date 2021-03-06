package lucky.xiong.实际工作问题;

/**
 * 用于管理Wafer流水码的枚举类工具。
 * @author XiongJl
 * @date 2021/1/22 13:17
 */
public enum WaferStreamCodeEnums {


    THREE("3", 0),

    FOUR("4", 1),

    FIVE("5",2),
    SIX("6",3),
    SEVEN("7",4),
    EIGHT("8",5),
    NINE("9",6),
    A("A",7),
    B("B",8),
    C("C",9),
    D("D",10),
    E("E",11),
    F("F",12),
    G("G",13),
    H("H",14),
    J("J",15),
    K("K",16),
    L("L",17),
    M("M",18),
    N("N",19),
    P("P",20),
    Q("Q",21),
    R("R",22),
    S("S",23),
    T("T",24),
    U("U",25),
    V("V",26),
    W("W",27),
    X("X",28),
    Y("Y",29)


;
    private final String code;
    private final int value;


    WaferStreamCodeEnums(String code, int value) {
        this.value = value;
        this.code = code;
    }




    /**
     *
     * 获取枚举对应的代码值。
     * 如果未知具体枚举类，可以使用如下代码获取枚举
     * <p>
     *     <br></br>
     * WaferStreamCodeEnums B = WaferStreamCodeEnums.valueOf("B");
     * <br></br>
     * B.getCode();
     * </p>
     * @return 返回对应的流水字符串代码
     */
    public String getCode(){
        return code;
    }
    public int getValue(){
        return value;
    }

    /**
     * 通过代码获取枚举
     * @param code 传入的字符串代码
     * @return 若无法匹配则返回null ， 若匹配，返回WaferStreamCodeEnums类对象
     */
    public static WaferStreamCodeEnums getEnumByCode(String code){
        for(WaferStreamCodeEnums c : WaferStreamCodeEnums.values()){
            if (c.code.equals(code)){
                return c;
            }
        }
        return null;
    }

    /**
     * 通过值获取枚举
     * @param value 传入的代码代表的数字值
     * @return 若无法匹配则返回null ， 若匹配，返回WaferStreamCodeEnums类对象
     */
    public static WaferStreamCodeEnums getEnumByValue(int value){
        for(WaferStreamCodeEnums c : WaferStreamCodeEnums.values()){
            if (c.value==value){
                return c;
            }
        }
        return null;
    }
}
