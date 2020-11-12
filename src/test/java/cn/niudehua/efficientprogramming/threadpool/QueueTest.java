package cn.niudehua.efficientprogramming.threadpool;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author deng
 * @datetime 2020/11/9 10:58 下午
 */
public class QueueTest {
    @Test
    public void arrayBlockingQueue() throws InterruptedException {
        /**
         * 有界队列，队列容量为10 基于数组
         */
        ArrayBlockingQueue queue = new ArrayBlockingQueue<Integer>(10);
        for (int i = 0; i < 20; i++) {
            queue.put(i);
            System.out.println("向队列中添加值：" + i);
        }
    }

    @Test
    public void linkedBlockingQueue() throws InterruptedException {
        // 有界队列/无界，队列容量为10 基于链表
//        LinkedBlockingQueue queue = new LinkedBlockingQueue<>(10);
        LinkedBlockingQueue queue = new LinkedBlockingQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.put(i);
            System.out.println("向队列中添加值：" + i);
        }
    }

    @Test
    public void synchronousQueue() throws InterruptedException {
        // 同步移交阻塞队列
        SynchronousQueue queue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                queue.put(1);
                System.out.println("插入成功");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000L * 60);

        new Thread(() -> {
            try {
                queue.take();
                System.out.println("删除成功");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
