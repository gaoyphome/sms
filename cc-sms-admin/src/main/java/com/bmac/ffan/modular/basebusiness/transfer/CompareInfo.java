package com.bmac.ffan.modular.basebusiness.transfer;

/**
 * @Author: Buff
 * @Description:
 * @Date: Created in 2018/2/2 15:23
 */
public class CompareInfo {
    private String filedName;
    private String filedValueOld;
    private String filedValueNew;

    public CompareInfo(String filedName, String filedValueOld, String filedValueNew) {
        this.filedName = filedName;
        this.filedValueOld = filedValueOld;
        this.filedValueNew = filedValueNew;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getFiledValueOld() {
        return filedValueOld;
    }

    public void setFiledValueOld(String filedValueOld) {
        this.filedValueOld = filedValueOld;
    }

    public String getFiledValueNew() {
        return filedValueNew;
    }

    public void setFiledValueNew(String filedValueNew) {
        this.filedValueNew = filedValueNew;
    }
}
