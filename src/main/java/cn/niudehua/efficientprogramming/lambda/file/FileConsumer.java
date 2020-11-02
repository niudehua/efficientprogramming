package cn.niudehua.efficientprogramming.lambda.file;

/**
 * 文件处理函数式接口
 * @author deng
 * @datetime 2020/10/23 11:53 下午
 */
@FunctionalInterface
public interface FileConsumer {
    /**
     * 函数式接口抽象方法
     * @param fileContent -文件内容字符串
     */
    void fileHandle(String fileContent);
}
