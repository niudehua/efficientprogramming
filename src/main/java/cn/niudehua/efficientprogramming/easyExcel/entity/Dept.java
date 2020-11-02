package cn.niudehua.efficientprogramming.easyExcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author deng
 * @datetime 2020/10/24 2:01 下午
 */
@Data
public class Dept {
    @ExcelProperty(value = "部门编号")
    private Integer deptno;
    @ExcelProperty(value = "部门名称")
    private String dname;
    @ExcelProperty(value = "部门来源")
    private String dbDource;
    @ExcelProperty(value = "员工名")
    private String empname;
}
