package com.examsys.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.stereotype.Component;

/**
 * Created by twinkleStar on 2019/9/8.
 */
@Component
public class ExcelTemplateUtil {

    public HSSFWorkbook addUsersTemplate(){

        HSSFWorkbook   wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("添加用户excel模板");

        HSSFRow row1 = sheet.createRow(0);
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style1.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置背景色
        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFCell cell1 = null;
        for(int i=0;i<2;i++){
            cell1 = row1.createCell(i);
            cell1.setCellValue("在线考试系统用户导入模板");
            cell1.setCellStyle(style1);
        }
        CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, 1);
        sheet.addMergedRegion(region1);

        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框



        HSSFRow row2 = sheet.createRow(1);
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style2.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置背景色
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框


        HSSFCell cell2 = null;

        for(int i=0;i<2;i++){
            cell2 = row2.createCell(i);
            cell2.setCellValue("请勿修改黄色区域表格，所有单元格应为文本格式");
            cell2.setCellStyle(style2);
        }
        CellRangeAddress region2 = new CellRangeAddress(1, 1, 0, 1);
        sheet.addMergedRegion(region2);

        HSSFRow row3 = sheet.createRow(2);
        HSSFCellStyle style3 = wb.createCellStyle();
        style3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style3.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置背景色
        style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style3.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style3.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style3.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        HSSFCell cell3 = null;

        cell3 = row3.createCell(0);
        cell3.setCellValue("姓名");
        cell3.setCellStyle(style3);
        cell3 = row3.createCell(1);
        cell3.setCellValue("身份证号");
        cell3.setCellStyle(style3);


        sheet.setColumnWidth(0, 60 * 128);  //设置列宽，20个字符宽
        sheet.setColumnWidth(1, 60 * 128);
        return wb;
    }



    public HSSFWorkbook addPaperTemplate(){

        HSSFWorkbook   wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("添加试卷excel模板");

        HSSFRow row1 = sheet.createRow(0);
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style1.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置背景色
        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFCell cell1 = null;
        for(int i=0;i<5;i++){
            cell1 = row1.createCell(i);
            cell1.setCellValue("在线考试系统试卷导入模板");
            cell1.setCellStyle(style1);
        }
        CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, 4);
        sheet.addMergedRegion(region1);

        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框



        HSSFRow row2 = sheet.createRow(1);
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style2.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置背景色
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框


        HSSFCell cell2 = null;

        for(int i=0;i<5;i++){
            cell2 = row2.createCell(i);
            cell2.setCellValue("请勿修改黄色区域表格，分值必须为数字");
            cell2.setCellStyle(style2);
        }
        CellRangeAddress region2 = new CellRangeAddress(1, 1, 0, 4);
        sheet.addMergedRegion(region2);

        HSSFRow row3 = sheet.createRow(2);
        HSSFCellStyle style3 = wb.createCellStyle();
        style3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style3.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置背景色
        style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style3.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style3.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style3.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        HSSFCell cell3 = null;

        cell3 = row3.createCell(0);
        cell3.setCellValue("题干");
        cell3.setCellStyle(style3);

        cell3 = row3.createCell(1);
        cell3.setCellValue("题目类型");
        cell3.setCellStyle(style3);

        cell3 = row3.createCell(2);
        cell3.setCellValue("分值");
        cell3.setCellStyle(style3);

        cell3 = row3.createCell(3);
        cell3.setCellValue("参考答案");
        cell3.setCellStyle(style3);

        cell3 = row3.createCell(4);
        cell3.setCellValue("选项");
        cell3.setCellStyle(style3);

        String[] textlist={"单选题","多选题","判断题","主观题"};
        DVConstraint constraint = DVConstraint
                .createExplicitListConstraint(textlist);

        CellRangeAddressList regions = new CellRangeAddressList(3,
                60, 1, 1);
        HSSFDataValidation data_validation_list = new HSSFDataValidation(
                regions, constraint);
        sheet.addValidationData(data_validation_list);

        DVConstraint constraint2 = DVConstraint.createNumericConstraint(DVConstraint.ValidationType.DECIMAL,
                DVConstraint.OperatorType.GREATER_OR_EQUAL, "0", "0");
        // 设定在哪个单元格生效
        CellRangeAddressList regions2 = new CellRangeAddressList(3, 60, 2, 2);
        // 创建规则对象
        HSSFDataValidation ret = new HSSFDataValidation(regions2, constraint2);
        sheet.addValidationData(ret);


        sheet.setColumnWidth(0, 60 * 128);  //设置列宽，20个字符宽
        sheet.setColumnWidth(1, 60 * 60);
        sheet.setColumnWidth(2, 60 * 60);
        sheet.setColumnWidth(3, 60 * 60);
        sheet.setColumnWidth(4, 60 * 128);

        return wb;
    }



    public HSSFWorkbook addQuestionsTemplate(){

        HSSFWorkbook   wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("添加题目excel模板");

        HSSFRow row1 = sheet.createRow(0);
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style1.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置背景色
        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFCell cell1 = null;
        for(int i=0;i<4;i++){
            cell1 = row1.createCell(i);
            cell1.setCellValue("在线考试系统题库导入模板");
            cell1.setCellStyle(style1);
        }
        CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, 3);
        sheet.addMergedRegion(region1);

        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框


        HSSFRow row2 = sheet.createRow(1);
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style2.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置背景色
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框


        HSSFCell cell2 = null;

        for(int i=0;i<4;i++){
            cell2 = row2.createCell(i);
            cell2.setCellValue("请勿修改黄色区域表格");
            cell2.setCellStyle(style2);
        }
        CellRangeAddress region2 = new CellRangeAddress(1, 1, 0, 3);
        sheet.addMergedRegion(region2);

        HSSFRow row3 = sheet.createRow(2);
        HSSFCellStyle style3 = wb.createCellStyle();
        style3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style3.setFillForegroundColor(IndexedColors.YELLOW.getIndex());// 设置背景色
        style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style3.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style3.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style3.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框

        HSSFCell cell3 = null;

        cell3 = row3.createCell(0);
        cell3.setCellValue("题干");
        cell3.setCellStyle(style3);

        cell3 = row3.createCell(1);
        cell3.setCellValue("题目类型");
        cell3.setCellStyle(style3);

        cell3 = row3.createCell(2);
        cell3.setCellValue("参考答案");
        cell3.setCellStyle(style3);

        cell3 = row3.createCell(3);
        cell3.setCellValue("选项");
        cell3.setCellStyle(style3);

        String[] textlist={"单选题","多选题","判断题","主观题"};
        DVConstraint constraint = DVConstraint
                .createExplicitListConstraint(textlist);

        CellRangeAddressList regions = new CellRangeAddressList(3,
                60, 1, 1);
        HSSFDataValidation data_validation_list = new HSSFDataValidation(
                regions, constraint);
        sheet.addValidationData(data_validation_list);


        sheet.setColumnWidth(0, 60 * 128);  //设置列宽，20个字符宽
        sheet.setColumnWidth(1, 60 * 60);
        sheet.setColumnWidth(2, 60 * 60);
        sheet.setColumnWidth(3, 60 * 128);

        return wb;
    }
}
