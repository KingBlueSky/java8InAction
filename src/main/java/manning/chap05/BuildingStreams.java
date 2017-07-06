package manning.chap05;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 除了从集合外，其他创建stream对象的方式
 * Created by jbwang0106 on 2017/7/4.
 */
public class BuildingStreams {
    
    public static void main(String [] args) throws IOException {
        
        // 1. 由值创建流
        Stream<String> stream = Stream.of("java8", "in", "lambda", "action");
        stream.map(String::toUpperCase).forEach(s -> System.out.println(s));
        System.out.println("------------------------------");
        Stream<Object> empty = Stream.empty();
        System.out.println(empty.count());
        System.out.println("------------------------------");

        //2. 由数组创建流
        int [] numbers = {2, 4, 6, 8, 10};
        Arrays.stream(numbers).map(a -> a * a)
                .boxed()
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("------------------------------");

        //3. 由文件生成流
        int uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("D:\\jbwang\\IdeaProjects\\java8InAction\\src\\main\\java\\manning\\chap05\\data.txt"), Charset.defaultCharset())) {
            long count = lines.flatMap(line -> Arrays.stream(line.split("")))
                    .distinct()
                    .count();
            //Stream<Stream<String>> streamStream = lines.map(line -> Arrays.stream(line.split("")));
           // Stream<String> stringStream = lines.flatMap(line -> Arrays.stream(line.split("")));
            System.out.println(count);
        }
        System.out.println("------------------------------");

        //4. 由函数生成流：创建无限流
        //4.1 迭代
        Stream.iterate(0 ,n -> n + 2)
                .limit(10)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("------------------------------");
        
        //斐波纳契元组序列
        Stream.iterate(new int[]{0, 1}, t -> new int[] {t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.print("[" + t[0] + "," + t[1] + "]"));
        System.out.println("------------------------------");

        Stream.iterate(new int[]{0, 1}, t -> new int[] {t[1], t[0] + t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(t -> System.out.print(t + ","));
        System.out.println();
        System.out.println("------------------------------");

        //4.2 生成
        Stream.generate(Math::random)
                .limit(5)
                .forEach(s -> System.out.println(s));
        System.out.println("------------------------------");

        //计算斐波那契数组的值
        IntSupplier fie = new IntSupplier() {

            private int prev = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldValue = this.prev;
                int nextValue = this.prev + this.current;
                this.prev = this.current;
                this.current = nextValue;
                return oldValue;
            }
        };

        IntStream.generate(fie).limit(10).forEach(System.out::println);
        System.out.println("------------------------------");


    }
    
}
