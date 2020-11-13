package cn.niudehua.efficientprogramming.lombok;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import lombok.Cleanup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 资源关闭
 *
 * @author deng
 * @datetime 2020/11/12 8:53 下午
 */
public class CleanupTest {
    public static void copyFile(String in, String out) throws IOException {
        @Cleanup FileInputStream inputStream = new FileInputStream(new File(in));
        @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(new File(out));
        int content;
        while ((content = inputStream.read()) != -1) {
            fileOutputStream.write(content);
        }
    }

    public static void main(String[] args) {
        try {
            copyFile("/Users/deng/Downloads/efficientprogramming/src/test/java/cn/niudehua/efficientprogramming/guava/IOTest.java", "/Users/deng/Downloads/efficientprogramming/src/test/java/cn/niudehua/efficientprogramming/guava/IOTest.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
