package manning.chap03;

import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * lambda表达式的复合使用
 * Created by jbwang0106 on 2017/6/30.
 */
public class LambdaRecombination {
    
    public static void main(String [] args) {
        
        //将apple排序完后再逆序
        List<Apple> inventory = Arrays.asList(new Apple(120, "red"), new Apple(120, "green"),new Apple(80, "green"),new Apple(160, "green"));
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println(inventory);
        
        //按照重量排序反转后再按照颜色排序
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
        System.out.println(inventory);
        
        //找出不是红色的苹果
        Predicate<Apple> redApple = apple -> "red".equals(apple.getColor());
        Predicate<Apple> noRedApple = redApple.negate();
        System.out.println(noRedApple.test(new Apple(120,"green")));

        //即是红苹果，又是重量大于150的
        Predicate<Apple> andApple = redApple.and(apple -> apple.getWeight() > 150);
        System.out.println(andApple.test(new Apple(151, "red")));

        //如果是红苹果，那么必须大于150，或者是绿苹果
        Predicate<Apple> or = redApple.and(a -> a.getWeight() > 150).or(a -> "green".equals(a.getColor()));
        System.out.println(or.test(new Apple(120,"red")));
        System.out.println(or.test(new Apple(120,"green")));

        //function的函数复合使用
        //1. 先加后乘
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> andThen = f.andThen(g);
        System.out.println(andThen.apply(1));
        System.out.println(andThen.apply(2));
        System.out.println(andThen.apply(3));

        //2. 先乘后加
        Function<Integer, Integer> compose = f.compose(g);
        System.out.println(andThen.apply(1));
        System.out.println(andThen.apply(2));
        System.out.println(andThen.apply(3));

    }
}
