package cn.niudehua.efficientprogramming.lombok;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author deng
 * @datetime 2020/11/12 9:17 下午
 */
@Builder
@Data
public class BuilderTest<T> {
    private static String staticField;
    private final String finalField;
    private final String initField = "初始化字段";
    private String field = "hihi";
    @Singular
    private List<T> lists;

    public static void main(String[] args) {
        BuilderTest haha = BuilderTest.builder()
                .finalField("final 字段")
                .field("haha")
//                .list(new ArrayList<>())
                .list("张三")
                .list(11)
                .list(new HashMap<>())
                .build();
        System.out.println(haha);
        haha.setField("hehe");
        System.out.println(JSON.toJSONString(haha, true));
    }

}
