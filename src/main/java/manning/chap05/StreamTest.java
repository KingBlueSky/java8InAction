package manning.chap05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * stream的相关操作01
 * Created by jbwang0106 on 2017/7/3.
 */
public class StreamTest {

    public static void main(String [] args) {

        List<Dish> dishes = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        dishes.forEach(s -> System.out.println(s.getName()));
        System.out.println("-------------------");

        //筛选出所有的偶数并去重打印
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(integer -> integer % 2 == 0)
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("-------------------");

        //选出热量超过300卡路里的头三道菜：
        Dish.menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("-------------------");

        //跳过热量超过300卡路里的前1道菜并返回3道菜
        Dish.menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(1)
                .limit(3)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("-------------------");

        //通过映射返回菜肴的名称
        Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("-------------------");

        //返回单词的长度列表
        Arrays.asList("java8", "in", "lambda", "action")
                .stream()
                .map(String::length)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("-------------------");

        //通过映射返回菜肴名称的长度
        Dish.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("-------------------");

        //1. 给定单词返回字符列表---->返回的是一个个的数组
        Arrays.asList("Hello", "World")
                .stream()
                .map(s -> s.split(""))
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("-------------------");

        //2. 再次尝试，返回两个流对象
        Arrays.asList("Hello", "World")
                .stream()
                .map(s -> s.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("-------------------");
        
        //3.使用flatmap,把多个流映射成一个流的内容
        Arrays.asList("Hello", "World")
                .stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList())
                .forEach(s -> System.out.println(s));
        System.out.println("-------------------");

        //给定数字列表，返回数字平方的列表
        Arrays.asList(1, 2, 3, 4)
                .stream()
                .map(n -> n*n)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("-------------------");

        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);

        List<int[]> list = number1.stream()
                .flatMap(i -> number2
                        .stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
                
        list.forEach(s->{for (int i = 0; i < s.length; i++) {System.out.print(s[i] + ",");}});

        
    }


}
