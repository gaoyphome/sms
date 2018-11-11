package com.bmac.ffan.modular.system.service.impl;

import com.bmac.ffan.core.util.Convert;
import com.bmac.ffan.modular.system.dao.RoleDao;
import com.bmac.ffan.modular.system.service.IRoleService;
import com.bmac.ffan.common.persistence.dao.CcSysRelationMapper;
import com.bmac.ffan.common.persistence.dao.CcSysRoleMapper;
import com.bmac.ffan.common.persistence.model.CcSysRelation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    CcSysRoleMapper roleMapper;

    @Resource
    RoleDao roleDao;

    @Resource
    CcSysRelationMapper relationMapper;

    @Override
    @Transactional(readOnly = false)
    public void setAuthority(Integer roleId, String ids) {

        // 删除该角色所有的权限
        this.roleDao.deleteRolesById(roleId);

        // 添加新的权限
        for (Integer id : Convert.toIntArray(ids)) {
            CcSysRelation relation = new CcSysRelation();
            relation.setRoleid(roleId);
            relation.setMenuid(id);
            this.relationMapper.insert(relation);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delRoleById(Integer roleId) {
        //删除角色
        this.roleMapper.deleteById(roleId);

        // 删除该角色所有的权限
        this.roleDao.deleteRolesById(roleId);
    }

}
