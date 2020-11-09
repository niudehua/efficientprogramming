package cn.niudehua.efficientprogramming.resouce;

import cn.niudehua.efficientprogramming.lambda.file.FileConsumer;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 资源关闭对比
 *
 * @author deng
 * @datetime 2020/11/6 12:08 上午
 */
public class ResourceCloseVS {
    @Test
    public void newFileHandle(String url, FileConsumer fileConsumer) {
        try (
                // 声明、创建文件的读取流
                FileInputStream fileInputStream = new FileInputStream(url);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            // 循环读取文件内容
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            // 调用函数式接口方法，将文件内容传递给lambda表达式，实现业务逻辑
            fileConsumer.fileHandle(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void oldFileHandle(String url, FileConsumer fileConsumer) {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileInputStream = new FileInputStream(url);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            fileConsumer.fileHandle(stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
