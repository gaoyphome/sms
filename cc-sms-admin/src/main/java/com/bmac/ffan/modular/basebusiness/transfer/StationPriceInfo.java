package com.bmac.ffan.modular.basebusiness.transfer;

import java.util.ArrayList;
import java.util.List;

public class StationPriceInfo {
    public StationPriceInfo() {
    }

    public StationPriceInfo(String num, String stationname, List<String> prices) {
        this.num = num;
        this.stationname = stationname;
        this.prices = prices;
    }

    private String num;

    private String stationname;

    private List<String> prices = new ArrayList<String>();

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public List<String> getPrices() {
        return prices;
    }

    public void setPrices(List<String> prices) {
        this.prices = prices;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "StationPriceInfo{" +
                "num='" + num + '\'' +
                ", stationname='" + stationname + '\'' +
                ", prices=" + prices +
                '}';
    }
}
