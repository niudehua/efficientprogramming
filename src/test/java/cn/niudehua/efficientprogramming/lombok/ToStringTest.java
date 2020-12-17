package cn.niudehua.efficientprogramming.lombok;

/**
 * @author deng
 * @datetime 2020/11/11 11:45 下午
 */

import lombok.Getter;
import lombok.ToString;

@ToString(
        includeFieldNames = false,
        of = {"field2"},
        doNotUseGetters = true
        )
public class ToStringTest {

    private String field1;
    @Getter
    private String field2;
}
