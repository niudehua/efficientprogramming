package cn.niudehua.efficientprogramming.threadpool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author deng
 * @datetime 2020/11/9 11:29 下午
 */
public class RunTest {
    @Test
    public void submitTest() throws ExecutionException, InterruptedException {
        // 创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 利用submit方法提交任务，接收任务的返回结果
        Future<Integer> future = executorService.submit(() -> {
            Thread.sleep(1000L * 10);
            return 2 * 5;
        });

        // 阻塞方法，知道任务有返回值后，才向下执行
        Integer num = future.get();
        System.out.println("打印结果：" + num);
        Thread.sleep(1000L*100);
    }

    @Test
    public void executeTest() throws InterruptedException {
        // 创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 利用execute方法提交任务，没有返回结果
        executorService.execute(() -> {
            try {
                Thread.sleep(1000L * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer num = 2 * 5;
            System.out.println("执行结果：" + num);
        });
        Thread.sleep(1000L * 1000);
    }
}
