package manning.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jbwang0106 on 2017/6/29.
 */
public class Lambdas {
    
    public static void main(String [] args) {

        //简单的lambda表达式
        Runnable r = () -> System.out.println("hello world");
        r.run();

        List<Apple> inventory = Arrays.asList(new Apple(155, "green"), new Apple(120, "red"), new Apple(88, "green"));

        List<Apple> apples = filter(inventory, (a) -> "green".equals(a.getColor()));
        System.out.println(apples);

        List<Apple> apples1 = filter(inventory, a -> a.getWeight() > 150);
        System.out.println(apples1);

        List<Apple> apples2 = filter(inventory, a -> a.getWeight() > 150 && "green".equals(a.getColor()));
        System.out.println(apples2);

        inventory.sort((a1,a2) -> a1.getWeight().compareTo( a2.getWeight()));
        System.out.println(inventory);
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : inventory) {
            if(p.test(a)) {
                result.add(a);
            }
        }
        return result;
    }

    @FunctionalInterface
    interface ApplePredicate {

        boolean test (Apple a);
    }
    
}
