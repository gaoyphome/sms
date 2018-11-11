package com.bmac.ffan.modular.basebusiness.warpper;

import java.util.Map;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

public class StationInfoWarpper extends BaseControllerWarpper{

	public StationInfoWarpper(Object obj) {
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

		obj =  map.get("flag");
		if (ToolUtil.isNotEmpty(obj)){
			map.put("flag",(boolean)obj ? "上行": "下行");
		}
	}

}
