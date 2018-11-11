package com.bmac.ffan.modular.basebusiness.warpper;

import java.util.Map;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

public class BusCompanyWarpper extends BaseControllerWarpper {

	public BusCompanyWarpper(Object list) {
		super(list);
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		boolean type = (boolean) map.get("type");
		if(type) {			
			map.put("type", "子运营公司");
		}else{
			map.put("type", "运营公司");
		}

		Object obj =  map.get("updateTime");
		if (ToolUtil.isNotEmpty(obj)) {
			map.put("updateTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
		}
		obj = map.get("createTime");
		if (ToolUtil.isNotEmpty(obj)) {
			map.put("createTime", DateUtil.stringPattern((String)obj, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
		}
	}

}
