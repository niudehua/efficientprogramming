package cn.niudehua.efficientprogramming.stream;

import cn.niudehua.efficientprogramming.lambda.cart.CartService;
import cn.niudehua.efficientprogramming.lambda.cart.Sku;
import cn.niudehua.efficientprogramming.lambda.cart.SkuCategoryEnum;
import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.Predicate;

/**
 * 演示流的各种操作
 *
 * @author deng
 * @datetime 2020/10/31 5:36 下午
 */
public class StreamOperator {
    List<Sku> list;

    @Before
    public void init() {
        list = CartService.getCartSkuList();
    }

    /**
     * Returns a stream consisting of the elements of this stream that match
     * the given predicate.
     * 返回由该流中与给定谓词匹配的元素组成的流。
     * This is an intermediate operation.
     * 这是一个中间操作
     */
    @Test
    public void filterTest() {
        list.stream()
                .filter(sku ->
                        SkuCategoryEnum.BOOKS
                                .equals(sku.getSkuCategory()))

                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)));
    }

    /**
     * Returns a stream consisting of the results of applying the given
     * function to the elements of this stream.
     * 返回一个流，该流包括将给定函数应用于此流的元素的结果。
     * This is an intermediate operation.
     * 这是一个中间操作
     */
    @Test
    public void mapTest() {
        list.stream()

                .map(Sku::getSkuName)

                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)));
    }

    /**
     * 将一个对象转换成流
     */
    @Test
    public void flatMapTest() {
        list.stream()
                .flatMap(sku -> Arrays.stream(sku.getSkuName().split("")))
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)));
    }

    /**
     * peek：对流中元素进行遍历操作，与forEach类似但不会销毁流 是中间操作
     */
    @Test
    public void peekTest() {
        list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)));
    }

    /**
     * sorted:对流中元素进行排序，可选择自然排序或指定排序规则 有状态的操作
     */
    @Test
    public void sortedTest() {
        list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .sorted(Comparator.comparing(Sku::getSkuPrice))
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)));
    }

    /**
     * distinct：对流中元素进行去重 是个有状态操作
     */
    @Test
    public void distinctTest() {
        list.stream()
                .map(Sku::getSkuCategory)
                .distinct()
                .forEach(e -> System.out.println(JSON.toJSONString(e, true)));
    }

    /**
     * skip：跳过前n条记录 是有状态操作
     */
    @Test
    public void skipTest() {
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice).reversed())
                .skip(3L)
                .forEach(e -> System.out.println(JSON.toJSONString(e, true)));
    }

    /**
     * limit:截断前n条记录 是有状态操作
     */
    @Test
    public void limitTest() {
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .skip(3L)
                .limit(3L)
                .forEach(e -> System.out.println(JSON.toJSONString(e, true)));
    }

    /**
     * allMatch:终端操作，断路操作 所有元素匹配 返回true
     */
    @Test
    public void allMatchTest() {
        boolean b = list.stream()

                .peek(new Consumer<Sku>() {
                    @Override
                    public void accept(Sku sku) {
                        System.out.println(JSON.toJSONString(sku, true));
                    }
                })
                .allMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(b);
    }

    /**
     * anyMatch:终端操作 断路操作 任一元素匹配返回true
     */
    @Test
    public void anyMatchTest() {
        boolean b = list.stream()
                .peek(new Consumer<Sku>() {
                    @Override
                    public void accept(Sku sku) {
                        System.out.println(JSON.toJSONString(sku, true));
                    }
                })
                .anyMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(b);
    }

    /**
     * noneMatch:所有元素都不匹配 返回true
     */
    @Test
    public void noneMatchTest() {
        boolean b = list.stream()
                .peek(new Consumer<Sku>() {
                    @Override
                    public void accept(Sku sku) {
                        System.out.println(JSON.toJSONString(sku, true));
                    }
                })
                .noneMatch(sku -> sku.getTotalPrice() < 100);
        System.out.println(b);
    }

    /**
     * 找到第一个
     */
    @Test
    public void findFirstTest() {
        Optional<Sku> first = list.stream()
                .findFirst();
        System.out.println(JSON.toJSONString(first.get(), true));
    }

    @Test
    public void findAnyTest() {
        Optional<Sku> any = list.stream()
                .findAny();
        System.out.println(JSON.toJSONString(any.get(), true));
    }

    // 非短路终端操作
    @Test
    public void maxTest() {
        OptionalDouble max = list.stream()
                .mapToDouble(Sku::getTotalPrice)
                .max();
        System.out.println(max.getAsDouble());
    }

    @Test
    public void minTest() {
        OptionalDouble min = list.stream()
                .mapToDouble(Sku::getTotalPrice)
                .min();
        System.out.println(min.getAsDouble());
    }

    @Test
    public void countTest() {
        long count = list.stream()
                .count();
        System.out.println(count);
    }
}
