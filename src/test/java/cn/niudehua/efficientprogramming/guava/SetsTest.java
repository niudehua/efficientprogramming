package cn.niudehua.efficientprogramming.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Set;


/**
 * Lists/Sets使用
 *
 * @author deng
 * @datetime 2020/11/8 10:30 下午
 */
public class SetsTest {

    // Sets工具类的常用方法
    // 并集、交集、差集、分解集合中的所有子集、求两个集合的笛卡尔积
    private static final Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4);
    private static final Set<Integer> set2 = Sets.newHashSet(4, 5, 6);

    @Test
    public void union() {
        //并集
        Set<Integer> union = Sets.union(set1, set2);
        System.out.println(union);
    }

    @Test
    public void intersection() {
        // 交集
        Set<Integer> intersection = Sets.intersection(set1, set2);
        System.out.println(intersection);

    }

    @Test
    public void difference() {
        // 差集 set1存在 set2中不存在的元素
        Set<Integer> difference = Sets.difference(set1, set2);
        System.out.println(difference);
        // 相对差集 set1中有但是set2中没有的元素 或者set2中有但是set1中没有的元素
        difference = Sets.symmetricDifference(set1, set2);
        System.out.println(difference);
    }

    @Test
    public void powerSet() {
        // 拆分子集
        Set<Set<Integer>> set = Sets.powerSet(set1);
        System.out.println(set);
        System.out.println(JSON.toJSONString(set));
    }

    @Test
    public void cartesianProduct() {
        // 计算两个集合的笛卡尔积
        Set<List<Integer>> cartesianProduct = Sets.cartesianProduct(set1, set2);
        System.out.println(cartesianProduct);
        System.out.println(JSON.toJSONString(cartesianProduct));
    }


    // Lists工具类的常用方式
    // 反转、拆分
    @Test
    public void partition() {
        // 拆分成 3个3个一组
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        List<List<Integer>> partition = Lists.partition(list, 3);
        System.out.println(JSON.toJSONString(partition));
    }

    @Test
    public void reverse() {
        // 元素反转
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> reverse = Lists.reverse(list);
        System.out.println(JSON.toJSONString(reverse));


    }

}
