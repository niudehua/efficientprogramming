package cn.niudehua.efficientprogramming.threadpool;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author deng
 * @datetime 2020/11/8 11:10 下午
 */
public class ThreadVs {
    @Test
    public void newHandle() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int request = 1; request < 100; request++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("文档处理开始:" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000L * 3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("文档处理结束:" + Thread.currentThread().getName()+"-----------");
                }
            });
        }
        Thread.sleep(1000L * 60);
    }

    @Test
    public void oldHandle() throws InterruptedException {
        for (int request = 1; request < 100; request++) {
            new Thread(() -> {
                System.out.println("文档处理开始:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L * 30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("文档处理结束:" + Thread.currentThread().getName());
            }).start();
        }
        Thread.sleep(1000L * 1000);
    }
}
