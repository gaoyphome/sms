package com.bmac.ffan.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bmac.ffan.base.BaseJunit;
import com.bmac.ffan.common.persistence.dao.CcSysMenuMapper;
import com.bmac.ffan.common.persistence.model.CcSysMenu;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Stack;

/**
 * 菜单测试
 *
 * @author xuzhanfu
 * @date 2017-06-13 21:23
 */
public class MenuTest extends BaseJunit {

    @Autowired
    CcSysMenuMapper menuMapper;

    /**
     * 初始化pcodes
     *
     * @author xuzhanfu
     * @Date 2017/6/13 21:24
     */
    @Test
    public void generatePcodes() {
        List<CcSysMenu> menus = menuMapper.selectList(null);
        for (CcSysMenu menu : menus) {
            if ("0".equals(menu.getPcode()) || null == menu.getPcode()) {
                menu.setPcodes("[0],");
            } else {
                StringBuffer sb = new StringBuffer();
                CcSysMenu parentMenu = getParentMenu(menu.getCode());
                sb.append("[0],");
                Stack<String> pcodes = new Stack<>();
                while (null != parentMenu.getPcode()) {
                    pcodes.push(parentMenu.getCode());
                    parentMenu = getParentMenu(parentMenu.getPcode());
                }
                int pcodeSize = pcodes.size();
                for (int i = 0; i < pcodeSize; i++) {
                    String code = pcodes.pop();
                    if (code.equals(menu.getCode())) {
                        continue;
                    }
                    sb.append("[" + code + "],");
                }

                menu.setPcodes(sb.toString());
            }
            menu.updateById();
        }
    }

    private CcSysMenu getParentMenu(String code) {
        Wrapper<CcSysMenu> wrapper = new EntityWrapper<CcSysMenu>();
        wrapper = wrapper.eq("code", code);
        List<CcSysMenu> menus = menuMapper.selectList(wrapper);
        if (menus == null || menus.size() == 0) {
            return new CcSysMenu();
        } else {
            return menus.get(0);
        }
    }
}
