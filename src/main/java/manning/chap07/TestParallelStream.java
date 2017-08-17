package manning.chap07;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by jbwang0106 on 2017/8/16.
 */
public class TestParallelStream {

    public static void main(String [] args) {

        System.out.println(iterativeSum(2));
        System.out.println(sequentialSum(2));
        System.out.println(parallelSum(2));
        
        System.out.println("-------------");
        System.out.println(measureSumPerf(TestParallelStream::iterativeSum, 10000000) + " msecs");
        System.out.println(measureSumPerf(TestParallelStream::sequentialSum, 10000000) + " msecs");
        System.out.println(measureSumPerf(TestParallelStream::parallelSum, 10000000) + " msecs");
        System.out.println(measureSumPerf(TestParallelStream::parallelRangedSum, 10000000) + " msecs");
        System.out.println(measureSumPerf(TestParallelStream::sideEffectParallelSum, 10000000) + " msecs");
    }

    /**
     * java7的方式求和
     * @param n
     * @return
     */
    public static long iterativeSum(long n) {
        long result = 0;

        for (int i = 1; i <= n; i++) {
            result += i;
        }

        return result;
    }

    /**
     * java8 顺序流
     * @param n
     * @return
     */
    public static long sequentialSum(long n) {

        return Stream.iterate(1l, i -> i + 1)
                .limit(n)
                .reduce(0l, Long ::sum);
    }

    /**
     * java8 并行流
     * @param n
     * @return
     */
    public static long parallelSum(long n) {

        return Stream.iterate(1l, i -> i+1)
                .limit(n)
                .parallel()
                .reduce(0l, Long::sum);
    }

    /**
     * 使用longStream 避免自动拆箱
     * @param n
     * @return
     */
    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    static class Accumulator {
        public long total = 0;
        public void add(long value) { total += value; }
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fasttime = Long.MAX_VALUE;

        for (int i = 0; i < 10; i ++) {
            long start = System.nanoTime();
            Long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fasttime) fasttime = duration;

        }

        return fasttime;
    }

}
