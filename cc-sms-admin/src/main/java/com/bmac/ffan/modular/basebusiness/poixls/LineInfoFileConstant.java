package com.bmac.ffan.modular.basebusiness.poixls;

import java.io.UnsupportedEncodingException;

public class LineInfoFileConstant {
    public static final String SHEET_LINEINFO = "线路信息";
    public static final String SHEET_LINE_UP1 = "上行";
    public static final String SHEET_LINE_DOWN1 = "下行";
    public static final String SHEET_LINE_UP2 = "增站";
    public static final String SHEET_LINE_DOWN2= "减站";
    public static final String SHEET_LINE_RING = "环型";

    public static final int SHEET_LINEINFO_ROWS = 21;
    public static final int SHEET_LINEINFO_ROWS_ = 28;
    public static final String LINEINFO_ROW1_C1 = "线路编号";
    public static final String LINEINFO_ROW2_C1 = "线路名称";
    public static final String LINEINFO_ROW3_C1 = "运营单位标识";
    public static final String LINEINFO_ROW4_C1 = "客运企业名称";
    public static final String LINEINFO_ROW5_C1 = "所属运营分公司代码";
    public static final String LINEINFO_ROW6_C1 = "分公司名称";
    public static final String LINEINFO_ROW7_C1 = "线路类型";
    public static final String LINEINFO_ROW8_C1 = "上行(环形)站点总数量";
    public static final String LINEINFO_ROW9_C1 = "下行站点总数量";
    public static final String LINEINFO_ROW10_C1 = "当前线路文件格式版本号";
    public static final String LINEINFO_ROW11_C1 = "换乘时限(分钟)";
    public static final String LINEINFO_ROW12_C1 = "基本票价(元)";
    public static final String LINEINFO_ROW13_C1 = "上行市界起点";
    public static final String LINEINFO_ROW14_C1 = "下行市界起点";
    public static final String LINEINFO_ROW15_C1 = "本地未定义卡类型处理模式";
    public static final String LINEINFO_ROW16_C1 = "异地未定义卡类型处理模式";
    public static final String LINEINFO_ROW17_C1 = "车辆属性";
    public static final String LINEINFO_ROW18_C1 = "异地逃票补票模式";
    public static final String LINEINFO_ROW19_C1 = "上下车方向不同补票规则";
    public static final String LINEINFO_ROW20_C1 = "补票金额是否无折扣标志";
    public static final String LINEINFO_ROW21_C1 = "功能开关";


    public static final int STATION_HEAD_CELLS = 9;
    public static final String STATION_HEAD_C1 = "站号";
    public static final String STATION_HEAD_C2 = "运营站号";
    public static final String STATION_HEAD_C3 = "IC卡站号";
    public static final String STATION_HEAD_C4 = "IC卡招呼站号";
    public static final String STATION_HEAD_C5 = "经度";
    public static final String STATION_HEAD_C6 = "纬度";
    public static final String STATION_HEAD_C6_ = "维度";
    public static final String STATION_HEAD_C7 = "当前站点距离与起始站公里数";
    public static final String STATION_HEAD_C8 = "站名";
    public static final String STATION_HEAD_C9 = "票价";


    public static String encode(String chinese){
        byte[] bytes = chinese.getBytes();
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
