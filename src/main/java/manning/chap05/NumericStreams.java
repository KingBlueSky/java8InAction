package manning.chap05;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 原始类型流特化
 * Created by jbwang0106 on 2017/7/4.
 */
public class NumericStreams {
    
    public static void main(String [] args) {
        
        //1. 对menu中的卡路里求和：
        int sum = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(sum);
        System.out.println("-------------------");

        //2. 转换回对象流
        Stream<Integer> stream = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .boxed();
        System.out.println(stream);
        System.out.println("-------------------");
        
        //3. 默认值OptionalInt
        OptionalInt max = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        int i = max.orElse(1);
        System.out.println(i);
        System.out.println("-------------------");

        //4. 数值范围
        long count = IntStream.rangeClosed(1, 100)
                .filter(s -> s % 2 == 0)
                .count();
        System.out.println(count);
        System.out.println("-------------------");

        //5. 勾股数
        Stream<double[]> stream1 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .mapToObj(
                                        b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                                .filter(t -> t[2] % 1 == 0));
        stream1.forEach(System.out :: println);



    }
}

