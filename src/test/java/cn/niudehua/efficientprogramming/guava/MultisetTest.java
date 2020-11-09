package cn.niudehua.efficientprogramming.guava;

import com.google.common.collect.HashMultiset;
import com.google.common.primitives.Chars;
import org.junit.Test;

/**
 * 实现：使用Multiset统计一首古诗的文字出现频率
 *
 * @author deng
 * @datetime 2020/11/8 10:23 下午
 */
public class MultisetTest {
    private static final String text = "南陵别儿童入京" +
            "白酒新熟山中归，黄鸡啄黍秋正肥。" +
            "呼童烹鸡酌白酒，儿女嬉笑牵人衣。" +
            "高歌取醉欲自慰，起舞落日争光辉。" +
            "游说万乘苦不早，著鞭跨马涉远道。" +
            "会稽愚妇轻买臣，余亦辞家西入秦。" +
            "仰天大笑出门去，我辈岂是蓬蒿人。";

    @Test
    public void handle() {
        final HashMultiset<Character> multiset = HashMultiset.create();
        final char[] chars = text.toCharArray();
        Chars.asList(chars)
                .stream()
                .forEach(character -> multiset.add(character));
        System.out.println("size:" + multiset.size());
        System.out.println(multiset);
        System.out.println(multiset.count('人'));
    }
}
