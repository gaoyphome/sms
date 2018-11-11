package com.bmac.ffan.modular.basebusiness.ratezf;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2017/12/20 20:52
 */
public class RateZ2 {
    private RateHead rateHead;

    private List<RateRecordZ2> rateRecordZ2List = new ArrayList<RateRecordZ2>();

    public RateHead getRateHead() {
        return rateHead;
    }

    public void setRateHead(RateHead rateHead) {
        this.rateHead = rateHead;
    }

    public List<RateRecordZ2> getRateRecordZ2List() {
        return rateRecordZ2List;
    }

    public void setRateRecordZ2List(List<RateRecordZ2> rateRecordZ2List) {
        this.rateRecordZ2List = rateRecordZ2List;
    }

    @Override
    public String toString() {
        return "RateZ2{" +
                "rateHead=" + rateHead +
                ", rateRecordZ2List=" + rateRecordZ2List +
                '}';
    }
}
