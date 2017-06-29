package manning.chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jbwang0106 on 2017/6/27.
 */
public class FilteringApples {

    public static void main(String [] args) {

        List<Apple> inventory = Arrays.asList(new Apple(88,"green"), new Apple(155,"green"), new Apple(120,"red"));

        List<Apple> apples = filterGreenApples(inventory);
        System.out.println(apples);
        List<Apple> apples1 = filterApplesByColor(inventory, "red");
        System.out.println(apples1);

        List<Apple> apples2 = filterApplesByWeight(inventory, 100);
        System.out.println(apples2);

        List<Apple> apples3 = filterApples(inventory, new AppleWeightAndColorPredicate());
        System.out.println(apples3);


    }

    public static List<Apple> filterGreenApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory){
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color){
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory){
            if(apple.getColor().equals(color)){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight){
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory){
            if(apple.getWeight() > weight){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (p.test(apple))
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

    interface ApplePredicate{
        public boolean test(Apple a);
    }

    static class AppleWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple a) {
            return a.getWeight() > 150;
        }
    }

    static class AppleColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple a) {
            return "green".equals(a.getColor());
        }
    }

    static class AppleWeightAndColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple a) {
            return "green".equals(a.getColor()) && a.getWeight() >150;
        }
    }
}
