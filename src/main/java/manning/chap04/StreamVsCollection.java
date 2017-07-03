package manning.chap04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by jbwang0106 on 2017/7/3.
 */
public class StreamVsCollection {
    
    public static void main(String [] args) {

        //当只能消费一次，在次消费会报错
        List<String> list = Arrays.asList("java8", "in", "lambda", "action");
        Stream<String> stream = list.stream();
        stream.forEach(s -> System.out.println(s));
        //java.lang.IllegalStateException: stream has already been operated upon or closed
        stream.forEach(System.out::println);

    }

}
