package lucky.xiong.多线程;

import java.util.concurrent.*;

/**
 * @author XiongJl
 * @date 2020/12/24 16:38
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) {

        System.out.println(90L*24L*60L*60L*1000L);


        ExecutorService excutor =
                Executors.newCachedThreadPool();

        for (int i = 0; i<3;i++){
            excutor.execute(()->{
                for (int j = 0; j< 500;j++){
                    System.out.println("线程"+excutor.toString()+"： doSomthing"+j);
                }

            });
        }
        excutor.shutdown();
    }
}

