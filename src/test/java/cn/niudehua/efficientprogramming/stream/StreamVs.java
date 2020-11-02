package cn.niudehua.efficientprogramming.stream;

import cn.niudehua.efficientprogramming.lambda.cart.CartService;
import cn.niudehua.efficientprogramming.lambda.cart.Sku;
import cn.niudehua.efficientprogramming.lambda.cart.SkuCategoryEnum;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 对比:原始集合操作与Stream集合操作
 *
 * @author deng
 * @datetime 2020/10/24 12:35 上午
 */
@Slf4j
public class StreamVs {

//   1.想看看购物车中都有什么商品
//   2.图书类商品都给买
//   3.其余的商品中买两件最贵的
//   4.只需要两件商品的名称和总价

    /**
     * 以原始集合操作实现需求
     */
    @Test
    public void oldCartHandler() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 1.打印所有商品
        for (Sku sku : cartSkuList) {
            System.out.println(sku);
        }

        // 2.图书类过滤掉
        List<Sku> notBookSkuList = new ArrayList<>();
        for (Sku sku : cartSkuList) {
            if (!SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory())) {
                notBookSkuList.add(sku);
            }
        }

        // 3. 排序
        notBookSkuList.sort(new Comparator<Sku>() {
            @Override
            public int compare(Sku o1, Sku o2) {
                return o2.getSkuPrice().compareTo(o1.getTotalPrice());
            }
        });

        // TOP2
        List<Sku> top2SkuList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            top2SkuList.add(notBookSkuList.get(i));
        }

        // 4. 求两件商品的总价
        Double money = 0.0;
        for (Sku sku : top2SkuList) {
            money += sku.getSkuPrice();
        }

        // 获取两件商品的名称
        List<String> resultSkuName = new ArrayList<>();
        for (Sku sku : top2SkuList) {
            resultSkuName.add(sku.getSkuName());
        }

        // 打印输出结果
        System.out.println(JSON.toJSONString(resultSkuName, true));
        System.out.println("商品总价：" + money);
    }

    /**
     * 以Stream流方式实现需求
     */
    @Test
    public void newCartHandle() {
        AtomicReference<Double> money = new AtomicReference<>(0.0d);
        List<String> resultSkuNameList = CartService.getCartSkuList()
                .stream()
                // 1.打印商品信息
                .peek(sku -> {
                    log.info(JSON.toJSONString(sku, true));
                })
                // 2.过滤掉所有图书类商品
                .filter(sku -> !SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                // 排序
                .sorted(Comparator.comparing(Sku::getTotalPrice).reversed())
                // TOP2
                .limit(2L)
                // 累加商品总金额
                .peek(sku -> money.set(money.get() + sku.getTotalPrice()))
                // 获取商品名称
                .map(Sku::getSkuName)
                // 收集结果
                .collect(Collectors.toList());
        log.info("打印商品名称：{}", JSON.toJSONString(resultSkuNameList, true));
        log.info("打印商品的总价：{}", money.get());

    }
}
