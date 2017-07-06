package manning.chap06;


/**
 * 静态导入类的方法
 */
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;

import static java.util.stream.Collectors.*;

/**
 * Created by jbwang0106 on 2017/7/5.
 */
public class StreamTest01 {

    public static void main(String [] args) {

        //1. 菜肴的数量
        Long aLong = Dish.menu.stream().collect(counting());
        System.out.println(aLong);
        System.out.println("--------------------------");
        
        //2. 最大热量菜和最小热量菜
        Optional<Dish> maxValue = Dish.menu.stream()
                .collect(maxBy(Comparator.comparing(Dish::getCalories)));

        Optional<Dish> minValue = Dish.menu.stream()
                .collect(minBy(Comparator.comparing(Dish::getCalories)));
        System.out.println(maxValue.get() + ":" + minValue.get());
        System.out.println("--------------------------");
        
        //3. 汇总操作 数量，总和，最大最小值，平均值
        IntSummaryStatistics summaryStatistics = Dish.menu.stream()
                .collect(summarizingInt(Dish::getCalories));
        System.out.println(summaryStatistics);
        System.out.println("--------------------------");

        //4. 连接字符串
        String collect = Dish.menu.stream()
                .map(Dish::getName)
                .collect(joining(", "));
        System.out.println(collect);
        System.out.println("--------------------------");
        
        //5. 广义的归约汇总
        Integer sumValue = Dish.menu.stream()
                .collect(reducing(0, Dish::getCalories, (x, y) -> x + y));
        System.out.println(sumValue);

        Integer sumValues = Dish.menu.stream()
                .collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(sumValues);
        System.out.println("--------------------------");


    }
}
