package cn.niudehua.efficientprogramming.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.font.ScriptRun;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 验证测试类
 *
 * @author deng
 * @datetime 2020/11/12 10:17 下午
 */
public class ValidationTest {
    private Validator validator;
    private UserInfo userInfo;
    private Set<ConstraintViolation<UserInfo>> set;


    @Before
    public void init() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        userInfo = UserInfo.builder()
                .userId("niudehua")
                .userName("niudehua")
                .passWord("niudehua")
                .email("deng.xiaobing@foxmail.com")
                .build();

    }

    @Test
    public void nullValidation() {
        set = validator.validate(userInfo);
    }

    @After
    public void print() {
        set.forEach(item -> System.out.println(item.getMessage()));
    }
}
