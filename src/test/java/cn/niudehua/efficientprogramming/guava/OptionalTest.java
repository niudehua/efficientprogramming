package cn.niudehua.efficientprogramming.guava;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Java8中的Optional使用方法
 *
 * @author deng
 * @datetime 2020/11/8 9:54 下午
 */
public class OptionalTest {
    public void test() throws Throwable {
        // 三种创建Optional对象的方式
        // 创建空的Optional对象
        Optional.empty();
        // 使用非null值创建Optional对象
        Optional.of("niudehua");
        // 使用任意值创建Optional对象
        Optional optional = Optional.ofNullable(null);

        // 判断是否引用缺失的方法（建议不直接使用）
        optional.isPresent();

        // 当optional引用存在时执行
        optional.ifPresent(System.out::println);

        // 当optional引用缺失时执行
        optional.orElse("引用缺失");
        optional.orElseGet(() -> "自定义引用缺失");
        optional.orElseThrow(() -> {
            throw new RuntimeException("引用缺失异常");
        });
    }

    public static void stream(List<String> list) {
//        list.forEach(System.out::println);
        Optional.ofNullable(list)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .forEach(System.out::println);
    }


    public static void main(String[] args) {
        stream(null);
    }
}

