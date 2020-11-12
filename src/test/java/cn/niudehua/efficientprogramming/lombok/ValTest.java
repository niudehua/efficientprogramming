package cn.niudehua.efficientprogramming.lombok;

import com.google.common.collect.Lists;
import lombok.var;

/**
 * @author deng
 * @datetime 2020/11/12 12:01 上午
 */
public class ValTest {
    public void varTest() {
        var a = "aaaa";
        var b = Lists.<String>newArrayList();
        b.add(a);
    }
}
