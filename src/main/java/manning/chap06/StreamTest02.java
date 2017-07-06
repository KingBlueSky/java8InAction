package manning.chap06;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static manning.chap06.Dish.*;
/**
 * 分组
 * Created by jbwang0106 on 2017/7/5.
 */
public class StreamTest02 {

    public static void main(String [] args) {

        //1. 分组
        Map<Type, List<Dish>> collect = menu.stream()
                .collect(groupingBy(Dish::getType));
        System.out.println(collect);
        System.out.println("-------------------");

        Map<String, List<Dish>> collect1 = menu.stream()
                .collect(groupingBy(dish -> {
                    if (dish.getCalories() <= 400)
                        return "DIET";
                    else if (dish.getCalories() <= 700)
                        return "NOMAL";
                    else
                        return "FAT";
                }));
        System.out.println(collect1);
        System.out.println("-------------------");

        //2. 多级分组
        Map<Type, Map<String, List<Dish>>> collect2 = menu.stream()
                .collect(groupingBy(Dish::getType, groupingBy(dish -> {
                    if (dish.getCalories() <= 400)
                        return "DIET";
                    else if (dish.getCalories() <= 700)
                        return "NOMAL";
                    else
                        return "FAT";
                })));
        System.out.println(collect2);
    }
}
