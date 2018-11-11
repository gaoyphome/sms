package com.bmac.ffan.modular.dataanalysis.warpper;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2017/12/17 17:16
 */
public class IcecAnalysisWarpper extends BaseControllerWarpper {
    private int dataType;
    public IcecAnalysisWarpper(Object obj, int dataType) {
        super(obj);
        this.dataType = dataType;
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        switch (dataType){
            case 1:
                {
                break;
            }
            case 2  :
                {
                break;
            }
            case 3 :
                {
                break;
            }
            case 4 :
                {
                break;
            }
            case 5 :
            {
                break;
            }
            default:{

            }
        }
    }
}
