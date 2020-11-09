package cn.niudehua.efficientprogramming.guava;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 使用流（Source）与汇（Sink）来对文件进行常用操作
 *
 * @author deng
 * @datetime 2020/11/8 11:00 下午
 */
public class IOTest {
    @Test
    public void copyFile() throws IOException {
        // 创建对应的流和汇
        CharSource charSource = Files.asCharSource(new File("SourceText.txt"), Charsets.UTF_8);
        CharSink charSink = Files.asCharSink(new File("TargetText.txt"), Charsets.UTF_8);
        // 拷贝
        charSource.copyTo(charSink);
    }
}
