package cn.niudehua.efficientprogramming.stream.cases;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 标签管理功能模块，允许用户批量添加标签，后台需要对标签去重，并且需要防止数据库中存在同名的标签
 *
 * @author deng
 * @datetime 2020/11/4 11:34 下午
 */
public class CaseTwo {

    // 用户请求的创建标签模型
    @Data
    @AllArgsConstructor
    class TagReqDTO {
        /**
         * 标签名
         */
        private String name;
        /**
         * 标签值：年龄
         */
        private Integer age;
    }

    List<String> tagListFromDB;
    List<TagReqDTO> tagListFromReq;

    @Before
    public void init() {
        tagListFromDB = Lists.newArrayList("张三", "李四", "王五");
        tagListFromReq = Lists.newArrayList(
                new TagReqDTO("李四", 17),
                new TagReqDTO("王五", 18),
                new TagReqDTO("李四", 19),
                new TagReqDTO("赵六", 20),
                new TagReqDTO("赵六", 21));
    }

    @Test
    public void distinctTag() {
        List<TagReqDTO> collect = tagListFromReq.stream()
                .filter(tagReqDTO -> !tagListFromDB.contains(tagReqDTO.getName()))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect, true));
    }
}
