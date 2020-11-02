package cn.niudehua.efficientprogramming.easyExcel.controller;

import cn.niudehua.efficientprogramming.easyExcel.entity.Dept;
import cn.niudehua.efficientprogramming.easyExcel.handler.CellColorSheetWriteHandler;
import cn.niudehua.efficientprogramming.easyExcel.handler.CustomCellWriteHandler;
import cn.niudehua.efficientprogramming.easyExcel.handler.TitleColorSheetWriteHandler;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author deng
 * @datetime 2020/10/24 2:00 下午
 */
@RestController
public class DeptController {
    @RequestMapping("/download")
    public void download(HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");

        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        List<Dept> depts = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            Dept dept = new Dept();
            dept.setDname("d" + i);
            dept.setDeptno(i);
            dept.setDbDource("s" + i);
            dept.setEmpname("0000000000000000000000000000000000000000000e" + i);
            depts.add(dept);
        }

        ExcelWriterBuilder write = EasyExcel.write(response.getOutputStream(), Dept.class);
        write.head(Dept.class)
                .registerWriteHandler(new CustomCellWriteHandler())
                .registerWriteHandler(new TitleColorSheetWriteHandler())
                .registerWriteHandler(new CellColorSheetWriteHandler())
                .sheet("模板").doWrite(depts);
    }
}
