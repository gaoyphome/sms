package com.bmac.ffan.modular.basebusiness.ratezf;

import com.bmac.ffan.core.util.ByteUtil;

import java.io.*;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2017/12/20 21:00
 */
public class ParseZf {
    private RateZ2 rateZ2;
    private RateZ3 rateZ3;
    private InputStream inputStream;
    private ZFType type;
    private String fileContext = "";
    public ParseZf(InputStream inputStream, ZFType type){
        this.inputStream = inputStream;
        this.type = type;
    }

    /**
     * 解析文件头
     */
    private void parseHead() throws Exception {
        if(this.readIntStream() && fileContext != null){
            RateHead rateHead = new RateHead();
            String tmp = "";
            tmp = fileContext.substring(0,2);
            rateHead.setFrameVersion(tmp);
            tmp = fileContext.substring(2,4);
            rateHead.setFrameAmount(tmp);
            tmp = fileContext.substring(4,6);
            rateHead.setFrameNo(tmp);
            tmp = fileContext.substring(6,10);
            rateHead.setRecordAmount(tmp);
            tmp = fileContext.substring(10,14);
            rateHead.setSingleRecordCount(tmp);
            tmp = fileContext.substring(14,18);
            rateHead.setRecordStartPosition(tmp);
            tmp = fileContext.substring(18,26);
            rateHead.setGenerateDate(tmp);
            tmp = fileContext.substring(26,34);
            rateHead.setReciveUnitCode(tmp);
            tmp = fileContext.substring(34,42);
            rateHead.setParamVerNo(tmp);
            tmp = fileContext.substring(42,56);
            rateHead.setTimeOfActive(tmp);
            tmp = fileContext.substring(56,60);
            rateHead.setReserved(tmp);
            tmp = fileContext.substring(60,64);
            rateHead.setCrc(tmp);
            if(type == ZFType.Z2){
                rateZ2 = new RateZ2();
                rateZ2.setRateHead(rateHead);
            }else if(type == ZFType.Z3){
                rateZ3 = new RateZ3();
                rateZ3.setRateHead(rateHead);
            }
        }
    }

    /**
     * 解析Z3文件的单条记录
     * @param record
     * @return
     */
    public RateRecordZ3 parseBodyZ3(String record) throws Exception {
        RateRecordZ3 rz3 = new RateRecordZ3();
        String tmp = "";
        tmp = record.substring(0,16);
        rz3.setIssuerNo(tmp);
        tmp = record.substring(16,18);
        rz3.setLogicType(ByteUtil.hexStr2num(tmp,0)+"");
        tmp = record.substring(18,20);
        rz3.setCostMode(ByteUtil.hexStr2num(tmp,0)+"");
        tmp = record.substring(20,24);
        rz3.setOverdrawMax(ByteUtil.hexStr2num(tmp, 0)+"");
        tmp = record.substring(24,32);
        rz3.setMaxLimitOfCardMoney(ByteUtil.hexStr2num(tmp, 0)+"");
        tmp = record.substring(32,36);
        rz3.setMaxAmount(ByteUtil.hexStr2num(tmp, 0)+"");
        tmp = record.substring(36,38);
        rz3.setInRate(ByteUtil.hexStr2num(tmp,0)+"");
        tmp = record.substring(38,42);
        rz3.setInAmount(ByteUtil.hexStr2num(tmp, 0)+"");
        tmp = record.substring(42,44);
        rz3.setOutRate(ByteUtil.hexStr2num(tmp,0)+"");
        tmp = record.substring(44,48);
        rz3.setOutAmount(ByteUtil.hexStr2num(tmp, 0)+"");
        tmp = record.substring(48,96);
        rz3.setReserved(tmp);
        return rz3;
    }

    /**
     * 解析Z2文件的单条记录
     * @param record
     * @return
     */
    public RateRecordZ2 parseBodyZ2(String record) throws Exception {
        RateRecordZ2 rz2 = new RateRecordZ2();
        String tmp = "";
        tmp = record.substring(0,2);
        rz2.setPhysicType(ByteUtil.hexStr2num(tmp,1)+"");
        tmp = record.substring(2,4);
        rz2.setLogicType(tmp);
        tmp = record.substring(4,6);
        rz2.setCardAttr(ByteUtil.hexStr2num(tmp,1)+"");
        tmp = record.substring(6,8);
        rz2.setCostMode(tmp);
        tmp = record.substring(8,12);
        rz2.setMinLimitOfCardMoney(ByteUtil.hexStr2num(tmp, 1)+"");
        tmp = record.substring(12,16);
        rz2.setOverdrawMax(ByteUtil.hexStr2num(tmp, 1)+"");
        tmp = record.substring(16,24);
        rz2.setMaxLimitOfCardMoney(ByteUtil.hexStr2num(tmp, 1)+"");
        tmp = record.substring(24,28);
        rz2.setMaxAmount(ByteUtil.hexStr2num(tmp, 1)+"");
        tmp = record.substring(28,30);
        rz2.setInRate(ByteUtil.hexStr2num(tmp,1)+"");
        tmp = record.substring(30,34);
        rz2.setInAmount(ByteUtil.hexStr2num(tmp, 1)+"");
        tmp = record.substring(34,36);
        rz2.setOutRate(ByteUtil.hexStr2num(tmp,1)+"");
        tmp = record.substring(36,40);
        rz2.setOutAmount(ByteUtil.hexStr2num(tmp, 1)+"");
        tmp = record.substring(40,96);
        rz2.setReserved(tmp);
        return rz2;
    }

    public RateZ2 getRateZ2() {
        try {
            if(this.type == ZFType.Z2){
                parseHead();
                String body = fileContext.substring(64);
                long count = ByteUtil.hexStr2num(this.rateZ2.getRateHead().getRecordAmount(),1);
                long recordCount = ByteUtil.hexStr2num(this.rateZ2.getRateHead().getSingleRecordCount(),1)*2;
                int recordCount_int = new Long(recordCount).intValue();//实际应该不会超出整数范围
                for(int i=0; i< count; i++){
                    RateRecordZ2 rz2 = parseBodyZ2(body.substring(i*recordCount_int, i*recordCount_int+recordCount_int));
                    this.rateZ2.getRateRecordZ2List().add(rz2);
                }
                return rateZ2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public RateZ3 getRateZ3() {
        try {
            if(this.type == ZFType.Z3){
                parseHead();
                String body = fileContext.substring(64);
                long count = ByteUtil.hexStr2num(this.rateZ3.getRateHead().getRecordAmount(),0);
                long recordCount = ByteUtil.hexStr2num(this.rateZ3.getRateHead().getSingleRecordCount(),0)*2;

                int recordCount_int = new Long(recordCount).intValue();//实际应该不会超出整数范围
                for(int i=0; i< count; i++){
                    RateRecordZ3 rz3 = parseBodyZ3(body.substring(i*recordCount_int, i*recordCount_int+recordCount_int));
                    this.rateZ3.getRateRecordZ3List().add(rz3);
                }
                return rateZ3;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 读取文件内容，以HEX串输出
     * @return
     */
    private boolean readIntStream(){
        if(inputStream != null){
            try {
                byte[] tempbytes = new byte[1024];
                int byteread = 0;
                while ((byteread = inputStream.read(tempbytes,0, tempbytes.length)) != -1) {
                    byte[] temp =  new byte[byteread];
                    System.arraycopy(tempbytes,0,temp,0,byteread);
                    fileContext += ByteUtil.byte2hex(temp);
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public enum ZFType{
        Z2, Z3
    }
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args) {
        File file = new File("C:\\Users\\vicya\\Desktop\\TMP\\Z3.txt");
        try {
            ParseZf zf  = new ParseZf(new FileInputStream(file), ZFType.Z3);
            System.out.println(zf.fileContext);
          //  zf.readIntStream();
            RateZ3 r2 = zf.getRateZ3();
            System.out.println(zf.fileContext);
            System.out.println(r2.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
