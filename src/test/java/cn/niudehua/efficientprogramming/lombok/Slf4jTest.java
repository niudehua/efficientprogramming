package cn.niudehua.efficientprogramming.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author deng
 * @datetime 2020/11/12 9:08 下午
 */
@Slf4j
public class Slf4jTest {
    @Test
    public void func() {
        log.error("日志日志！！！！");
    }
}
