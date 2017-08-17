package manning.chap07;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by jbwang0106 on 2017/8/16.
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 2; //阈值
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        //如果任务足够小就执行执行
        boolean canCompute = (end - start) <= THRESHOLD;

        if (canCompute) {
            for (int i = start; i <= end; i ++) {
                sum += i;
            }
        } else {

            //进行任务的分割
            int middle = (end + start) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);

            //执行子任务
            leftTask.fork();
            rightTask.fork();

            //等待执行结果
            Integer leftResult = leftTask.join();
            Integer rightResult = rightTask.join();

            //合并子任务
            sum += leftResult + rightResult;

        }

        return sum;
    }

    public static void main(String [] args) {

        //生成计算任务
        CountTask countTask = new CountTask(1, 4);

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //执行计算任务
        ForkJoinTask<Integer> submit = forkJoinPool.submit(countTask);

        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
