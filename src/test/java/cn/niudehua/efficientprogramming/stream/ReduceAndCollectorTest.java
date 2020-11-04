package cn.niudehua.efficientprogramming.stream;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 归约与汇总操作
 *
 * @author deng
 * @datetime 2020/11/3 12:41 上午
 */
public class ReduceAndCollectorTest {

    @Test
    public void reduceTest() {

        // 订单对象
        @Data
        @AllArgsConstructor
        class Order {
            /**
             * 订单编号
             */
            private Integer id;
            /**
             * 商品数量
             */
            private Integer productCount;
            /**
             * 消费总金额
             */
            private Double totalAmount;

        }

        // 准备数据
        List<Order> list = new ArrayList<>();
        list.add(new Order(1, 2, 25.12));
        list.add(new Order(2, 5, 257.23));
        list.add(new Order(3, 3, 23332.12));

        // 1.计算商品数量
        // 2.计算消费总金额
        Order order = list.stream()
                .parallel()
                .reduce(
                        // 初始值
                        new Order(0, 0, 0.0),
                        // Stream中两元素的计算逻辑
                        (order1, order2) -> {
                            System.out.println("执行计算逻辑方法！！！！");
                            int productCount = order1.getProductCount() + order2.getProductCount();
                            double totalAmount = order1.getTotalAmount() + order2.getTotalAmount();
                            return new Order(0, productCount, totalAmount);
                        },
                        // 并行情况下，多个并行结果如何合并
                        (order1, order2) -> {
                            System.out.println("执行合并方法！！！！");
                            int productCount = order1.getProductCount() + order2.getProductCount();
                            double totalAmount = order1.getTotalAmount() + order2.getTotalAmount();
                            return new Order(0, productCount, totalAmount);
                        });
        System.out.println(JSON.toJSONString(order, true));
    }

    @Test
    public void collectTest() {
        // 订单对象
        @Data
        @AllArgsConstructor
        class Order {
            /**
             * 订单编号
             */
            private Integer id;
            /**
             * 用户账号
             */
            private String account;
            /**
             * 商品数量
             */
            private Integer productCount;
            /**
             * 消费总金额
             */
            private Double totalAmount;

            public Order() {

            }
        }

        // 准备数据
        List<Order> list = new ArrayList<>();
        list.add(new Order(1, "zhangsan", 2, 25.12));
        list.add(new Order(2, "zhangsan", 5, 257.23));
        list.add(new Order(3, "wangba", 3, 23332.12));

        // Map<用户账号，订单(数量和金额)>
        HashMap<String, Order> collect = list.stream()
                .parallel()
                .collect(
                        // 执行初始化容器
                        () -> new HashMap<>(2),
                        // 执行新元素添加到容器
                        (map, order) -> {
                            System.out.println("执行新元素添加到容器");
                            map.merge(order.getAccount(), order, (order12, order2) -> {
                                order12.setTotalAmount(order12.getTotalAmount() + order2.getTotalAmount());
                                order12.setProductCount(order12.getProductCount() + order2.getProductCount());
                                return order12;
                            });
                        }, (map, map2) -> {
                            System.out.println("执行并行结果合并操作");
                            map2.forEach((s, order) -> map.merge(s, order, (order1, order2) -> {
                                order1.setTotalAmount(order1.getTotalAmount() + order2.getTotalAmount());
                                order1.setProductCount(order1.getProductCount() + order2.getProductCount());
                                return order1;
                            }));
                        });

        System.out.println(JSON.toJSONString(collect, true));
    }


}

