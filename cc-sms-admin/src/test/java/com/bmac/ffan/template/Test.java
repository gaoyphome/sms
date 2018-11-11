package com.bmac.ffan.template;

import com.bmac.ffan.core.util.ByteUtil;
import com.bmac.ffan.core.util.ToolUtil;
import com.bmac.ffan.modular.basebusiness.controller.LineInfoController;
import com.bmac.ffan.modular.basebusiness.util.CRC16;
import com.bmac.ffan.scheme.util.FileUtil;
import com.bmac.ffan.scheme.util.NumberStringUtil;
import com.sun.tools.javac.util.ArrayUtils;
import org.beetl.ext.fn.ArrayUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println(Double.parseDouble("2"));
        System.out.println("--------------------------");
    /*    String str =
                "plt_ssn,req_ssn,settle_date_loc,trans_recv_date,trans_recv_time,trans_code_out,trans_code_chnl,trans_code_ch_name,mch_code_in,mch_name_out,acq_ins_id,acq_ins_seq,loc_tracs_time,loc_tracs_date,term_trans_time,term_trans_date,card_city,txn_token,order_no,state,ntorderid,user_id,order_type,txn_type,ticket_id,cardno,agent_id,mchnt_id,spot_id,term_id,sesq,total_cnt,use_cnt,pos_id,pos_trade_seq,pos_date,tac,tac_mode,psam_id,psam_seq,key_ver,key_index,key_req_num,auth_seq,create_time,update_time,charge_actual,card_balance,card_counter,card_type,card_phy_type,pre_card_balance,serial_num,cpu_counter,charge_ideal,ticket_upload,product_code";
        Optional<String> stringOptional = Optional.ofNullable(str);
        Arrays.stream(stringOptional.get().split(","))
                .map(s->{return s+" as "+replice(s) + ",";})
                .forEach(System.out::println);*/
      //  System.out.println(Integer.parseInt("2.1"));


    }

    public static String formatDouble(String doublestr){
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        return decimalFormat.format(doublestr);
    }
    public static String replice(String str){
        if(str.contains("_")){
            int index = str.indexOf("_");
            char[] cs = str.toCharArray();
            cs = org.apache.commons.lang.ArrayUtils.remove(cs, index);
            cs[index] = Character.toUpperCase(cs[index]);
            return replice(new String(cs));
        }else{
            return str;
        }
    }
}
