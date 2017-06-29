package manning.chap02;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jbwang0106 on 2017/6/27.
 */
public class TestOther {
    
    public static void main(String [] args) {
        //最简单线程的lambda表现形式
        Thread thread = new Thread(() -> System.out.println("hello world"));
        thread.start();

        //自定义排序的lambda表现形式
        List<FilteringApples.Apple> inventory = Arrays.asList(new FilteringApples.Apple(88,"green"), new FilteringApples.Apple(155,"green"), new FilteringApples.Apple(120,"red"));
        System.out.println(inventory);

        inventory.sort((FilteringApples.Apple a1, FilteringApples.Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(inventory);
    }
}
