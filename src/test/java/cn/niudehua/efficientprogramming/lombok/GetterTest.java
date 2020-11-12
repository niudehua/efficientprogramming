package cn.niudehua.efficientprogramming.lombok;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author deng
 * @datetime 2020/11/11 11:30 下午
 */
public class GetterTest {
    @Getter(lazy = true)
    private final String filed1 = "lazy";
    @Getter(value = AccessLevel.PRIVATE, onMethod_ = {@NotNull})
    private String filed2;


}
