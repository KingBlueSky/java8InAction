package manning.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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
        
        List<Integer> list = Arrays.asList(12,14,2,12,42);
        
        forEach(list,(integer -> System.out.println(integer)));

        List<Integer> map = map(Arrays.asList("lambda", "in", "action"), (t) -> t.length());
        System.out.println(map);
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

    /**
     * 使用consum对象实现foreach方法
     * @param list
     * @param c
     * @param <T>
     */
    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {

        List<R> result = new ArrayList<R>();

        for (T t : list) {
            result.add(f.apply(t));
        }

        return result;
    }
}
