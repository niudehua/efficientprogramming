package cn.niudehua.efficientprogramming.validation;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 待验证实体类
 *
 * @author deng
 * @datetime 2020/11/12 10:16 下午
 */
@Data
@Builder
public class UserInfo {
    @NotNull(message = "用户Id不能为空")
    private String userId;
    /**
     * Empty 不会去掉空格
     */
    @NotEmpty(message = "用户名不能为空")
    private String userName;
    /**
     * Blank 去掉空格
     */
    @NotBlank(message = "用户密码不能为空")
    @Length(max = 10, min = 6, message = "密码长度最大{max}位，最小{min}位")
    private String passWord;
    @Email(message = "请输入有效邮箱")
    private String email;
    private String phone;
    private LocalDateTime birthday;
    private List<UserInfo> friends;
}
