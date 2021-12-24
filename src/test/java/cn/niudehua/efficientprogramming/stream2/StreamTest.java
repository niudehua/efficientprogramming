package cn.niudehua.efficientprogramming.stream2;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 类名称：StreamTest
 * ***********************
 * <p>
 * 类描述：
 *
 * @author deng on 2020/12/512:35
 */
public class StreamTest {

    @Test
    public void filterTest() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);

        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 7).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 7).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x > 7);

        findAny.ifPresent(integer -> System.out.println("匹配任意一个值：" + integer));
        findFirst.ifPresent(integer -> System.out.println("匹配第一个值：" + integer));
        System.out.println("是否存在大于7的值：" + anyMatch);
    }

    @Test
    public void filterTest1() {
        List<Integer> list = Arrays.asList(6, 7, 3, 8, 1, 2, 9);
        // 筛选出Integer集合中大于7的元素，并打印出来
        list.stream().filter(x -> x > 7).forEach(System.out::println);
    }

    @Test
    public void test2() {
        // 筛选员工中工资高于8000的人，并形成新的集合
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        List<String> filterList = personList.stream()
            .filter(x -> x.getSalary() > 8000)
            .map(Person::getName)
            .collect(Collectors.toList());
        System.out.print("高于8000的员工姓名：" + filterList);
    }

    @Test
    public void test3() {
        // 获取String集合中最长的元素。
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list.stream()
            .max(Comparator.comparing(String::length));
        System.out.println("最长的字符串：" + max.get());
    }

    @Test
    public void test4() {
        List<Integer> list = Arrays.asList(7, 6, 9, 4, 11, 6);
        // 获取Integer集合中的最大值。
        // 自然排序
        Optional<Integer> max = list.stream()
            .max(Integer::compareTo);
        // 自定义排序
        Optional<Integer> max2 = list.stream()
            .max(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1.compareTo(o2);
                }
            });
        System.out.println("自然排序的最大值：" + max.get());
        System.out.println("自定义排序的最大值：" + max2.get());
    }

    @Test
    public void test5() {

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        // 获取员工工资最高的人。
        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        max.ifPresent(e -> {
            System.out.printf("员工工资最大值：%d", e.getSalary());
        });
    }

    @Test
    public void test6() {
        // 计算Integer集合中大于6的元素的个数。
        List<Integer> list = Arrays.asList(7, 6, 4, 8, 2, 11, 9);
        long count = list.stream().filter(x -> x > 6).count();
        System.out.println("list中大于6的元素个数：" + count);
    }

    @Test
    public void test7() {
        // 英文字符串数组的元素全部改为大写。
        String[] strArr = {"abcd", "bcdd", "defde", "fTr"};
        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        //整数数组每个元素+3。
        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> Integer.sum(x, 3)).collect(Collectors.toList());

        System.out.println("每个元素大写：" + strList);
        System.out.println("每个元素+3：" + intListNew);
    }

    @Test
    public void test8() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));
        // 将员工的薪资全部增加10000
        // 不改变原来员工集合的方式
        System.out.println("一次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        List<Person> personListNew = personList.stream()
                .map(person -> {
                    Person personNew = new Person(person.getName(), 0, 0, null, null);
                    personNew.setSalary(person.getSalary() + 10000);
                    return personNew;
                })
                .collect(Collectors.toList());
        System.out.println("一次改动后：" + personListNew.get(0).getName() + "-->" + personListNew.get(0).getSalary());

        System.out.println("二次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        // 改变原来员工集合的方式
        List<Person> personListNew2 = personList.stream()
                .peek(person -> {
                    person.setSalary(person.getSalary() + 10000);
                })
                .collect(Collectors.toList());
        System.out.println("二次改动后：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        System.out.println("二次改动后：" + personListNew2.get(0).getName() + "-->" + personListNew2.get(0).getSalary());
    }

    @Test
    public void test9() {
        // 将两个字符串数组合并成一个新的字符串数组。
        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list.stream()
                .flatMap(s -> {
                    // 将每个元素转换成一个stream
                    String[] split = s.split(",");
                    return Arrays.stream(split);
                }).collect(Collectors.toList());

        System.out.println("处理前的集合：" + list);
        System.out.println("处理后的集合：" + listNew);
    }

    @Test
    public void test10() {
        // 求Integer集合的元素之和、乘积和最大值。
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        // 求和方式1
        Optional<Integer> sum = list.stream()
                .reduce((x, y) -> x + y);
        // 求和方式2
        Optional<Integer> sum2 = list.stream()
                .reduce(Integer::sum);
        // 求和方式3
        Integer sum3 = list.stream()
                .reduce(0, Integer::sum);

        // 求乘积
        Optional<Integer> product = list.stream()
                .reduce((x, y) -> x * y);

        // 求最大值方式1
        Optional<Integer> max = list.stream()
                .reduce((x, y) -> x > y ? x : y);
        // 求最大值写法2
        Integer max2 = list.stream()
                .reduce(0, (a, b) -> {
                    return Integer.max(a, b);
                });

        System.out.println("list求和：" + sum.get() + "," + sum2.get() + "," + sum3);
        System.out.println("list求积：" + product.get());
        System.out.println("list求最大值：" + max.get() + "," + max2);
    }

    @Test
    public void test11() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        // 求工资之和方式1：
        Optional<Integer> sumSalary = personList.stream()
                .map(Person::getSalary)
                .reduce(Integer::sum);
        // 求工资之和方式2：
        Integer sumSalary2 = personList.stream()
                .reduce(0,
                        (sum, p) -> {
                            return sum += p.getSalary();
                        },
                        (sum1, sum2) -> {
                            return sum1 + sum2;
                        });
        // 求工资之和方式3：
        Integer sumSalary3 = personList.stream()
                .reduce(0,
                        (sum, p) -> sum += p.getSalary(),
                        Integer::sum);

        // 求最高工资方式1：
        Integer maxSalary = personList.stream()
                .reduce(0, (max, p) -> {
                            return max > p.getSalary() ? max : p.getSalary();
                        },
                        Integer::max);
        // 求最高工资方式2：
        List<Integer> collect = personList.stream().map(Person::getSalary).collect(Collectors.toList());
        Optional<Integer> max3 = collect.stream().max(Integer::compareTo);
        Integer maxSalary2 = personList.stream()
                .reduce(0,
                        (max, p) -> max > p.getSalary() ? max : p.getSalary(),
                        (max1, max2) -> max1 > max2 ? max1 : max2);

        System.out.println("工资之和：" + sumSalary.get() + "," + sumSalary2 + "," + sumSalary3);
        System.out.println("最高工资：" + maxSalary + "," + maxSalary2 + "," + max3.get());
    }

    @Test
    public void test12() {
        List<Integer> list = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> listNew = list.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());
        Set<Integer> set = list.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toSet());

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));

        Map<?, Person> map = personList.stream()
                .filter(p -> p.getSalary() > 8000)
                .collect(Collectors.toMap(Person::getName, p -> p));
        System.out.println("toList:" + listNew);
        System.out.println("toSet:" + set);
        System.out.println("toMap:" + map);
    }

    @Test
    public void test13() {
        // 统计员工人数、平均工资、工资总额、最高工资。
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        // 求总数
        Long count = personList.stream()
                .collect(Collectors.counting());
        // 求平均工资
        Double average = personList.stream()
                .collect(Collectors.averagingDouble(Person::getSalary));
        // 求最高工资
        Optional<Integer> max = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
        // 求最低工资
        Optional<Integer> min = personList.stream().map(Person::getSalary).collect(Collectors.minBy(Integer::compare));
        // 求工资之和
        Integer sum = personList.stream()
                .collect(Collectors.summingInt(Person::getSalary));
        // 一次性统计所有信息
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));

        System.out.println("员工总数：" + count);
        System.out.println("员工工资总和：" + sum);
        min.ifPresent(e -> System.out.println("员工最低工资：" + e));
        System.out.println("员工平均工资：" + average);
        max.ifPresent(e -> System.out.println("员工最高工资：" + e));
        System.out.println("员工工资所有统计：" + collect);
    }

    @Test
    public void test14() {
        // 将员工按薪资是否高于8000分为两部分；将员工按性别和地区分组
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200, "female", "New York"));
        personList.add(new Person("Owen", 9500, "male", "New York"));
        personList.add(new Person("Alisa", 7900, "female", "New York"));

        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> part = personList.stream()
                .collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream()
                .collect(Collectors.groupingBy(Person::getSex));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> group2 = personList.stream()
                .collect(Collectors.groupingBy(Person::getSex,
                        Collectors.groupingBy(Person::getArea)));
        System.out.println("员工按薪资是否大于8000分组情况：" + JSON.toJSONString(part, true));
        System.out.println("员工按性别分组情况：" + JSON.toJSONString(group, true));
        System.out.println("员工按性别、地区：" + JSON.toJSONString(group2, true));
    }

    @Test
    public void test15() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        String names = personList.stream()
                .map(Person::getName)
                .collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
        List<String> list = Arrays.asList("A", "B", "C");
        String string = list.stream()
                .collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);
    }

    @Test
    public void test16() {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        Integer sum = personList.stream()
                .collect(Collectors
                        .reducing(0,
                                Person::getSalary,
                                (i, j) -> (i + j - 5000)));
        System.out.println("员工扣税薪资总和：" + sum);

        // stream的reduce
        Optional<Integer> sum2 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());
    }

    @Test
    public void test17() {
        List<Person> personList = new ArrayList<>();

        personList.add(new Person("Sherry", 9000, 24, "female", "New York"));
        personList.add(new Person("Tom", 8900, 22, "male", "Washington"));
        personList.add(new Person("Jack", 9000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 8800, 26, "male", "New York"));
        personList.add(new Person("Alisa", 9000, 26, "female", "New York"));

        // 按工资升序排序（自然排序）
        List<String> newList = personList
                .stream()
                .sorted(Comparator.comparing(Person::getSalary))
                .map(Person::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = personList
                .stream()
                .sorted(Comparator
                        .comparing(Person::getSalary)
                        .reversed())
                .map(Person::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> newList3 = personList
                .stream()
                .sorted(Comparator
                        .comparing(Person::getSalary)
                        .thenComparing(Person::getAge))
                .map(Person::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = personList
                .stream()
                .sorted(new Comparator<Person>() {
                    @Override
                    public int compare(Person p1, Person p2) {
                        if (p1.getSalary() == p2.getSalary()) {
                            return p2.getAge() - p1.getAge();
                        } else {
                            return p2.getSalary() - p1.getSalary();
                        }
                    }
                })
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println("按工资升序排序：" + newList);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄升序排序：" + newList3);
        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);
    }

    @Test
    public void test20() {
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"d", "e", "f", "g"};

        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct：去重
        List<String> newList = Stream
                .concat(stream1, stream2)
                .distinct()
                .collect(Collectors.toList());
        // limit：限制从流中获得前n个数据
        List<Integer> collect = Stream
                .iterate(1, x -> x + 2)
                .limit(10)
                .collect(Collectors.toList());
        // skip：跳过前n个数据
        List<Integer> collect2 = Stream
                .iterate(1, x -> x + 2)
                .skip(1)
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("流合并：" + newList);
        System.out.println("limit：" + collect);
        System.out.println("skip：" + collect2);
    }
}


