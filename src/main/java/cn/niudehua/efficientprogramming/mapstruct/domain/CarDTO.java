package cn.niudehua.efficientprogramming.mapstruct.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 类名称：CarDTO
 * ***********************
 * <p>
 * 类描述：
 *
 * @author deng on 2020/12/1715:08
 */
@Data
@AllArgsConstructor
public class CarDTO {
    private String make;
    private int seatCount;
    private String type;
}