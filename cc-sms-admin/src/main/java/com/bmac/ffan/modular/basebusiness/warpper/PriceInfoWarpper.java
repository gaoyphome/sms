package com.bmac.ffan.modular.basebusiness.warpper;

import java.util.Map;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

public class PriceInfoWarpper extends BaseControllerWarpper{

	public PriceInfoWarpper(Object obj) {
		super(obj);
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		Object obj =  map.get("updateTime");
		if (ToolUtil.isNotEmpty(obj)) {
			map.put("updateTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
		}
		obj = map.get("createTime");
		if (ToolUtil.isNotEmpty(obj)) {
			map.put("createTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
		}
		obj =  map.get("dirFlag");
		if (ToolUtil.isNotEmpty(obj)){
			if(((String)obj).equals("1")){
				map.put("dirFlag","上行");
			}else{
				map.put("dirFlag","下行");
			}
		}
	}

}
