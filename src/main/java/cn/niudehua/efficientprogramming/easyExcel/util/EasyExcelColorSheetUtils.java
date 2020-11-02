package cn.niudehua.efficientprogramming.easyExcel.util;

import cn.niudehua.efficientprogramming.easyExcel.entity.Dept;
import cn.niudehua.efficientprogramming.easyExcel.handler.CellColorSheetWriteHandler;
import cn.niudehua.efficientprogramming.easyExcel.handler.TitleColorSheetWriteHandler;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author deng
 * @datetime 2020/10/24 3:23 下午
 */
public class EasyExcelColorSheetUtils {
    /**
     * 导出表头必填字段标红色
     *
     * @param outputStream      输入流
     * @param dataList          导入数据
     * @param headList          表头列表
     * @param sheetName         sheetname
     * @param cellWriteHandlers
     */
    public static void writeExcelWithModel(OutputStream outputStream, List<? extends Object> dataList, List<String> headList, String sheetName, CellWriteHandler... cellWriteHandlers) {
        List<List<String>> list = new ArrayList<>();
        if (headList != null) {
            headList.forEach(h -> list.add(Collections.singletonList(h)));
        }

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 单元格策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 初始化表格样式
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.write(outputStream).head(list).sheet(sheetName).registerWriteHandler(horizontalCellStyleStrategy);
        if (null != cellWriteHandlers && cellWriteHandlers.length > 0) {
            for (CellWriteHandler cellWriteHandler : cellWriteHandlers) {
                excelWriterSheetBuilder.registerWriteHandler(cellWriteHandler);
            }
        }
        // 开始导出
        excelWriterSheetBuilder.doWrite(dataList);
    }

    /**
     * 导出表头必填字段标红色
     *
     * @param outputStream      输入流
     * @param dataList          导入数据
     * @param headList          表头列表
     * @param sheetName         sheetname
     * @param cellWriteHandlers
     */
    public static void writeExcelWithModel(OutputStream outputStream, List<? extends Object> dataList, Class<? extends Object> headList, String sheetName, CellWriteHandler... cellWriteHandlers) {

        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 单元格策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 初始化表格样式
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.write(outputStream, headList).sheet(sheetName).registerWriteHandler(horizontalCellStyleStrategy);
        if (null != cellWriteHandlers && cellWriteHandlers.length > 0) {
            for (int i = 0; i < cellWriteHandlers.length; i++) {
                excelWriterSheetBuilder.registerWriteHandler(cellWriteHandlers[i]);
            }
        }
        // 开始导出
        excelWriterSheetBuilder.doWrite(dataList);
    }

    public static void main(String[] args) throws Exception {
        // 输出流
        OutputStream outputStream = null;
        outputStream = new FileOutputStream(new File("/Users/deng/desktop/测试.xlsx"));

        // 导出的数据
        List<Dept> depts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Dept dept = new Dept();
            dept.setDname("d" + i);
            dept.setDeptno(i);
            dept.setDbDource("s" + i);
            dept.setEmpname("0000000000000000000000000000000000000000000e" + i);
            depts.add(dept);
        }

        // 标题
        List<String> headList = Arrays.asList("姓名", "年龄", "学校");

        String sheetName = "导出文件";

        List<Integer> columnIndexs = Arrays.asList(0, 1, 2);
        List<Integer> rowIndexs = Collections.singletonList(0);
        TitleColorSheetWriteHandler titleColorSheetWriteHandler = new TitleColorSheetWriteHandler(rowIndexs, columnIndexs, IndexedColors.RED.index);

        List<Integer> columnIndexs1 = Arrays.asList(0, 1);
        List<Integer> rowIndexs1 = Arrays.asList(1, 2, 3, 4);
        CellColorSheetWriteHandler colorSheetWriteHandler = new CellColorSheetWriteHandler(rowIndexs1, columnIndexs1, IndexedColors.RED.index);

        writeExcelWithModel(outputStream, depts, headList, sheetName, titleColorSheetWriteHandler, colorSheetWriteHandler);
    }
}
