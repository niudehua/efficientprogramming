package cn.niudehua.efficientprogramming.stream.cases;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 案例一：班级中有20名学生，每名学生有5门课的考试成绩。其中缺考的科目分数字段为空，需要找出有缺考的学生都叫什么名字
 *
 * @author deng
 * @datetime 2020/11/4 12:09 上午
 */
public class CaseOne {

    /**
     * 考试成绩模型
     */
    @Data
    @AllArgsConstructor
    class ExamStudentScore {
        /**
         * 学生名字
         */
        private String studentName;
        /**
         * 成绩
         */
        private Integer scoreValue;
        /**
         * 分数
         */
        private String subject;
    }

    Map<String, List<ExamStudentScore>> studentMap;

    @Before
    public void init() {
        studentMap = new HashMap<>();
        List<ExamStudentScore> zs = new ArrayList<>();
        zs.add(new ExamStudentScore("zhangsan", 30, "CHINESE"));
        zs.add(new ExamStudentScore("zhangsan", 30, "ENGLISH"));
        zs.add(new ExamStudentScore("zhangsan", 30, "MATHS"));
        List<ExamStudentScore> ls = new ArrayList<>();
        ls.add(new ExamStudentScore("lisi", 30, "CHINESE"));
        ls.add(new ExamStudentScore("lisi", null, "ENGLISH"));
        ls.add(new ExamStudentScore("lisi", 30, "MATHS"));
        List<ExamStudentScore> ww = new ArrayList<>();
        ww.add(new ExamStudentScore("wangwu", 30, "CHINESE"));
        ww.add(new ExamStudentScore("wangwu", null, "ENGLISH"));
        ww.add(new ExamStudentScore("wangwu", null, "MATHS"));
        studentMap.put("zhangsan", zs);
        studentMap.put("lisi", ls);
        studentMap.put("wangwu", ww);
    }

    @Test
    public void findStudent() {
        studentMap.forEach((s, scores) -> {
            boolean b = scores.stream()
                    .peek(examStudentScore -> System.out.println(JSON.toJSONString(examStudentScore, true)))
                    .anyMatch(examStudentScore -> examStudentScore.getScoreValue() == null);
            if (b) {
                System.out.println(s + "有缺考的情况");

            }
        });
    }
}
