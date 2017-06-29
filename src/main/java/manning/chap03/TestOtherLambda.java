package manning.chap03;

/**
 * Created by jbwang0106 on 2017/6/29.
 */
public class TestOtherLambda {

    public static void main(String [] args) {

        String str = "helloworld";

        Integer filter = filter(str, s -> s.length());
        System.out.println(filter);

        Thread thread = new Thread(() -> System.out.println("gogle"));
        thread.start();
        
        process(() -> System.out.println("this is awesome"));
    }

    public static Integer filter(String s, Test t){
        return t.test(s);
    }

    @FunctionalInterface
    interface Test {
        Integer test(String s);
    }

    public static void process(Runnable r) {
        r.run();
    }
}
