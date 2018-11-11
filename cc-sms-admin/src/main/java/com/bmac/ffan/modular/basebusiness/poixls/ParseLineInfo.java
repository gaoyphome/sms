package com.bmac.ffan.modular.basebusiness.poixls;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bmac.ffan.common.persistence.model.*;
import com.bmac.ffan.core.util.ByteUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.modular.basebusiness.util.StringUtil;

/**
 * wangshengjun
 * 2017,12,6
 * 解析线路文件
 */
public class ParseLineInfo {
    private int lineType;//线路类型0：折返型线路， 1：环型线路
    private Sheet sheet_lineinfo;
    private Sheet sheet_line_up;
    private Sheet sheet_line_down;
    private CcLineInfo ccLineInfo = new CcLineInfo();
    private CcBusCompany busCompany = new CcBusCompany();
    private CcBusCompany subBusCompany = new CcBusCompany();
    private List<CcStationInfo> ccStationInfoList_up = new ArrayList<CcStationInfo>();
    private List<CcStationInfo> ccStationInfoList_down = new ArrayList<CcStationInfo>();
    private List<CcPriceInfo> ccPriceInfoList_up = new ArrayList<CcPriceInfo>();
    private List<CcPriceInfo> ccPriceInfoList_down = new ArrayList<CcPriceInfo>();
    private CcMileageInfo ccMileageInfo_up = new CcMileageInfo();
    private CcMileageInfo ccMileageInfo_down = new CcMileageInfo();

    public static void main(String[] args) {
        String str = "恋日名12AA城.";
        ParseLineInfo parseLineInfo = new ParseLineInfo(0);
        System.out.println(parseLineInfo.checkLineName(str));
        /*try {
            ParseLineInfo parseLineInfo = new ParseLineInfo(0);
            InputStream inp = new FileInputStream("C:\\Users\\vicya\\Desktop\\TMP\\2222.xls");
            parseLineInfo.parseXLS(inp);
            System.out.println(parseLineInfo.getCcLineInfo());
            parseLineInfo.getCcStationInfoList_up().stream().forEach(e->{
                System.out.println("stationUP--->"+e);
            });
            parseLineInfo.getCcStationInfoList_down().stream().forEach(e->{
                System.out.println("stationDW--->"+e);
            });
            parseLineInfo.getCcPriceInfoList_up().stream().forEach(e -> {
                System.out.println("priceUP--->"+e.getNum() + "  " + e.getPrice());
            });

            parseLineInfo.getCcPriceInfoList_down().stream().forEach(e -> {
                System.out.println("priceDW--->"+e.getNum() + "  " + e.getPrice());
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseLineInfoException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
    }

    public ParseLineInfo() {}
    public ParseLineInfo(int lineType) {
        this.lineType = lineType;
    }

    public void parseXLS(InputStream inputStream) throws ParseLineInfoException, UnsupportedEncodingException {
        if (parseSheet(inputStream)) {
            parseLineinfo();
            parseStationUp();
            if(lineType == 0) {
                parseStationDown();
            }
        }
    }

    private void parseStationUp() throws ParseLineInfoException, UnsupportedEncodingException {
        parseStation(this.sheet_line_up, true);
    }

    private void parseStationDown() throws ParseLineInfoException, UnsupportedEncodingException {
        parseStation(this.sheet_line_down, false);
    }

    /**
     *
     * @param sheet
     * @param flag true:上行数据 false:下行数据
     * @throws ParseLineInfoException
     */
    private void parseStation(Sheet sheet, boolean flag) throws ParseLineInfoException, UnsupportedEncodingException {
        int rows = sheet.getPhysicalNumberOfRows();
        String flagName = (flag)?"上行":"下行";
        if (rows <= 1) {
            throw new ParseLineInfoException(flagName+"数据小于等于1行");
        }
        Row row_head = sheet.getRow(0);
        if (row_head.getPhysicalNumberOfCells() < LineInfoFileConstant.STATION_HEAD_CELLS) {
            throw new ParseLineInfoException(flagName+"SHEET,第一行（标题行）列数不足" + LineInfoFileConstant.STATION_HEAD_CELLS);
        }

        Cell cell = row_head.getCell(0);
        if (!cell.toString().startsWith(LineInfoFileConstant.STATION_HEAD_C1)
                && !cell.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.STATION_HEAD_C1))) {
            throw new ParseLineInfoException(flagName+"SHEET,第一列不是" + LineInfoFileConstant.STATION_HEAD_C1);
        }
        cell = row_head.getCell(1);
        if (!cell.toString().startsWith(LineInfoFileConstant.STATION_HEAD_C2)
                && !cell.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.STATION_HEAD_C2))) {
            throw new ParseLineInfoException(flagName+"SHEET,第二列不是" + LineInfoFileConstant.STATION_HEAD_C2);
        }
        cell = row_head.getCell(2);
        if (!cell.toString().startsWith(LineInfoFileConstant.STATION_HEAD_C3)
                && !cell.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.STATION_HEAD_C3))) {
            throw new ParseLineInfoException(flagName+"SHEET,第三列不是" + LineInfoFileConstant.STATION_HEAD_C3);
        }
        cell = row_head.getCell(3);
        if (!cell.toString().startsWith(LineInfoFileConstant.STATION_HEAD_C4)
                && !cell.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.STATION_HEAD_C4))) {
            throw new ParseLineInfoException(flagName+"SHEET,第四列不是" + LineInfoFileConstant.STATION_HEAD_C4);
        }
        cell = row_head.getCell(4);
        if (!cell.toString().startsWith(LineInfoFileConstant.STATION_HEAD_C5)
                && !cell.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.STATION_HEAD_C5))) {
            throw new ParseLineInfoException(flagName+"SHEET,第五列不是" + LineInfoFileConstant.STATION_HEAD_C5);
        }
        cell = row_head.getCell(5);
        if (!cell.toString().startsWith(LineInfoFileConstant.STATION_HEAD_C6_)
                && !cell.toString().startsWith(LineInfoFileConstant.STATION_HEAD_C6)
                && !cell.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.STATION_HEAD_C6))) {
            throw new ParseLineInfoException(flagName+"SHEET,第六列不是" + LineInfoFileConstant.STATION_HEAD_C6);
        }
        cell = row_head.getCell(6);
        if (!cell.toString().startsWith(LineInfoFileConstant.STATION_HEAD_C7)
                && !cell.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.STATION_HEAD_C7))) {
            throw new ParseLineInfoException(flagName+"SHEET,第七列不是" + LineInfoFileConstant.STATION_HEAD_C7);
        }
        cell = row_head.getCell(7);
        if (!cell.toString().startsWith(LineInfoFileConstant.STATION_HEAD_C8)
                && !cell.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.STATION_HEAD_C8))) {
            throw new ParseLineInfoException(flagName+"SHEET,第八列不是" + LineInfoFileConstant.STATION_HEAD_C8);
        }
        cell = row_head.getCell(8);
//        if (!cell.toString().startsWith(LineInfoFileConstant.STATION_HEAD_C9)
//                && !cell.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.STATION_HEAD_C9))) {
//            throw new ParseLineInfoException("上行SHEET,第九列不是" + LineInfoFileConstant.STATION_HEAD_C9);
//        }

        String mileage = "";
        int mileage_tmp = -1;
        for (int i = 1; i < rows; i++) {
        	Row row = sheet.getRow(i);
        	if(row == null){
        		//到结尾了，跳出循环
        		break;
        	}
            if(null == row.getCell(0)||null == row.getCell(1)||null == row.getCell(2)||null == row.getCell(3)||
                    null == row.getCell(4)||null == row.getCell(5)||null == row.getCell(6)||null == row.getCell(7)){
                //如果有单元格为空，则可能遇到空白行了，跳过继续执行
                continue;
            }

            String name = cellToString(row.getCell(7));
            if(name.getBytes("GBK").length >19){
                throw new ParseLineInfoException("站点名称大于19个字节：" + name);
            }

            int station_num = Integer.parseInt(cellToString(row.getCell(0)));
            if(flag && i != station_num){//判断上行站点序号是否正确
                throw new ParseLineInfoException("上行站点序号不正确：" + station_num);
            }
            if(!flag && (this.getCcLineInfo().getStationsNumDn() - station_num + 1) != i){ //判断下行站点序号是否正确
                throw new ParseLineInfoException("下行站点序号不正确：" + station_num);
            }

            CcStationInfo ccStationInfo = null;
            ccStationInfo = new CcStationInfo();
            ccStationInfo.setLineId(this.ccLineInfo.getLineId());

            ccStationInfo.setName(checkBlank(name));
            System.out.println(ccStationInfo.getName());
            ccStationInfo.setFlag(flag ? 1 : 0);

            ccStationInfo.setNum(station_num);
            ccStationInfo.setLongitude(cellToString(row.getCell(4)));
            ccStationInfo.setLatitude(cellToString(row.getCell(5)));
            ccStationInfo.setOperaStationNum(Integer.parseInt(cellToString(row.getCell(1))));
            ccStationInfo.setIcStationNum(Integer.parseInt(cellToString(row.getCell(2))));
            ccStationInfo.setIcSubStationNum(Integer.parseInt(cellToString(row.getCell(3))));
            ccStationInfo.setUpdateTime(DateUtil.getAllMsTime());
            ccStationInfo.setCreateTime(DateUtil.getAllMsTime());
            
            if(i == 1){//首站不统计票价
            	if (flag) {
                    ccStationInfoList_up.add(ccStationInfo);
                } else {
                    ccStationInfoList_down.add(ccStationInfo);
                }
            	continue;
            }
            int mlg = StringUtil.stringMultiplyOneHundred(cellToString(row.getCell(6)));
            if(mlg < mileage_tmp){
                throw new ParseLineInfoException((flag?"上行":"下行")+"公里数排序有误：" + cellToString(row.getCell(6)));
            }
            mileage_tmp = mlg;
            mileage += mlg;
            mileage += ",";
            /*ccPriceInfo = new CcPriceInfo();
            ccPriceInfo.setDirFlag(String.valueOf(flag ? 1 : 0));
            ccPriceInfo.setLineId(this.ccLineInfo.getLineId());
            ccPriceInfo.setNum(cellToString(row.getCell(0)));
            ccPriceInfo.setUpdateTime(DateUtil.getAllMsTime());
            ccPriceInfo.setCreateTime(DateUtil.getAllMsTime());
            int prices = i - 1;//票价列数
            String prices_str = "";
            for (int k = 0; k < prices; k++) {
                String price = cellToString(row.getCell(LineInfoFileConstant.STATION_HEAD_CELLS - 1 + k));
                prices_str += (new Double(100 * new Double(price))).intValue();
                prices_str += ",";
            }
            //站点1到站点1的票价为空，该条数据也插入数据库中，实际可能没用
            if (prices_str.length() > 1) {
                prices_str = prices_str.substring(0, prices_str.length() - 1);
            }
            ccPriceInfo.setPrice(prices_str);*/


            if (flag) {
                ccStationInfoList_up.add(ccStationInfo);
//                ccPriceInfoList_up.add(ccPriceInfo);
            } else {
                ccStationInfoList_down.add(ccStationInfo);
//                ccPriceInfoList_down.add(ccPriceInfo);
            }

            int num = Integer.parseInt(cellToString(row.getCell(0)));
            if(flag){
                if(num == this.getCcLineInfo().getStationsNumUp()){
                    break;
                }
            }else{
                if(1 == num){
                    break;
                }
            }

        }
        CcMileageInfo mileageInfo = new CcMileageInfo();
        mileageInfo.setMileageValue(mileage.substring(0, mileage.length() - 1));
        mileageInfo.setLineId(this.ccLineInfo.getLineId());
        mileageInfo.setCreateTime(DateUtil.getAllMsTime());
        mileageInfo.setUpdateTime(DateUtil.getAllMsTime());
        if(flag) {
            mileageInfo.setDirFlag("1");
            ccMileageInfo_up = mileageInfo;
        }else{
            mileageInfo.setDirFlag("0");
            ccMileageInfo_down = mileageInfo;
        }
        
        parsePrice(sheet, this.ccLineInfo.getLineId(), flag);
    }

    /**
     * 解析票价
     * @param sheet
     * @param flag
     * @throws ParseLineInfoException
     */
    private void parsePrice(Sheet sheet,String lineId, boolean flag) throws ParseLineInfoException {
        int count = 0;
        if(flag){
            count = this.getCcLineInfo().getStationsNumUp();
        }else{
            count = this.getCcLineInfo().getStationsNumDn();
        }
        int prices[] = new int[count];
        for(int i=0; i<count-1; i++){//列
            CcPriceInfo ccPriceInfo = new CcPriceInfo();
            ccPriceInfo.setDirFlag(flag?"1":"0");
            ccPriceInfo.setLineId(lineId);
            ccPriceInfo.setNum(i+"");
            String prices_str = "";
            int num_row = 0;
            boolean isfirst = true;
            int price_tmp = 0;
            for(int j=2+i, k=i; j<count+1; j++,k++) {//行
                if(isfirst){
                    num_row = j-1;
                    isfirst = false;
                }
                Row row = sheet.getRow(j);
                if(row == null){//如果row为null，则说明该行没有数据了，站点数大于实际内容
                    throw new ParseLineInfoException("线路信息中站点总数量与文件中实际站点行数不匹配，请核实！");
                }
                Cell cell = row.getCell(LineInfoFileConstant.STATION_HEAD_CELLS - 1 + i );
                String tmp = cellToString(cell);
                int price = 0;
                try{
                    price = (int)(Double.parseDouble(tmp.trim())*100);//.parseInt(tmp.trim())*100;//元转换成分
                }catch (NumberFormatException e){
                    throw new ParseLineInfoException("第"+(cell.getRowIndex()+1)+"行，第"+ ByteUtil.excelColumnValStr(cell.getColumnIndex()+1)+"列票价格式不正确，请核实！");
                }
                if(price < price_tmp){
                    throw new ParseLineInfoException("第"+(cell.getRowIndex()+1)+"行，第"+ ByteUtil.excelColumnValStr(cell.getColumnIndex()+1)+"列票价比上一站票（列向）价小，请核实！");
                }
                if(i != 0 && price > prices[k]){
                    throw new ParseLineInfoException("第"+(cell.getRowIndex()+1)+"行，第"+ByteUtil.excelColumnValStr(cell.getColumnIndex()+1)+"列票价比上一站票（横向）价小，请核实！");
                }
                prices[k] = price;
                price_tmp = price;
                prices_str += price;
                prices_str += ",";
            }
            if (prices_str.length() > 1) {
                prices_str = prices_str.substring(0, prices_str.length() - 1);
            }

            System.out.println(prices_str);
            ccPriceInfo.setPrice(prices_str);
            ccPriceInfo.setNum(cellToString(sheet.getRow(num_row).getCell(0)));
            ccPriceInfo.setUpdateTime(DateUtil.getAllMsTime());
            ccPriceInfo.setCreateTime(DateUtil.getAllMsTime());
            if (flag) {
                ccPriceInfoList_up.add(ccPriceInfo);
            } else {
                ccPriceInfoList_down.add(ccPriceInfo);
            }
        }
    }
    
    private void initCompany(){
        busCompany.setType(0);
        busCompany.setParentCompanyId("0");
        busCompany.setCreateTime(DateUtil.getAllMsTime());
        busCompany.setUpdateTime(DateUtil.getAllMsTime());
        subBusCompany.setType(1);
        subBusCompany.setParentCompanyId(busCompany.getCompanyId());
        subBusCompany.setCreateTime(DateUtil.getAllMsTime());
        subBusCompany.setUpdateTime(DateUtil.getAllMsTime());
    }

    private void parseLineinfo() throws ParseLineInfoException {
        int rows = this.sheet_lineinfo.getPhysicalNumberOfRows();
        /* 不再检验行数是否等于22或28，只要判断行数大于22即可
        if (LineInfoFileConstant.SHEET_LINEINFO_ROWS != rows
                && LineInfoFileConstant.SHEET_LINEINFO_ROWS_ != rows) {
            throw new ParseLineInfoException("第一个Sheet的总行数等于" + LineInfoFileConstant.SHEET_LINEINFO_ROWS +
            "或者等于：" + LineInfoFileConstant.SHEET_LINEINFO_ROWS_);
        }*/
        if (rows < LineInfoFileConstant.SHEET_LINEINFO_ROWS ){
            throw new ParseLineInfoException("第一个Sheet的总行是:"+rows+", 小于21行。");
        }
        for(int i=0; i<LineInfoFileConstant.SHEET_LINEINFO_ROWS ; i++){
            Row row = sheet_lineinfo.getRow(i);
        // for (Row row : sheet_lineinfo) {//解析每行数据
            Cell cell_tital = row.getCell(0);
            Cell cell_value = row.getCell(1);
            //线路编码
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW1_C1)
                    || cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW1_C1))) {

                ccLineInfo.setLineId(Integer.valueOf(cellToString(cell_value))+"");
            }
            //线路名称
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW2_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW2_C1))) {
                String lineName = cellToString(cell_value);
                if(!checkLineName(lineName)){
                    throw new ParseLineInfoException("线路名称包含数字、字母和中文以外的特殊字符！");
                }
                ccLineInfo.setName(cellToString(cell_value));
            }
            //运营单位标识
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW3_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW3_C1))) {
                ccLineInfo.setCompanyId(cellToString(cell_value));
                busCompany.setCompanyId(cellToString(cell_value));
                initCompany();
            }
            //客运企业名称
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW4_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW4_C1))) {
                busCompany.setCompanyName(cellToString(cell_value));
            }
            //所属运营分公司代码
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW5_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW5_C1))) {
                ccLineInfo.setSubCompanyId(cellToString(cell_value));
                subBusCompany.setCompanyId(cellToString(cell_value));
            }
            //分公司名称
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW6_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW6_C1))) {
                subBusCompany.setCompanyName(cellToString(cell_value));
            }
            //线路类型/线路属性
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW7_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW7_C1))) {
                ccLineInfo.setLineAttr(cellToString(cell_value));
            }
            //上行(环形)站点总数量
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW8_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW8_C1))) {
                ccLineInfo.setStationsNumUp(Integer.parseInt(cellToString(cell_value)));
            }
            //下行站点总数量
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW9_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW9_C1))) {
                ccLineInfo.setStationsNumDn(Integer.parseInt(cellToString(cell_value)));
            }
            //当前线路文件格式版本号
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW10_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW10_C1))) {
                ccLineInfo.setFileVersion(cellToString(cell_value));
            }
            //换乘时限(分钟)
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW11_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW11_C1))) {
                ccLineInfo.setExchangeTime(Integer.parseInt(cellToString(cell_value)));
            }
            //基本票价(元)
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW12_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW12_C1))) {
                ccLineInfo.setPriceBase(Integer.parseInt(cellToString(cell_value))*100);
            }
            //上行市界起点
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW13_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW13_C1))) {
                ccLineInfo.setCityLeaveUp(cellToString(cell_value));
            }
            //下行市界起点
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW14_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW14_C1))) {
                ccLineInfo.setCityLeaveDown(cellToString(cell_value));
            }
            //本地未定义卡类型处理模式
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW15_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW15_C1))) {
                ccLineInfo.setLocalNoMode(cellToString(cell_value));
            }
            //异地未定义卡类型处理模式
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW16_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW16_C1))) {
                ccLineInfo.setOffsiteNoModel(cellToString(cell_value));
            }
            //车辆属性
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW17_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW17_C1))) {
                ccLineInfo.setBusAttr(cellToString(cell_value));
            }
            //异地逃票补票模式
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW18_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW18_C1))) {
                ccLineInfo.setOffsiteTicketModel(cellToString(cell_value));
            }
            //上下车方向不同补票规则
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW19_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW19_C1))) {
                ccLineInfo.setDifTicketRule(cellToString(cell_value));
            }
            //补票金额是否无折扣标志
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW20_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW20_C1))) {
                ccLineInfo.setTicketDiscountFlag(cellToString(cell_value));
            }
            //功能开关
            if (cell_tital.toString().startsWith(LineInfoFileConstant.LINEINFO_ROW21_C1)
                    ||cell_tital.toString().startsWith(LineInfoFileConstant.encode(LineInfoFileConstant.LINEINFO_ROW21_C1))) {
                ccLineInfo.setFunctionSwitch(cellToString(cell_value));
            }
            ccLineInfo.setUpdateTime(DateUtil.getAllMsTime());
            ccLineInfo.setCreateTime(DateUtil.getAllMsTime());
        }
    }

    /**
     * 将cell内容是数字类型的数据转换成string
     *
     * @param cell
     * @return
     */
    private String cellToString(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        if (cellType == CellType.NUMERIC) {
            String tmp = cell.toString();
            DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
            return decimalFormat.format(Double.parseDouble(cell.toString()));
        } else {
            return cell.toString();
        }
    }

    /**
     * 去掉字符串是否存在空格、制表符、换行、回车
     * @return
     */
    private String checkBlank(String string){
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(string);
        return m.replaceAll("");
    }

    public boolean checkLineName(String lineName){
        Pattern p = Pattern.compile("[a-zA-Z0-9\\u4e00-\\u9fa5]+");
        Matcher m = p.matcher(lineName);
        return m.matches();
    }


    public boolean parseSheet(InputStream inputStream) throws ParseLineInfoException {
        try {
            Workbook wb = WorkbookFactory.create(inputStream);
            //Sheet sheet = wb.getSheetAt(0);
            int ss = wb.getNumberOfSheets();
            if (lineType == 0 && ss != 3) {
                throw new ParseLineInfoException("折返型线路--文件Sheet数量不等于3");
            }
            if (lineType == 1 && ss != 2) {
                throw new ParseLineInfoException("环型线路--文件Sheet数量不等于2");
            }
            sheet_lineinfo = wb.getSheetAt(0);

            if (!LineInfoFileConstant.SHEET_LINEINFO.equals(sheet_lineinfo.getSheetName())
            && !LineInfoFileConstant.encode(LineInfoFileConstant.SHEET_LINEINFO).equals(sheet_lineinfo.getSheetName())) {
                throw new ParseLineInfoException("文件第一个Sheet的名称应该为" + LineInfoFileConstant.SHEET_LINEINFO);
            }
            if(lineType == 0) {
                sheet_line_up = wb.getSheetAt(1);
                if (!LineInfoFileConstant.SHEET_LINE_UP1.equals(sheet_line_up.getSheetName())
                        && !LineInfoFileConstant.encode(LineInfoFileConstant.SHEET_LINE_UP1).equals(sheet_line_up.getSheetName())
                        && !LineInfoFileConstant.SHEET_LINE_UP2.equals(sheet_line_up.getSheetName())
                        && !LineInfoFileConstant.encode(LineInfoFileConstant.SHEET_LINE_UP2).equals(sheet_line_up.getSheetName())) {
                    throw new ParseLineInfoException("文件第二个Sheet的名称应该为：" + LineInfoFileConstant.SHEET_LINE_UP1
                            + "，或者为：" + LineInfoFileConstant.SHEET_LINE_UP2);
                }


                sheet_line_down = wb.getSheetAt(2);
                if (!LineInfoFileConstant.SHEET_LINE_DOWN1.equals(sheet_line_down.getSheetName())
                        && !LineInfoFileConstant.encode(LineInfoFileConstant.SHEET_LINE_DOWN1).equals(sheet_line_down.getSheetName())
                        && !LineInfoFileConstant.SHEET_LINE_DOWN2.equals(sheet_line_down.getSheetName())
                        && !LineInfoFileConstant.encode(LineInfoFileConstant.SHEET_LINE_DOWN2).equals(sheet_line_down.getSheetName())) {
                    throw new ParseLineInfoException("文件第三个Sheet的名称应该为" + LineInfoFileConstant.SHEET_LINE_DOWN1
                            + "，或者为：" + LineInfoFileConstant.SHEET_LINE_DOWN2);
                }
            }else if(lineType == 1){
                sheet_line_up = wb.getSheetAt(1);
                if (!LineInfoFileConstant.SHEET_LINE_RING.equals(sheet_line_up.getSheetName())
                        && !LineInfoFileConstant.encode(LineInfoFileConstant.SHEET_LINE_RING).equals(sheet_line_up.getSheetName())) {
                    throw new ParseLineInfoException("文件第二个Sheet的名称应该为：" + LineInfoFileConstant.SHEET_LINE_RING);
                }
            }
            return true;
        } catch (IOException e) {
            throw new ParseLineInfoException("文件IO操作异常", e);
        } catch (InvalidFormatException e) {
            throw new ParseLineInfoException("文件格式校验异常", e);
        }
    }

    public CcLineInfo getCcLineInfo() {
        return ccLineInfo;
    }

    public void setCcLineInfo(CcLineInfo ccLineInfo) {
        this.ccLineInfo = ccLineInfo;
    }

    public List<CcStationInfo> getCcStationInfoList_up() {
        return ccStationInfoList_up;
    }

    public void setCcStationInfoList_up(List<CcStationInfo> ccStationInfoList_up) {
        this.ccStationInfoList_up = ccStationInfoList_up;
    }

    public List<CcStationInfo> getCcStationInfoList_down() {
        return ccStationInfoList_down;
    }

    public void setCcStationInfoList_down(List<CcStationInfo> ccStationInfoList_down) {
        this.ccStationInfoList_down = ccStationInfoList_down;
    }

    public List<CcPriceInfo> getCcPriceInfoList_up() {
        return ccPriceInfoList_up;
    }

    public void setCcPriceInfoList_up(List<CcPriceInfo> ccPriceInfoList_up) {
        this.ccPriceInfoList_up = ccPriceInfoList_up;
    }

    public List<CcPriceInfo> getCcPriceInfoList_down() {
        return ccPriceInfoList_down;
    }

    public void setCcPriceInfoList_down(List<CcPriceInfo> ccPriceInfoList_down) {
        this.ccPriceInfoList_down = ccPriceInfoList_down;
    }

    public CcMileageInfo getCcMileageInfo_up() {
        return ccMileageInfo_up;
    }

    public void setCcMileageInfo_up(CcMileageInfo ccMileageInfo_up) {
        this.ccMileageInfo_up = ccMileageInfo_up;
    }

    public CcMileageInfo getCcMileageInfo_down() {
        return ccMileageInfo_down;
    }

    public void setCcMileageInfo_down(CcMileageInfo ccMileageInfo_down) {
        this.ccMileageInfo_down = ccMileageInfo_down;
    }

    public CcBusCompany getBusCompany() {
        return busCompany;
    }

    public void setBusCompany(CcBusCompany busCompany) {
        this.busCompany = busCompany;
    }

    public CcBusCompany getSubBusCompany() {
        return subBusCompany;
    }

    public void setSubBusCompany(CcBusCompany subBusCompany) {
        this.subBusCompany = subBusCompany;
    }
}
