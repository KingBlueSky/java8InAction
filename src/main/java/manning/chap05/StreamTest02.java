package manning.chap05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * stream的相关操作02
 * Created by jbwang0106 on 2017/7/4.
 */
public class StreamTest02 {
    
    public static void main(String [] args) {
        
        //检查谓词是否至少匹配一个元素 anyMatch
        if(Dish.menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        //检查谓词是否匹配所有元素 allMatch
        if(Dish.menu.stream().allMatch(dish -> dish.getCalories() < 1000))
            System.out.println("all dish's calories less 1000");

        //流中没有任何元素与给定的谓词匹配 noneMatch
        boolean noneMatch = Dish.menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);
        System.out.println(noneMatch);
        
        //findAny方法将返回当前流中满足条件的任意元素的任意一个
        Optional<Dish> dish = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        
        if (dish.isPresent()) {
            System.out.println(dish.get());
        }

        //查找第一个元素,给定一个数字列表，下面的代码能找出第一个平方能被3整除的数
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);
        Optional<Integer> first = integerList.stream()
                .map(x -> x * x)
                .filter(y -> y % 3 == 0)
                .findAny();
        //.findFirst();
        
        first.ifPresent(x -> System.out.println(x));

        //列表中的数字求和和求积操作reduce()
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        //求和
        Integer sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        //流中么有元素，可能为空
        Optional<Integer> reduce = numbers.stream()
                .reduce((a, b) -> a * b);

        Integer sum1 = numbers.stream()
                .reduce(0, Integer::sum);

        //求积
        Integer product = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println(product);

        //最大值和最小值 reduce()
        Optional<Integer> maxNum = numbers.stream()
                .reduce(Integer::max);
        maxNum.ifPresent(s -> System.out.println(s));

        Optional<Integer> minNum = numbers.stream()
                .reduce((x, y) -> x < y ? x : y);
        minNum.ifPresent(s -> System.out.println(s));
        
        //用map和reduce方法数一数流中有多少个菜
        Integer numCount = Dish.menu.stream()
                .map(num -> 1)
                .reduce(0, Integer::sum);
        System.out.println(numCount);
        long count = Dish.menu.stream().count();
        System.out.println(count);


    }
}
