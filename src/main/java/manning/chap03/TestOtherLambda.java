package manning.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by jbwang0106 on 2017/6/29.
 */
public class TestOtherLambda {

    static Map<String, Function<Integer, Apple>> map2 = new HashMap<>();

    static {

        map2.put("apple", Apple::new);
        map2.put("apple1", Apple::new);
    }

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

        //lambda表达式使用局部变量时，局部变量隐式的是final类型的
        int portNumber = 12345;
        Runnable r = () -> System.out.println(portNumber);
        //portNumber = 15432;

        //使用方法引用进行排序
        List<String> strs = Arrays.asList("a", "b", "A", "B");
        strs.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        strs.sort(String::compareToIgnoreCase);

        //使用方法引用方式创建对象
        Supplier<Apple> s = Apple::new;
        Apple apple = s.get();
        System.out.println(apple);

        Function<Integer, Apple> f = Apple::new;
        System.out.println(f.apply(120));

        BiFunction<Integer, String, Apple> b = Apple::new;
        Apple apple1 = b.apply(140, "green");
        System.out.println(apple1);

        List<Integer> map1 = map(Arrays.asList("lambda", "in", "action"), String::length);
        System.out.println(map1);

        Apple apple2 = map2.get("apple").apply(122);
        System.out.println(apple2);
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
