package cn.niudehua.efficientprogramming.threadpool;

import org.junit.After;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 饱和策略
 *
 * @author deng
 * @datetime 2020/11/9 11:45 下午
 */
public class PolicyTest {
    // 2个核心线程 3个最大线程，5个任务队列 只有1个线程可用
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(5));

    class Task implements Runnable {
        private String taskName;

        public Task(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println("线程【" + Thread.currentThread().getName() + "】正在执行【" + this.taskName + "】任务。。。");
            try {
                Thread.sleep(1000L * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程【" + Thread.currentThread().getName() + "】已执行完【" + this.taskName + "】任务!!!!!");
        }
    }

    @Test
    public void abortPolicyTest() {
        // 设置饱和策略为 终止策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            try {
                executor.execute(new Task("线程任务" + i));
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        // 关闭线程池
        executor.shutdown();
    }

    @Test
    public void discardPolicyTest()  {
        // 设置饱和策略为 抛弃策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 10; i++) {
            try {
                executor.execute(new Task("线程任务" + i));
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        // 关闭线程池
        executor.shutdown();
    }

    @Test
    public void discardOldPolicyTest()  {
        // 设置饱和策略为 抛弃旧任务策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 10; i++) {
            try {
                executor.execute(new Task("线程任务" + i));
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        // 关闭线程池
        executor.shutdown();
    }

    @Test
    public void callerRunsPolicyTest()  {
        // 设置饱和策略为 调用者运行策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            try {
                executor.execute(new Task("线程任务" + i));
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        // 关闭线程池
        executor.shutdown();
    }

    @After
    public void after() throws InterruptedException {
        Thread.sleep(1000 * 100);
    }
}
