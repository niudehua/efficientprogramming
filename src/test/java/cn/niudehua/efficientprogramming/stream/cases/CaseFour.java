package cn.niudehua.efficientprogramming.stream.cases;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 设计一个对外提供服务的接口，支持调用方传入多个账户编号查询订单
 *
 * @author deng
 * @datetime 2020/11/5 12:07 上午
 */
public class CaseFour {
    @Data
    @AllArgsConstructor
    class Order {
        /**
         * 订单编号
         */
        private Integer orderId;
        /**
         * 账户编号
         */
        private String accountId;
    }

    /**
     * 模拟数据库查询
     *
     * @param accountIds
     * @return
     */
    public List<Order> selectFromDB(List<String> accountIds) {
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orderList.add(new Order(i, accountIds.get(i % accountIds.size())));
        }
        return orderList;
    }

    public Map<String, List<Order>> queryOrderByAccountIds(List<String> accountIds) {
        return Optional.ofNullable(selectFromDB(accountIds))
                .map(List::stream)
                .orElseGet(Stream::empty)
                .collect(Collectors.groupingBy(Order::getAccountId));
    }
}
