package cn.niudehua.efficientprogramming.stream;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 流的四种构建形式
 *
 * @author deng
 * @datetime 2020/11/3 12:03 上午
 */
public class StreamConstructor {
    @Test
    public void streamFromValue() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5, 6, 7);
        integerStream.forEach(System.out::println);
    }

    /**
     * 通过数组构建流
     */
    @Test
    public void streamFromArray() {
        int[] numbers = {1, 2, 3, 56, 7, 8, 9, 6, 54, 123, 3};
        IntStream stream = Arrays.stream(numbers);
        stream.forEach(System.out::println);
    }

    @Test
    public void streamFromFile() throws IOException {
        Stream<String> lines = Files.lines(Paths.get("/Users/deng/Downloads/efficientprogramming/src/test/java/cn/niudehua/efficientprogramming/stream/StreamConstructor.java"));
        lines.forEach(System.out::println);
    }

    @Test
    public void streamFromFunction() {
        Stream<Integer> iterate = Stream.iterate(0, new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 2;
            }
        });
        iterate.limit(5)
                .forEach(System.out::println);
//        Stream<Object> generate = Stream.generate(new Supplier<Object>() {
//            @Override
//            public Object get() {
//
//                return Math.random();
//            }
//        });
//        generate.limit(100)
//                .forEach(System.out::println);

    }
}
