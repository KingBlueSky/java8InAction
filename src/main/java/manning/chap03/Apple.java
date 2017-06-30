package manning.chap03;

/**
 * Created by jbwang0106 on 2017/6/29.
 */
public class Apple {
    private int weight = 0;
    private String color = "";

    public Apple() {
        super();
    }

    public Apple(Integer weight) {
        this.weight = weight;
    }

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
