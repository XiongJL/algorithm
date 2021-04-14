package lucky.xiong.实际工作问题;

/**
 * 经常出现！=null判断
 *
 * @author XiongJl
 * @date 2021/4/14 15:40
 */
public class 减少非空判断 {
   public static void main(String[] args) {
       Parser parser = new MyParser();
       // 就不需要判断非空，直接调用方法。
       parser.findAction(null).doSomething();
   }
}

interface Action{
    void doSomething();
}
interface Parser{
    Action findAction(String userInput);
}

class MyParser implements Parser{
    private static Action DO_NOTHING = new Action() {
        @Override
        public void doSomething() {
            /**
             * do nothing
             */
            System.out.println("没找到指定方法");
        }
    };

    @Override
    public Action findAction(String userInput) {
        if (userInput == null){
            return DO_NOTHING;
        }else if("deal".equals(userInput)){
            return new Action() {
                @Override
                public void doSomething() {
                    System.out.println("处理问题1");
                }
            };
        }
        return null;
    }
}