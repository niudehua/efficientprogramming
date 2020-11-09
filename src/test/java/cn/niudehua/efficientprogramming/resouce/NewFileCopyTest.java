package cn.niudehua.efficientprogramming.resouce;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Java7之后，实现正确关闭流资源的方法
 * try - with - resources
 *
 * @author deng
 * @datetime 2020/11/6 12:14 上午
 */
public class NewFileCopyTest {
    public void copyFile() {

        // 1.创建输入/输出流
        // 2.执行文件拷贝，读取文件内容，写入到另一个文件中
        // 3.关闭文件流资源

        // 定义输入路径和输出路径
        String originalUrl = "";
        String targetUrl = "";

        try (
                FileInputStream originalFileInputStream = new FileInputStream(originalUrl);
                FileOutputStream targetFileOutputStream = new FileOutputStream(targetUrl);
        ) {
            // 读取的字节信息
            int content;
            // 迭代 读取、写入字节
            while ((content = originalFileInputStream.read()) != -1) {
                targetFileOutputStream.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
