package cn.niudehua.efficientprogramming.stream;

import cn.niudehua.efficientprogramming.lambda.cart.CartService;
import cn.niudehua.efficientprogramming.lambda.cart.Sku;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 常见预定义收集器使用
 *
 * @author deng
 * @datetime 2020/11/3 12:25 上午
 */
public class StreamCollector {
    @Test
    public void toList() {
        List<Sku> list = CartService.getCartSkuList();
        List<Sku> collect = list.stream()
                .filter(sku -> sku.getTotalPrice() > 100)
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect, true));
    }

    @Test
    public void group() {
        List<Sku> list = CartService.getCartSkuList();
        // map<分组条件，结果集合>
        Map<Enum, List<Sku>> collect = list.stream()
                .collect(Collectors.groupingBy(Sku::getSkuCategory));
        System.out.println(JSON.toJSONString(collect, true));
    }

    @Test
    public void partition() {
        List<Sku> list = CartService.getCartSkuList();
        Map<Boolean, List<Sku>> collect = list.stream()
                .collect(Collectors.partitioningBy(sku -> sku.getTotalPrice() > 100));
        System.out.println(JSON.toJSONString(collect, true));
    }
}
