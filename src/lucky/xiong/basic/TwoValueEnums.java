package lucky.xiong.basic;

/**
 * @author XiongJl
 * @date 2021/1/23 10:47
 */
public enum TwoValueEnums {
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


    TwoValueEnums(String code, int value) {
        this.value = value;
        this.code = code;
    }

    String getCode(int num){
        if (num>29){
            return null;
        }
        if (num == value){
            return code;
        }
        return null;
    }
}
