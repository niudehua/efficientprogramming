package cn.niudehua.efficientprogramming.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.assertj.core.util.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 不可变集合用法
 *
 * @author deng
 * @datetime 2020/11/8 10:08 下午
 */
public class ImmutableTest {
    public static void test(List<Integer> list) {
        list.remove(0);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Object> newList = Collections.unmodifiableList(list);
        test(list);
        System.out.println(list);
    }

    @Test
    public void immutable() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        // 构造不可变集合对象的三种方式
        // 通过已经存在的集合创建
        ImmutableSet.copyOf(list);
        // 通过初始值，直接创建不可变集合
        ImmutableSet.of(1, 2, 3, 4);
        // 以builder方式创建
        final ImmutableSet<Object> immutableSet = ImmutableSet.builder()
                .add(1)
                .addAll(list)
                .add(2)
                .add(2)
                .add(5)
                .build();
        System.out.println(immutableSet);
        ImmutableList immutableList = ImmutableList.builder()
            .add(1)
            .add(1)
            .add(1)
            .add(1)
            .add(1)
            .build();
        System.out.println(immutableList);

    }


}
