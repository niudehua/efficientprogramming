package cn.niudehua.efficientprogramming.lombok;

import com.sun.istack.internal.NotNull;
import lombok.AccessLevel;
import lombok.Setter;

/**
 * @author deng
 * @datetime 2020/11/11 11:40 下午
 */
public class SetterTest {
    @Setter(value = AccessLevel.PRIVATE, onMethod_ = {@NotNull}, onParam_ = {@NotNull})
    private String filed1;
    private String filed2;
}
