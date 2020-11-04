package cn.niudehua.efficientprogramming.stream.cases;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 在股票中，撮合交易的原则是一段时间内的交易申请，价格越高的先成交；价格一样，下单时间最早的先成交；价格和时间一致，交易量大的先成交；如果价格、时间和交易量都一致，机构优先成交，散户最后成交。
 * 现在有一批交易申请数据，需要确认交易先后顺序。
 *
 * @author deng
 * @datetime 2020/11/5 12:42 上午
 */
public class CaseFive {

    // 交易实体模型
    @Data
    @AllArgsConstructor
    class Trade {
        /**
         * 下单价格
         */
        private BigDecimal price;
        /**
         * 下单时间
         */
        private LocalDateTime time;
        /**
         * 下单量
         */
        private Integer counter;
        /**
         * 下单类型：机构/个人
         */
        private String type;
    }

    /**
     * 一段时间内的交易申请
     */
    List<Trade> trades;

    @Before
    public void init() {
        trades = Lists.newArrayList(
                new Trade(new BigDecimal(100), LocalDateTime.now().plusSeconds(1), 500, "机构"),
                new Trade(new BigDecimal(101), LocalDateTime.now().plusSeconds(2), 1, "个人"),
                new Trade(new BigDecimal(101), LocalDateTime.now().plusSeconds(1), 1, "个人"),
                new Trade(new BigDecimal(100), LocalDateTime.now().plusSeconds(1), 500, "个人"),
                new Trade(new BigDecimal(100), LocalDateTime.now().plusSeconds(0), 2, "个人"),
                new Trade(new BigDecimal(100), LocalDateTime.now().plusSeconds(0), 100, "机构")
        );
    }

    @Test
    public void sortTrade() {
        System.out.println(JSON.toJSONString(trades, true));
        List<Trade> collect = trades.stream()
                .sorted(Comparator
                        // 按价格排序
                        .comparing(Trade::getPrice, Comparator.reverseOrder())
                        // 按时间先后排序
                        .thenComparing(Trade::getTime)
                        // 按交易量排序，自然排序翻转
                        .thenComparing(Trade::getCounter, Comparator.reverseOrder())
                        // 自定义排序规则
                        .thenComparing(Trade::getType, (type1, type2) -> {
                            if ("机构".equals(type1) && "个人".equals(type2)) {
                                return -1;
                            } else if ("个人".equals(type1) && "机构".equals(type2)) {
                                return 1;
                            } else {
                                return 0;
                            }
                        }))
                .collect(Collectors.toList());

        System.out.println(JSON.toJSONString(collect, true));
    }
}

