package manning.chap01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by jbwang0106 on 2017/6/27.
 */
public class FilteringApples {

    public static void main(String [] args) {

        //创建一个苹果的集合列表
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),new Apple(155,"green"),new Apple(120,"red"));
        List<Apple> apples = filterGreenApples(inventory);
        System.out.println(apples);
        System.out.println(filterHeavyApples(inventory));

        //行为参数化
        List<Apple> apples1 = filterApple(inventory, FilteringApples::isGreenApple);
        System.out.println(apples1);

        List<Apple> apples2 = filterApple(inventory, FilteringApples::isHeavyApple);
        System.out.println(apples2);

        //lambda表达式的写法
        List<Apple> apples3 = filterApple(inventory, (Apple a) -> a.getWeight() > 150);
        System.out.println(apples3);
        List<Apple> apples4 = filterApple(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println(apples4);
    }

    /**
     * 选择green的苹果方法
     * @param inventory
     * @return
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<Apple>();

        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor()))
                result.add(apple);
        }
        return result;
    }

    /**
     * 选择重量大于150的苹果
     * @param inventory
     * @return
     */
    public static List<Apple> filterHeavyApples(List<Apple> inventory) {

        List<Apple> result = new ArrayList<Apple>();

        for (Apple apple : inventory) {
            if (apple.getWeight() > 150)
                result.add(apple);
        }

        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApple(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(p.test(apple))
                result.add(apple);
        }
        return result;
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color){
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
