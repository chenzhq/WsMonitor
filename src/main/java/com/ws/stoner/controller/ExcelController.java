package com.ws.stoner.controller;

import com.ws.stoner.model.ExportExcelVO;
import com.ws.stoner.model.ExportItemVO;
import com.ws.stoner.model.ExportPointVO;
import com.ws.stoner.model.ImportExcelVO;
import com.ws.stoner.model.dto.BriefItemDTO;
import com.ws.stoner.model.dto.BriefPointDTO;
import com.ws.stoner.model.dto.BriefTemplateDTO;
import com.ws.stoner.model.dto.BriefTriggerDTO;
import com.ws.stoner.service.ItemService;
import com.ws.stoner.service.PointService;
import com.ws.stoner.service.TemplateService;
import com.ws.stoner.service.TriggerService;
import com.ws.stoner.utils.ImportExcelUtil;
import com.ws.stoner.utils.ThresholdUtils;
import com.ws.stoner.utils.TypeConverter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongkf on 2018/1/26
 */
@Controller
@RequestMapping("")
public class ExcelController {

    public static final String templateNames[] = {
            "Windows_OS_Agent","Linux_OS_Agent","AIX_OS_Agent","Zabbix_Server",
            "Cisco_Switch_SNMP","Cisco_Firewall_SNMP","Cisco_AP_SNMP","Myqsl-Basic",
            "Oracle-Basic","SQL Server-Basic","Template Virt VMware Hypervisor","Template Virt VMware Guest",
            "Template Virt VMware","Hardware-IBM-3850X"};

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TriggerService triggerService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private PointService pointService;


    @RequestMapping(value = {"/excel", ""})
    public String excel() {
        return "excel";
    }

    /**
     * 描述：通过传统方式form表单提交方式导入excel文件  
     * @param request
     * @throws Exception
     */
    @RequestMapping(value="upload",method={RequestMethod.GET, RequestMethod.POST})
    public  String  uploadExcel(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("通过传统方式form表单提交方式导入excel文件！");

        InputStream in =null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("upfile");
        if(file.isEmpty()){
            throw new Exception("文件不存在！");
        }
        in = file.getInputStream();
        listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());
        in.close();

        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出  
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            ImportExcelVO vo = new ImportExcelVO();
            vo.setCode(String.valueOf(lo.get(0)));
            vo.setName(String.valueOf(lo.get(1)));
            vo.setDate(String.valueOf(lo.get(2)));
            vo.setMoney(String.valueOf(lo.get(3)));

            System.out.println("打印信息–>机构:"+vo.getCode()+"  名称："+vo.getName()+"   时间："+vo.getDate()+"   资产："+vo.getMoney());
        }
        return "excel";
    }

    /**
     * 描述：通过 jquery.form.js 插件提供的ajax方式上传文件 
     * @param request
     * @param response
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value="ajaxUpload",method={RequestMethod.GET,RequestMethod.POST})
    public  void  ajaxUploadExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        System.out.println("通过 jquery.form.js 提供的ajax方式上传文件！");

        InputStream in =null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("upfile");
        if(file.isEmpty()){
            throw new Exception("文件不存在！");
        }

        in = file.getInputStream();
        listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());

        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出  
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            ImportExcelVO vo = new ImportExcelVO();
            vo.setCode(String.valueOf(lo.get(0)));
            vo.setName(String.valueOf(lo.get(1)));
            vo.setDate(String.valueOf(lo.get(2)));
            vo.setMoney(String.valueOf(lo.get(3)));

            System.out.println("打印信息–>机构:"+vo.getCode()+"  名称："+vo.getName()+"   时间："+vo.getDate()+"   资产："+vo.getMoney());
        }

        PrintWriter out = null;
        response.setCharacterEncoding("utf-8");  //防止ajax接受到的中文信息乱码  
        out = response.getWriter();
        out.print("文件导入成功！");
        out.flush();
        out.close();
    }

    @ResponseBody
    @RequestMapping(value="/export")
    public  void  export(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //获取组装数据源
        List<BriefTemplateDTO> templateDTOS = templateService.getTempByName(templateNames);
        List<String> pointIds = new ArrayList<>();
        for(BriefTemplateDTO templateDTO : templateDTOS) {
            List<BriefPointDTO> pointDTOS = templateDTO.getPoints();
            for(BriefPointDTO pointDTO : pointDTOS) {
                pointIds.add(pointDTO.getPointId());
            }
        }
        //组装包含触发器的 itemids
        List<BriefItemDTO> withTriggersItemDTOS = itemService.getItemsWithTriggersByPointIds(pointIds);
        List<String> triItemIds = new ArrayList<>();
        for(BriefItemDTO itemDTO : withTriggersItemDTOS) {
            triItemIds.add(itemDTO.getItemId());
        }
        //扩展 point 到 itemDTOS
        List<BriefPointDTO> pointDTOS = pointService.getPointDTOSByPointIds(pointIds);
        List<BriefTriggerDTO> triggerDTOS = triggerService.getTriggersByItemIds(triItemIds);
        List<ExportExcelVO> excelVOS = new ArrayList<>();
        for(BriefTemplateDTO templateDTO : templateDTOS) {
            ExportExcelVO excelVO = new ExportExcelVO();
            excelVO.setTempName(templateDTO.getName());
            List<BriefPointDTO> tempPointDTOS = templateDTO.getPoints();
            List<ExportPointVO> pointVOS = new ArrayList<>();
            for(BriefPointDTO tempPointDTO : tempPointDTOS) {
                for(BriefPointDTO pointDTO: pointDTOS ) {
                    if(tempPointDTO.getPointId().equals(pointDTO.getPointId())) {
                        ExportPointVO pointVO = new ExportPointVO();
                        pointVO.setPointName(pointDTO.getName());
                        List<BriefItemDTO> itemDTOS = pointDTO.getItems();
                        List<ExportItemVO> itemVOS = new ArrayList<>();
                        for(BriefItemDTO itemDTO : itemDTOS) {
                            ExportItemVO itemVO = new ExportItemVO();
                            itemVO.setItemName(itemDTO.getName());
                            itemVO.setKey(itemDTO.getKey());
                            itemVO.setItemType(itemDTO.getType());
                            itemVO.setUnit(itemDTO.getUnits());
                            itemVO.setDescription(itemDTO.getDescription());
                            if(triItemIds.contains(itemDTO.getItemId())) {
                                itemVO.setAlter(true);
                                //阀值赋值：high,warning
                                //循环triggerDTOS，筛选出属于该itemDTO的触发器，取List<String> expression,priority  ,
                                for(BriefTriggerDTO triggerDTO : triggerDTOS) {
                                    String expression = triggerDTO.getExpression();
                                    String itemIdInfo = triggerDTO.getItems().get(0).getItemId();
                                    if(itemIdInfo.equals(itemDTO.getItemId())) {
                                        if(triggerDTO.getPriority() == 2) {
                                            // priority为2:警告阀值取expression的逻辑比较符号后面数据；
                                            itemVO.setWarning(ThresholdUtils.getThresholdValueSymbol(expression));
                                        }else if(triggerDTO.getPriority() == 4) {
                                            // priority为4:严重阀值取expression的逻辑比较符号后面数据；
                                            itemVO.setHigh(ThresholdUtils.getThresholdValueSymbol(expression));
                                        }
                                    }
                                }
                            }else {
                                itemVO.setAlter(false);
                                itemVO.setWarning("");
                                itemVO.setHigh("");
                            }
                            itemVOS.add(itemVO);
                        }
                        pointVO.setItems(itemVOS);
                        pointVOS.add(pointVO);
                    }
                }
            }
            excelVO.setPoints(pointVOS);
            excelVOS.add(excelVO);
        }

        // 创建基于stream的工作薄对象
        SXSSFWorkbook wkb = new SXSSFWorkbook();
        //字体
        Font font = wkb.createFont();
        font.setFontName("仿宋_GB2312");
        font.setFontHeightInPoints((short) 12);
        //设置样式
        CellStyle style = wkb.createCellStyle();
        //对其方式
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        style.setAlignment(HorizontalAlignment.CENTER);// 水平
        for(ExportExcelVO excelVO : excelVOS) {
            int rowIndex = 0;
            //创建工作簿
            Sheet sh = wkb.createSheet(excelVO.getTempName());
            sh.setDefaultRowHeight((short)500);
            sh.setDefaultColumnWidth(20);
            /*sh.autoSizeColumn(1);
            sh.autoSizeColumn(1, true);*/

            //拟定标题
            Row headline = sh.createRow(rowIndex++);
            //设置首行颜色
            CellStyle rowStyle = new XSSFCellStyle(new StylesTable());
            rowStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
            //headline.setRowStyle(rowStyle);
            Cell headText = headline.createCell(0);
            headText.setCellStyle(rowStyle);
            headText.setCellValue(excelVO.getTempName());

            //遍历 point 创建监控指标列表
            List<ExportPointVO> pointVOS = excelVO.getPoints();
            for(ExportPointVO pointVO : pointVOS) {
                Row pointline = sh.createRow(rowIndex++);
                //设置行颜色
                Cell point = pointline.createCell(0);
                point.setCellValue(pointVO.getPointName());
                List<ExportItemVO> itemVOS = pointVO.getItems();
                //item 表头
                Row itemHeadLine = sh.createRow(rowIndex++);

                Cell itemHead0 = itemHeadLine.createCell(0);
                itemHead0.setCellValue("指标名称");
                Cell itemHead1 = itemHeadLine.createCell(1);
                itemHead1.setCellValue("key值");
                Cell itemHead2 = itemHeadLine.createCell(2);
                itemHead2.setCellValue("监控方式");
                Cell itemHead3 = itemHeadLine.createCell(3);
                itemHead3.setCellValue("单位");
                Cell itemHead4 = itemHeadLine.createCell(4);
                itemHead4.setCellValue("告警阀值");
                Cell itemHead5 = itemHeadLine.createCell(5);
                itemHead5.setCellValue("严重阀值");
                Cell itemHead6 = itemHeadLine.createCell(6);
                itemHead6.setCellValue("是否告警");
                Cell itemHead7 = itemHeadLine.createCell(7);
                itemHead7.setCellValue("描述");

                for(ExportItemVO itemVO : itemVOS) {
                    Row itemline = sh.createRow(rowIndex++);

                    Cell item0 = itemline.createCell(0);
                    item0.setCellValue(itemVO.getItemName());
                    Cell item1 = itemline.createCell(1);
                    item1.setCellValue(itemVO.getKey());
                    Cell item2 = itemline.createCell(2);
                    item2.setCellValue(itemVO.getItemType() + "::" + TypeConverter.TransforItemType(itemVO.getItemType()));
                    Cell item3 = itemline.createCell(3);
                    item3.setCellValue(itemVO.getUnit());
                    Cell item4 = itemline.createCell(4);
                    item4.setCellValue(itemVO.getWarning());
                    Cell item5 = itemline.createCell(5);
                    item5.setCellValue(itemVO.getHigh());
                    Cell item6 = itemline.createCell(6);
                    item6.setCellValue(itemVO.isAlter() ? "是" : "");
                    Cell item7 = itemline.createCell(7);
                    item7.setCellValue(itemVO.getDescription());
                }
            }
        }
        //浏览器输出
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition","attachment;filename=temp.xlsx");
        response.setContentType("application/msexcel");
        wkb.write(output);
        output.close();
    }

}
