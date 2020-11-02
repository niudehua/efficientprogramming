package cn.niudehua.efficientprogramming.lambda.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FileService测试
 *
 * @author deng
 * @datetime 2020/10/24 12:05 上午
 */
class FileServiceTest {

    @Test
    void fileHandle() throws IOException {
        FileService fileService = new FileService();
        String url = "/Users/deng/Downloads/efficientprogramming/src/main/java/cn/niudehua/efficientprogramming/lambda/file/FileService.java";
        fileService.fileHandle(url, x -> System.out.println(x));
    }


}