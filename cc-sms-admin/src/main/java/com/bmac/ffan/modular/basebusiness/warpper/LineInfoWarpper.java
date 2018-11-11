package com.bmac.ffan.modular.basebusiness.warpper;

import java.util.Map;

import com.bmac.ffan.core.base.warpper.BaseControllerWarpper;
import com.bmac.ffan.core.util.DateUtil;
import com.bmac.ffan.core.util.ToolUtil;

public class LineInfoWarpper extends BaseControllerWarpper{

	public LineInfoWarpper(Object obj) {
		super(obj);
	}

	@Override
	protected void warpTheMap(Map<String, Object> map) {
		boolean type = (boolean) map.get("createFlag");
		if(type) {
			map.put("createFlag", "是");
		}else{
			map.put("createFlag", "否");
		}

		Object updateTime =  map.get("updateTime");
		if (ToolUtil.isNotEmpty(updateTime)) {
			map.put("updateTime", DateUtil.stringPattern((String)updateTime, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
		}
		updateTime = map.get("createTime");
		if (ToolUtil.isNotEmpty(updateTime)) {
			map.put("createTime", DateUtil.stringPattern((String)updateTime, "yyyyMMddHHmmssSSS", "yyyy-MM-dd HH:mm:ss"));
		}

		Object obj = map.get("localNoMode");
		if (ToolUtil.isNotEmpty(obj)) {
			if ("0".equals(obj.toString())) {
				map.put("localNoMode", "不处理本费率表中未定义的乘客卡类型");
			}
			if ("1".equals(obj.toString())) {
				map.put("localNoMode", "表示按照01类型用户卡折扣价执行");
			}
			if ("2".equals(obj.toString())) {
				map.put("localNoMode", "表示按照01类型用户卡无折扣执行");
			}
		}

		obj = map.get("offsiteNoModel");
		if (ToolUtil.isNotEmpty(obj)) {
			if ("0".equals(obj.toString())) {
				map.put("offsiteNoModel", "不处理本费率表中未定义的乘客卡类型");
			}
			if ("1".equals(obj.toString())) {
				map.put("offsiteNoModel", "表示按照01类型用户卡折扣价执行");
			}
			if ("2".equals(obj.toString())) {
				map.put("offsiteNoModel", "表示按照01类型用户卡无折扣执行");
			}
		}
		obj = map.get("busAttr");
		if (ToolUtil.isNotEmpty(obj)) {
			if ("1".equals(obj.toString())) {
				map.put("busAttr", "非空调车");
			}
			if ("2".equals(obj.toString())) {
				map.put("busAttr", "空调车");
			}
		}
		obj = map.get("offsiteTicketModel");
		if (ToolUtil.isNotEmpty(obj)) {
			if ("1".equals(obj.toString())) {
				map.put("offsiteTicketModel", "自动补互通卡异地逃票");
			}
			if ("2".equals(obj.toString())) {
				map.put("offsiteTicketModel", "互通卡异地套票时禁止使用");
			}
			if ("3".equals(obj.toString())) {
				map.put("offsiteTicketModel", "不判断异地套票");
			}
		}
		obj = map.get("difTicketRule");
		if (ToolUtil.isNotEmpty(obj)) {
			if ("1".equals(obj.toString())) {
				map.put("difTicketRule", "补票");
			}
			if ("2".equals(obj.toString())) {
				map.put("difTicketRule", "不补票(远端票价)");
			}
			if ("3".equals(obj.toString())) {
				map.put("difTicketRule", "不补票(近端票价)");
			}
		}
	}

}
