package cn.niudehua.efficientprogramming.mapstruct.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 类名称：Car
 * ***********************
 * <p>
 * 类描述：
 *
 * @author deng on 2020/12/1715:06
 */
@Data
@AllArgsConstructor
public class Car {

    private String make;
    private int numberOfSeats;
    private CarType type;

}