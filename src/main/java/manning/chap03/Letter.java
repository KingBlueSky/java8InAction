package manning.chap03;

import java.util.function.DoubleFunction;
import java.util.function.Function;

/**
 * Created by jbwang0106 on 2017/6/30.
 */
public class Letter {

    /**
     * 添加消息头
     * @param text
     * @return
     */
    public static String addHeader(String text) {
        return "From jbwang, Alan, Bob " + text;
    }

    /**
     * 添加消息尾部
     * @param text
     * @return
     */
    public static String addFooter(String text) {
        return text + " Kind regards";
    }

    public static String CheckSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }
    
    public static void main(String [] args) {

        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> checkSpell = addHeader.andThen(Letter::CheckSpelling).andThen(Letter::addFooter);
        String apply = checkSpell.apply("labda,gogle");
        System.out.println(apply);

        double integrate = integrate(d -> d + 10, 3, 7);
        System.out.println(integrate);

    }

    public static double integrate(DoubleFunction<Double> f, double x, double y) {
        return (f.apply(x) + f.apply(y)) * (y - x) / 2;
    }
}
