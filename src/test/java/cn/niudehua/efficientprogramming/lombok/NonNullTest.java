package cn.niudehua.efficientprogramming.lombok;

import lombok.NonNull;

/**
 * @author deng
 * @datetime 2020/11/12 12:06 上午
 */
public class NonNullTest {
    public void nonNullTest(@NonNull String field) {
        System.out.println(field);
    }
}
