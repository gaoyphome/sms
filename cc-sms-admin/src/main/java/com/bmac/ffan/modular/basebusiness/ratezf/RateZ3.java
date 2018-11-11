package com.bmac.ffan.modular.basebusiness.ratezf;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2017/12/20 20:52
 */
public class RateZ3 extends RateHead {
    private RateHead rateHead;

    private List<RateRecordZ3> rateRecordZ3List = new ArrayList<RateRecordZ3>();

    public RateHead getRateHead() {
        return rateHead;
    }

    public void setRateHead(RateHead rateHead) {
        this.rateHead = rateHead;
    }

    public List<RateRecordZ3> getRateRecordZ3List() {
        return rateRecordZ3List;
    }

    public void setRateRecordZ3List(List<RateRecordZ3> rateRecordZ3List) {
        this.rateRecordZ3List = rateRecordZ3List;
    }

    @Override
    public String toString() {
        return "RateZ3{" +
                "rateHead=" + rateHead +
                ", rateRecordZ3List=" + rateRecordZ3List +
                '}';
    }
}
