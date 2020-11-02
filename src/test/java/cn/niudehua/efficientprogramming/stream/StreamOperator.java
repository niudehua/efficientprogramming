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
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Test
    public void peekTest() {
        list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)));
    }

    @Test
    public void sortTest() {
        list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .sorted(Comparator.comparing(Sku::getSkuPrice))
                .forEach(item ->
                        System.out.println(
                                JSON.toJSONString(item, true)));
    }
}
