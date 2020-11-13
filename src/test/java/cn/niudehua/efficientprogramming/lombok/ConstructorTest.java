package cn.niudehua.efficientprogramming.lombok;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author deng
 * @datetime 2020/11/12 8:50 下午
 */
//@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
public class ConstructorTest {
    private String field1;
    private String field2;
    @NonNull
    private String field3;
    private final String field4;
}
