package com.ws.stoner.controller;

import com.ws.stoner.model.*;
import com.ws.stoner.model.dto.*;
import com.ws.stoner.service.*;
import com.ws.stoner.utils.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND;

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
    private ItemService itemService;

    @Autowired
    private PointService pointService;

    @Autowired
    private DDLService ddlService;

    @Autowired
    private ItemProtoService itemProtoService;

    @Autowired
    private TriggerService triggerService;

    @Autowired
    private TriggerProService triggerProService;

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
     * 导出 excel 模板
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="/export")
    public  void  export(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //获取组装数据源
        List<BriefTemplateDTO> templateDTOS = templateService.getTempByName(templateNames);
        List<String> hostIds = new ArrayList<>();
        for(BriefTemplateDTO templateDTO : templateDTOS) {
            hostIds.add(templateDTO.getTemplateId());
        }
        List<BriefItemDTO> itemDTOS = itemService.getItemsByHostIds(hostIds);
        List<BriefTriggerDTO> triggerDTOS = triggerService.getTriggersByHostIds(hostIds);
        List<BriefItemProtoDTO> itemProtoDTOS = itemProtoService.getItemProtoDTOSByHostIds(hostIds);
        //非原型监控项 DTO => VO
        List<ExcelItemVO> itemVOS = new ArrayList<>();
        for(BriefItemDTO itemDTO : itemDTOS) {
            ExcelItemVO itemVO = new ExcelItemVO();
            itemVO.setDDL(false);
            for(BriefTemplateDTO templateDTO : templateDTOS) {
                if(itemDTO.getHostId().equals(templateDTO.getTemplateId())) {
                    itemVO.setTempName(templateDTO.getName());
                }
            }
            itemVO.setPointName(itemDTO.getPoints()!=null&&itemDTO.getPoints().size()!=0 ? itemDTO.getPoints().get(0).getName() : "未分类");
            itemVO.setItemName(itemDTO.getName());
            itemVO.setUnit(itemDTO.getUnits());
            itemVO.setKey(itemDTO.getKey());
            itemVO.setSNMPOid(itemDTO.getSnmpOid());
            itemVO.setIPMISensor(itemDTO.getIpmiSensor());
            itemVO.setValueType(StatusConverter.getValueTypeString(itemDTO.getValueType()));
            itemVO.setDelay(itemDTO.getDelay()+"秒");
            itemVO.setHistory(itemDTO.getHistory()+"天");
            itemVO.setTrends(String.valueOf(itemDTO.getTrends()));
            itemVO.setItemType(TypeConverter.TransforItemType(itemDTO.getType()));
            itemVO.setDescription(itemDTO.getDescription());
            if(itemDTO.getTriggers() != null && itemDTO.getTriggers().size() != 0) {
                itemVO.setAlert(true);
            }else {
                itemVO.setAlert(false);
            }
            itemVOS.add(itemVO);
        }
        /*//原型item DTO => VO
        for(BriefItemProtoDTO itemDTO : itemProtoDTOS) {
            ExcelItemVO itemVO = new ExcelItemVO();
            itemVO.setDDL(true);
            for(BriefTemplateDTO templateDTO : templateDTOS) {
                if(itemDTO.getHostId().equals(templateDTO.getTemplateId())) {
                    itemVO.setTempName(templateDTO.getName());
                }
            }
            if(itemDTO.getPoints() != null && itemDTO.getPoints().size() != 0) {
                itemVO.setPointName(itemDTO.getPoints().get(0).getName());
            }else if(itemDTO.getPointProtos() != null && itemDTO.getPointProtos().size() != 0) {
                itemVO.setPointName(itemDTO.getPointProtos().get(0).getName());
            }else {
                itemVO.setPointName("未分类");
            }
            itemVO.setItemName(itemDTO.getName());
            itemVO.setUnit(itemDTO.getUnits());
            itemVO.setKey(itemDTO.getKey());
            itemVO.setDelay(itemDTO.getDelay()+"秒");
            itemVO.setHistory(itemDTO.getHistory()+"天");
            itemVO.setItemType(TypeConverter.TransforItemType(itemDTO.getType()));
            itemVO.setDescription(itemDTO.getDescription());
            if(itemDTO.getTriggers() != null && itemDTO.getTriggers().size() != 0) {
                itemVO.setAlert(true);
            }else {
                itemVO.setAlert(false);
            }
            itemVOS.add(itemVO);
        }*/
        ListUtils.sort(itemVOS,true,"tempName","pointName");
        //定义表头
        String[] thead = {"指标名称","key值","监控方式","SNMP-OID","IPMI传感器","信息类型","单位","刷新时间","历史保留时间","趋势周期","是否告警","是否DDL","描述"};
        // 创建基于stream的工作薄对象
        XSSFWorkbook wkb = new XSSFWorkbook();
        //字体
        Font font = wkb.createFont();
        font.setFontName("仿宋_GB2312");
        font.setFontHeightInPoints((short) 12);
        //设置样式
        XSSFCellStyle style = wkb.createCellStyle();
        //自动换行
        //style.setWrapText(true);
        //对其方式
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        style.setAlignment(HorizontalAlignment.CENTER);// 水平
        String tName = "";
        String pName = "";
        Sheet sh = null;
        int rowIndex = 0;
        for(ExcelItemVO itemVO : itemVOS) {
            if(!itemVO.getTempName().equals(tName)) {
                //不同模板下
                tName = itemVO.getTempName();
                rowIndex = 0;
                //创建工作簿
                sh = wkb.createSheet(itemVO.getTempName());
                sh.setDefaultRowHeight((short)500);
                sh.setDefaultColumnWidth(15);
                //“指标名称”、“key值”、“描述” 宽度单独设定
                sh.setColumnWidth(0,30*256);
                sh.setColumnWidth(1,30*256);
                sh.setColumnWidth(2,20*256);
                sh.setColumnWidth(3,30*256);
                sh.setColumnWidth(thead.length-1,100*256);
                //拟定标题
                Row headline = sh.createRow(rowIndex++);
                //设置首行样式
                Cell headText = headline.createCell(0);
                //颜色
                CellStyle headStyle = wkb.createCellStyle();
                headStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                headStyle.setFillPattern(SOLID_FOREGROUND);
                headStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
                headStyle.setAlignment(HorizontalAlignment.CENTER);// 水平
                headText.setCellStyle(style);
                headText.setCellValue("模板名称：" + itemVO.getTempName());

            }
            //绘制point
            if(!itemVO.getPointName().equals(pName)) {
                pName = itemVO.getPointName();
                sh.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, thead.length-1));
                Row pointline = sh.createRow(rowIndex++);
                //创建point
                Cell point = pointline.createCell(0);
                CellStyle pointStyle = wkb.createCellStyle();
                pointStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
                pointStyle.setFillPattern(SOLID_FOREGROUND);
                pointStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
                pointStyle.setAlignment(HorizontalAlignment.LEFT);// 水平
                point.setCellStyle(pointStyle);
                point.setCellValue("检测点：" + itemVO.getPointName());
                //item 表头
                Row itemHeadLine = sh.createRow(rowIndex++);
                for(int i=0;i<=thead.length-1;i++) {
                    Cell itemHead = itemHeadLine.createCell(i);
                    style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    style.setFillPattern(SOLID_FOREGROUND);
                    style.setBorderBottom(BorderStyle.THIN); //下边框
                    style.setBorderLeft(BorderStyle.THIN);//左边框
                    style.setBorderTop(BorderStyle.THIN);//上边框
                    style.setBorderRight(BorderStyle.THIN);//右边框
                    itemHead.setCellStyle(style);
                    itemHead.setCellValue(thead[i]);
                }
            }
            //item赋值
            //"指标名称","key值","监控方式","SNMP-OID","IPMI传感器","信息类型","单位","刷新时间","历史保留时间","趋势周期","是否告警","是否DDL","描述"
            Row itemline = sh.createRow(rowIndex++);
            Cell item0 = itemline.createCell(0);
            item0.setCellValue(itemVO.getItemName());
            Cell item1 = itemline.createCell(1);
            item1.setCellValue(itemVO.getKey());
            Cell item2 = itemline.createCell(2);
            item2.setCellValue(itemVO.getItemType());
            Cell item3 = itemline.createCell(3);
            item3.setCellValue(itemVO.getSNMPOid());
            Cell item4 = itemline.createCell(4);
            item4.setCellValue(itemVO.getIPMISensor());
            Cell item5 = itemline.createCell(5);
            item5.setCellValue(itemVO.getValueType());
            Cell item6 = itemline.createCell(6);
            item6.setCellValue(itemVO.getUnit());
            Cell item7 = itemline.createCell(7);
            item7.setCellValue(itemVO.getDelay());
            Cell item8 = itemline.createCell(8);
            item8.setCellValue(itemVO.getHistory());
            Cell item9 = itemline.createCell(9);
            item9.setCellValue(itemVO.getTrends());

            Cell item10 = itemline.createCell(10);
            item10.setCellValue(itemVO.isAlert() ? "是" : "");
            Cell item11 = itemline.createCell(11);
            item11.setCellValue(itemVO.isDDL() ? "DDL" : "");
            Cell item12 = itemline.createCell(12);
            item12.setCellValue(itemVO.getDescription());

        }

        //浏览器输出
        OutputStream output = response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition","attachment;filename=temp.xlsx");
        response.setContentType("application/msexcel");
        wkb.write(output);
        output.close();

    }

    /**
     * 导出 excel 模板 新
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value="/export1")
    public  void  export1(HttpServletRequest request,HttpServletResponse response) throws Exception{
        //获取组装数据源
        List<BriefTemplateDTO> templateDTOS = templateService.getTempByName(templateNames);
        List<String> pointIds =  new ArrayList<>();
        List<String> ruleIds = new ArrayList<>();
        List<String> hostIds = new ArrayList<>();
        for(BriefTemplateDTO templateDTO : templateDTOS) {
            hostIds.add(templateDTO.getTemplateId());
            List<BriefPointDTO> pointDTOS = templateDTO.getPoints();
            for(BriefPointDTO pointDTO : pointDTOS) {
                pointIds.add(pointDTO.getPointId());
            }
            List<BriefDDLDTO> ddldtos = templateDTO.getDdls();
            for(BriefDDLDTO ddldto : ddldtos) {
                ruleIds.add(ddldto.getItemId());
            }
        }
        List<BriefTriggerDTO> triggerDTOS = triggerService.getTriggersByHostIds(hostIds);
        List<BriefPointDTO> pointDTOS = pointService.getPointDTOSByPointIds(pointIds);
        List<BriefDDLDTO> ddldtos = ddlService.getDDLDTOSByRuleIds(ruleIds);
        List<BriefTriggerProDTO> triggerProDTOS = triggerProService.getTriProDTOSByHostIds(hostIds);

        //构造 templateVOS
        List<ExcelTemplateVO> excelTemplateVOS = new ArrayList<>();
        for(BriefTemplateDTO templateDTO : templateDTOS) {
            ExcelTemplateVO excelTemplateVO = new ExcelTemplateVO();
            //构造pointVOS select itemVOS
            excelTemplateVO.setName(templateDTO.getName());

            List<BriefPointDTO> tempPointDTOS = templateDTO.getPoints();
            List<ExcelPointVO> pointVOS = new ArrayList<>();
            for(BriefPointDTO tempPointDTO : tempPointDTOS) {
                ExcelPointVO pointVO = new ExcelPointVO();
                List<ExcelItemVO> itemVOS = new ArrayList<>();
                for(BriefPointDTO pointDTO : pointDTOS) {
                    if(pointDTO.getPointId().equals(tempPointDTO.getPointId())) {
                        List<BriefItemDTO> itemDTOS = pointDTO.getItems();
                        for(BriefItemDTO itemDTO : itemDTOS) {
                            ExcelItemVO itemVO = new ExcelItemVO();
                            //赋值构造itemVO
                            itemVO.setItemName(itemDTO.getName());
                            itemVO.setKey(itemDTO.getKey());
                            itemVO.setItemType(TypeConverter.TransforItemType(itemDTO.getType()));
                            itemVO.setSNMPOid(itemDTO.getSnmpOid());
                            itemVO.setIPMISensor(itemDTO.getIpmiSensor());
                            itemVO.setValueType(StatusConverter.getValueTypeString(itemDTO.getValueType()));
                            itemVO.setUnit(itemDTO.getUnits());
                            itemVO.setDelay(itemDTO.getDelay()+"秒");
                            itemVO.setHistory(itemDTO.getHistory()+"天");
                            itemVO.setTrends(String.valueOf(itemDTO.getTrends()));
                            itemVO.setDescription(itemDTO.getDescription());

                            itemVOS.add(itemVO);
                        }

                    }
                }
                pointVO.setName(tempPointDTO.getName());
                pointVO.setItems(itemVOS);

                //添加到pointVOS
                pointVOS.add(pointVO);
            }
            //赋值points属性
            excelTemplateVO.setPointVOS(pointVOS);
            //构造 ruleVOS
            List<ExcelDDLVO> ddlvos = new ArrayList<>();
            //tempTriggerProIds 用于搜集原型触发器的ID
            List<String > tempTriggerProIds = new ArrayList<>();
            for(BriefDDLDTO ddldto : ddldtos) {
                if(ddldto.getHostId().equals(templateDTO.getTemplateId())) {
                    //搜集原型触发器ID
                    for(BriefTriggerProDTO triggerProDTO : ddldto.getTriggers()) {
                        tempTriggerProIds.add(triggerProDTO.getTriggerId());
                    }
                    ExcelDDLVO ddlvo = new ExcelDDLVO();
                    List<BriefItemProtoDTO> itemProtoDTOS = ddldto.getItems();
                    List<ExcelItemVO> itemVOS = new ArrayList<>();
                    for(BriefItemProtoDTO itemProtoDTO : itemProtoDTOS) {
                        ExcelItemVO itemVO = new ExcelItemVO();
                        //构造 itemVO 原型
                        itemVO.setItemName(itemProtoDTO.getName());
                        itemVO.setItemType(TypeConverter.TransforItemType(itemProtoDTO.getType()));
                        itemVO.setKey(itemProtoDTO.getKey());
                        itemVO.setSNMPOid(itemProtoDTO.getSnmpOid());
                        itemVO.setIPMISensor(itemProtoDTO.getIpmiSensor());
                        itemVO.setValueType(StatusConverter.getValueTypeString(itemProtoDTO.getValueType()));
                        itemVO.setUnit(itemProtoDTO.getUnits());
                        itemVO.setDelay(itemProtoDTO.getDelay()+"秒");
                        itemVO.setHistory(itemProtoDTO.getHistory()+"天");
                        itemVO.setTrends(String.valueOf(itemProtoDTO.getTrends()));
                        itemVO.setDescription(itemProtoDTO.getDescription());

                        itemVOS.add(itemVO);
                    }
                    //赋值

                    ddlvo.setDdlName(ddldto.getName());
                    ddlvo.setKey(ddldto.getKey_());
                    ddlvo.setItemType(TypeConverter.TransforItemType(String.valueOf(ddldto.getType())));
                    ddlvo.setSNMPOID(ddldto.getSnmpOid());
                    ddlvo.setIpmiSensor(ddldto.getIpmiSensor());
                    ddlvo.setDelay(ddldto.getDelay()+"秒");
                    ddlvo.setLifetime(ddldto.getLifetime()+"天");
                    ddlvo.setDescription(ddldto.getDescription());
                    ddlvo.setItems(itemVOS);

                    ddlvos.add(ddlvo);
                }
            }
            //赋值ddlvos属性
            excelTemplateVO.setDdls(ddlvos);
            //触发器列表
            List<ExcelTriggerVO> triggerVOS = new ArrayList<>();
            List<BriefTriggerDTO> tempTriggers = templateDTO.getTriggers();
            for(BriefTriggerDTO tempTrigger : tempTriggers) {
                for(BriefTriggerDTO triggerDTO : triggerDTOS) {
                    if(triggerDTO.getTriggerId().equals(tempTrigger.getTriggerId())) {
                        ExcelTriggerVO triggerVO = new ExcelTriggerVO();
                        triggerVO.setName(triggerDTO.getName());
                        triggerVO.setPriority(StatusConverter.getStatusTextByTriggerPriority(triggerDTO.getPriority()));
                        triggerVO.setExpression(triggerDTO.getExpression());
                        triggerVO.setManualClose(triggerDTO.getManualClose() == 1 ? "允许" : " 不允许");
                        triggerVO.setComments(triggerDTO.getComments());

                        triggerVOS.add(triggerVO);
                    }
                }
            }
            //赋值 triggers 属性
            excelTemplateVO.setTriggers(triggerVOS);
            //触发器原型列表
            List<ExcelTriggerVO> triggerProVOS = new ArrayList<>();
            for(BriefTriggerProDTO triggerProDTO : triggerProDTOS) {
                if(tempTriggerProIds.contains(triggerProDTO.getTriggerId())) {
                    //如果包含本模板原型触发器
                    ExcelTriggerVO triggerVO = new ExcelTriggerVO();
                    triggerVO.setName(triggerProDTO.getDescription());
                    triggerVO.setPriority(StatusConverter.getStatusTextByTriggerPriority(triggerProDTO.getPriority()));
                    triggerVO.setExpression(triggerProDTO.getExpression());
                    triggerVO.setManualClose(triggerProDTO.getManualClose() == 1 ? "允许" : " 不允许");
                    triggerVO.setComments(triggerProDTO.getComments());

                    triggerProVOS.add(triggerVO);
                }
            }
            //赋值 triggers 属性
            excelTemplateVO.setTriggerProS(triggerProVOS);
            excelTemplateVOS.add(excelTemplateVO);
        }
        //定义表头
        String[] itemCol = {"指标名称","key值","监控方式","SNMP-OID","IPMI传感器","信息类型","单位","刷新时间","历史保留时间","趋势周期","描述"};
        String[] ddlCol = {"规则名称","key值","监控方式","SNMP-OID","IPMI传感器","刷新时间","保留失去的资源时间","描述"};
        String[] triggerCol = {"触发器名称","级别","表达式","允许手动关闭","描述"};

        // 创建基于stream的工作薄对象
        XSSFWorkbook wkb = new XSSFWorkbook();
        //字体
        Font font = wkb.createFont();
        font.setFontName("仿宋_GB2312");
        font.setFontHeightInPoints((short) 12);
        //设置样式
        XSSFCellStyle style = wkb.createCellStyle();
        //自动换行
        //style.setWrapText(true);
        //对其方式
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
        style.setAlignment(HorizontalAlignment.CENTER);// 水平

        Sheet sh;
        int rowIndex ;
        for(ExcelTemplateVO templateVO : excelTemplateVOS) {
            rowIndex = 0;
            //创建工作簿
            sh = wkb.createSheet(templateVO.getName());
            sh.setDefaultRowHeight((short)500);
            sh.setDefaultColumnWidth(15);
            //“指标名称”、“key值”、“描述” 宽度单独设定
            sh.setColumnWidth(0,30*256);
            sh.setColumnWidth(1,30*256);
            sh.setColumnWidth(2,40*256);
            sh.setColumnWidth(3,30*256);
            sh.setColumnWidth(itemCol.length-1,100*256);
            //拟定标题
            Row headline = sh.createRow(rowIndex++);
            //设置首行样式
            Cell headText = headline.createCell(0);
            //颜色
            CellStyle headStyle = wkb.createCellStyle();
            headStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            headStyle.setFillPattern(SOLID_FOREGROUND);
            headStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
            headStyle.setAlignment(HorizontalAlignment.CENTER);// 水平
            headText.setCellStyle(style);
            headText.setCellValue("模板名称：" + templateVO.getName());
            //空一行
            sh.createRow(rowIndex++);
            List<ExcelPointVO> pointVOS = templateVO.getPointVOS();
            for(ExcelPointVO pointVO : pointVOS) {
                if(pointVO.getItems() == null || pointVO.getItems().size() == 0) {
                    continue;
                }
                //point表头
                sh.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, itemCol.length-1));
                Row pointline = sh.createRow(rowIndex++);
                Cell point = pointline.createCell(0);
                CellStyle pointStyle = wkb.createCellStyle();
                pointStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
                pointStyle.setFillPattern(SOLID_FOREGROUND);
                pointStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
                pointStyle.setAlignment(HorizontalAlignment.LEFT);// 水平
                point.setCellStyle(pointStyle);
                point.setCellValue("检测点：" + pointVO.getName());
                List<ExcelItemVO> itemVOS = pointVO.getItems();
                //item 表头
                Row itemHeadLine = sh.createRow(rowIndex++);
                ExportExcelUtil.createExcelHead(itemHeadLine,itemCol,style);
                for(ExcelItemVO itemVO : itemVOS) {
                    //item赋值
                    //"指标名称","key值","监控方式","SNMP-OID","IPMI传感器","信息类型","单位","刷新时间","历史保留时间","趋势周期","是否告警","描述"
                    Row itemline = sh.createRow(rowIndex++);
                    Cell item0 = itemline.createCell(0);
                    item0.setCellValue(itemVO.getItemName());
                    Cell item1 = itemline.createCell(1);
                    item1.setCellValue(itemVO.getKey());
                    Cell item2 = itemline.createCell(2);
                    item2.setCellValue(itemVO.getItemType());
                    Cell item3 = itemline.createCell(3);
                    item3.setCellValue(itemVO.getSNMPOid());
                    Cell item4 = itemline.createCell(4);
                    item4.setCellValue(itemVO.getIPMISensor());
                    Cell item5 = itemline.createCell(5);
                    item5.setCellValue(itemVO.getValueType());
                    Cell item6 = itemline.createCell(6);
                    item6.setCellValue(itemVO.getUnit());
                    Cell item7 = itemline.createCell(7);
                    item7.setCellValue(itemVO.getDelay());
                    Cell item8 = itemline.createCell(8);
                    item8.setCellValue(itemVO.getHistory());
                    Cell item9 = itemline.createCell(9);
                    item9.setCellValue(itemVO.getTrends());
                    Cell item10 = itemline.createCell(10);
                    item10.setCellValue(itemVO.getDescription());
                }
                //空一行
                sh.createRow(rowIndex++);
            }
            //触发器区域
            if(templateVO.getTriggers() != null && templateVO.getTriggers().size() != 0) {
                //触发器标题
                sh.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, triggerCol.length-1));
                Row triggerLine = sh.createRow(rowIndex++);
                Cell triggerText = triggerLine.createCell(0);
                CellStyle triggerStyle = wkb.createCellStyle();
                triggerStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                triggerStyle.setFillPattern(SOLID_FOREGROUND);
                triggerStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
                triggerStyle.setAlignment(HorizontalAlignment.LEFT);// 水平
                triggerText.setCellStyle(triggerStyle);
                triggerText.setCellValue("触发器列表");
                //触发器表头
                Row triggerHeadLine = sh.createRow(rowIndex++);
                ExportExcelUtil.createExcelHead(triggerHeadLine,triggerCol,style);
                List<ExcelTriggerVO> triggerVOS = templateVO.getTriggers();
                for(ExcelTriggerVO triggerVO : triggerVOS) {
                    //trigger赋值
                    //"触发器名称","级别","表达式","允许手动关闭","描述"
                    Row triggerRow = sh.createRow(rowIndex++);
                    Cell trigger0 = triggerRow.createCell(0);
                    trigger0.setCellValue(triggerVO.getName());
                    Cell trigger1 = triggerRow.createCell(1);
                    trigger1.setCellValue(triggerVO.getPriority());
                    Cell trigger2 = triggerRow.createCell(2);
                    trigger2.setCellValue(triggerVO.getExpression());
                    Cell trigger3 = triggerRow.createCell(3);
                    trigger3.setCellValue(triggerVO.getManualClose());
                    Cell trigger4 = triggerRow.createCell(4);
                    trigger4.setCellValue(triggerVO.getComments());
                }
                //空一行
                sh.createRow(rowIndex++);
            }
            if(templateVO.getDdls() != null && templateVO.getDdls().size() != 0) {
                //DDL区域
                sh.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, ddlCol.length-1));
                Row ddlLine = sh.createRow(rowIndex++);
                Cell ddlCOl = ddlLine.createCell(0);
                CellStyle ddlStyle = wkb.createCellStyle();
                ddlStyle.setFillForegroundColor(IndexedColors.PINK.getIndex());
                ddlStyle.setFillPattern(SOLID_FOREGROUND);
                ddlStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
                ddlStyle.setAlignment(HorizontalAlignment.LEFT);// 水平
                ddlCOl.setCellStyle(ddlStyle);
                ddlCOl.setCellValue("DDL内容");
                List<ExcelDDLVO> ddlvos = templateVO.getDdls();
                for(ExcelDDLVO ddlvo : ddlvos) {
                    sh.createRow(rowIndex++);
                    //ddl表头
                    Row ddlHeadLine = sh.createRow(rowIndex++);
                    ExportExcelUtil.createExcelHead(ddlHeadLine,ddlCol,style);
                    //ddl内容
                    //"规则名称","key值","监控方式","SNMP-OID","IPMI传感器","刷新时间","保留失去的资源时间","描述"
                    Row ddlRow = sh.createRow(rowIndex++);
                    Cell ddlCell0 = ddlRow.createCell(0);
                    ddlCell0.setCellValue(ddlvo.getDdlName());
                    Cell ddlCell1 = ddlRow.createCell(1);
                    ddlCell1.setCellValue(ddlvo.getKey());
                    Cell ddlCell2 = ddlRow.createCell(2);
                    ddlCell2.setCellValue(ddlvo.getItemType());
                    Cell ddlCell3 = ddlRow.createCell(3);
                    ddlCell3.setCellValue(ddlvo.getSNMPOID());
                    Cell ddlCell4 = ddlRow.createCell(4);
                    ddlCell4.setCellValue(ddlvo.getIpmiSensor());
                    Cell ddlCell5 = ddlRow.createCell(5);
                    ddlCell5.setCellValue(ddlvo.getDelay());
                    Cell ddlCell6 = ddlRow.createCell(6);
                    ddlCell6.setCellValue(ddlvo.getLifetime());
                    Cell ddlCell7 = ddlRow.createCell(7);
                    ddlCell7.setCellValue(ddlvo.getDescription());
                    sh.createRow(rowIndex++);

                    //监控项原型
                    sh.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,itemCol.length-1));
                    Row itemProLine = sh.createRow(rowIndex++);
                    Cell itemProCol = itemProLine.createCell(0);
                    CellStyle protoStyle = wkb.createCellStyle();
                    protoStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
                    protoStyle.setFillPattern(SOLID_FOREGROUND);
                    protoStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
                    protoStyle.setAlignment(HorizontalAlignment.LEFT);// 水平
                    itemProCol.setCellStyle(protoStyle);
                    itemProCol.setCellValue(ddlvo.getDdlName() + " 监控项原型");
                    //原型表头
                    Row itemProHeadLine = sh.createRow(rowIndex++);
                    ExportExcelUtil.createExcelHead(itemProHeadLine,itemCol,style);
                    List<ExcelItemVO> itemProVOS = ddlvo.getItems();
                    for(ExcelItemVO itemProVO : itemProVOS) {
                        //item赋值
                        //"指标名称","key值","监控方式","SNMP-OID","IPMI传感器","信息类型","单位","刷新时间","历史保留时间","趋势周期","是否告警","描述"
                        Row itemline = sh.createRow(rowIndex++);
                        Cell item0 = itemline.createCell(0);
                        item0.setCellValue(itemProVO.getItemName());
                        Cell item1 = itemline.createCell(1);
                        item1.setCellValue(itemProVO.getKey());
                        Cell item2 = itemline.createCell(2);
                        item2.setCellValue(itemProVO.getItemType());
                        Cell item3 = itemline.createCell(3);
                        item3.setCellValue(itemProVO.getSNMPOid());
                        Cell item4 = itemline.createCell(4);
                        item4.setCellValue(itemProVO.getIPMISensor());
                        Cell item5 = itemline.createCell(5);
                        item5.setCellValue(itemProVO.getValueType());
                        Cell item6 = itemline.createCell(6);
                        item6.setCellValue(itemProVO.getUnit());
                        Cell item7 = itemline.createCell(7);
                        item7.setCellValue(itemProVO.getDelay());
                        Cell item8 = itemline.createCell(8);
                        item8.setCellValue(itemProVO.getHistory());
                        Cell item9 = itemline.createCell(9);
                        item9.setCellValue(itemProVO.getTrends());
                        Cell item10 = itemline.createCell(10);
                        item10.setCellValue(itemProVO.getDescription());
                    }
                    sh.createRow(rowIndex++);
                }
            }
            sh.createRow(rowIndex++);
            if(templateVO.getTriggerProS() != null && templateVO.getTriggerProS().size() != 0) {
                //原型触发器区域
                //原型触发器标题
                sh.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, triggerCol.length-1));
                Row triggerProLine = sh.createRow(rowIndex++);
                Cell triggerProText = triggerProLine.createCell(0);
                CellStyle triProStyle = wkb.createCellStyle();
                triProStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                triProStyle.setFillPattern(SOLID_FOREGROUND);
                triProStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
                triProStyle.setAlignment(HorizontalAlignment.LEFT);// 水平
                triggerProText.setCellStyle(triProStyle);
                triggerProText.setCellValue("原型触发器列表");
                //触发器表头
                Row triggerProHeadLine = sh.createRow(rowIndex++);
                ExportExcelUtil.createExcelHead(triggerProHeadLine,triggerCol,style);
                List<ExcelTriggerVO> triggerProVOS = templateVO.getTriggerProS();
                for(ExcelTriggerVO triggerVO : triggerProVOS) {
                    //trigger赋值
                    //"触发器名称","级别","表达式","允许手动关闭","描述"
                    Row triggerRow = sh.createRow(rowIndex++);
                    Cell trigger0 = triggerRow.createCell(0);
                    trigger0.setCellValue(triggerVO.getName());
                    Cell trigger1 = triggerRow.createCell(1);
                    trigger1.setCellValue(triggerVO.getPriority());
                    Cell trigger2 = triggerRow.createCell(2);
                    trigger2.setCellValue(triggerVO.getExpression());
                    Cell trigger3 = triggerRow.createCell(3);
                    trigger3.setCellValue(triggerVO.getManualClose());
                    Cell trigger4 = triggerRow.createCell(4);
                    trigger4.setCellValue(triggerVO.getComments());
                }
                //空一行
                sh.createRow(rowIndex++);
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
