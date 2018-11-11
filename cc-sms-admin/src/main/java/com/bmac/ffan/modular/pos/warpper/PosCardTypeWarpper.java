package com.bmac.ffan.modular.pos.warpper;

import java.util.Map;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

public class PosCardTypeWarpper extends BaseControllerWarpper{

	public PosCardTypeWarpper(Object obj) {
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
		obj = map.get("cardPhyType");
		if(ToolUtil.isNotEmpty(obj)){
			if ("01".equals(obj.toString())){
				map.put("cardPhyType","MIFARE 1(S50)卡");
			}
			if ("02".equals(obj.toString())){
				map.put("cardPhyType","CPU卡");
			}
			if ("03".equals(obj.toString())){
				map.put("cardPhyType","Ultra Light");
			}
			if ("04".equals(obj.toString())){
				map.put("cardPhyType","MIFARE 1(S70)卡");
			}
		}
	}

}
