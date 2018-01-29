package com.ws.stoner.utils;

import com.ws.stoner.model.dto.BriefTemplateDTO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhongkf on 2018/1/26
 */
public class ExportExcelUtil {

    public static final String templateNames[] = {
            "Windows_OS_Agent","Linux_OS_Agent","AIX_OS_Agent","Zabbix_Server",
            "Cisco_Switch_SNMP","Cisco_Firewall_SNMP","Cisco_AP_SNMP","Myqsl_Basic",
            "Oracle_Basic","SQL Server Basic","Template Virt VMare Hypervisor","Template Virt VMare Guest",
            "Template Virt VMare"};

    public void SXexportExcel(List<BriefTemplateDTO> templateDTOS) throws IOException {
        // 创建基于stream的工作薄对象的
        SXSSFWorkbook wb = new SXSSFWorkbook();
        Sheet sh = wb.createSheet();
        Row row1=sh.createRow(0);
        Cell cell1 = row1.createCell(0);
        cell1.setCellValue("模板ID");

        Cell cell2 = row1.createCell(1);
        cell2.setCellValue("模板名称");

        Cell cell3 = row1.createCell(2);
        cell3.setCellValue("模板分组");
        for(int i=0; i < templateDTOS.size(); i++) {
            Row row = sh.createRow(1 + i);
            row.createCell(0).setCellValue(templateDTOS.get(i).getTemplateId());
            row.createCell(1).setCellValue(templateDTOS.get(i).getName());
            row.createCell(2).setCellValue("分组");
        }
        // 写入文件中
        FileOutputStream out = new FileOutputStream("c:/zabbix/sxssf.xlsx");
        wb.write(out);
        // 关闭文件流对象
        out.close();
        System.out.println("基于流写入执行完毕!");
    }

    public void SHexportTempExcel(List<BriefTemplateDTO> templateDTOS) throws IOException {
        HSSFWorkbook wkb = new HSSFWorkbook();
        HSSFSheet sheet = wkb.createSheet("模板");
        HSSFRow row1=sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("模板ID");

        HSSFCell cell2 = row1.createCell(1);
        cell2.setCellValue("模板名称");

        HSSFCell cell3 = row1.createCell(2);
        cell3.setCellValue("模板分组");
        for(int i=0; i < templateDTOS.size(); i++) {
            HSSFRow row = sheet.createRow(1 + i);
            row.createCell(0).setCellValue(templateDTOS.get(i).getTemplateId());
            row.createCell(1).setCellValue(templateDTOS.get(i).getName());
            row.createCell(2).setCellValue("分组");

        }
        // 写入文件中
        FileOutputStream out = new FileOutputStream("c:/zabbix/sxssf.xls");
        wkb.write(out);
        // 关闭文件流对象
        out.close();
        System.out.println("基于流写入执行完毕!");

    }

}
