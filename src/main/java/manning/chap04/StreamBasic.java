package manning.chap04;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jbwang0106 on 2017/7/3.
 */
public class StreamBasic {
    
    public static void main(String [] args) {
        
        //java7查找小热量食物的名称
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        //java8使用流查找小热量食物的名称
        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> menu) {

        //1. 筛选树低热量的菜单
        List<Dish> lowCaloricList = new ArrayList<>();

        for (Dish dish:menu
             ) {
            if (dish.getCalories() < 400)
                lowCaloricList.add(dish);
        }

        //2.根据热量排序
        lowCaloricList.sort(new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        //3. 菜单名称
        List<String> names = new ArrayList<>();

        for (Dish dish: lowCaloricList
             ) {
            names.add(dish.getName());
        }

        return names;
    }

    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> menu) {

        List<String> names = menu.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());

        return names;
    }

}
