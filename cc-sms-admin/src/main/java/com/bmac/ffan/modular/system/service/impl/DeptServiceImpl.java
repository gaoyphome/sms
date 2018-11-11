package com.bmac.ffan.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bmac.ffan.common.persistence.dao.CcSysDeptMapper;
import com.bmac.ffan.common.persistence.model.CcSysDept;
import com.bmac.ffan.modular.system.service.IDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements IDeptService {

    @Resource
    CcSysDeptMapper deptMapper;

    @Override
    public void deleteDept(Integer deptId) {

        CcSysDept dept = deptMapper.selectById(deptId);

        Wrapper<CcSysDept> wrapper = new EntityWrapper<>();
        wrapper = wrapper.like("pids", "%[" + dept.getId() + "]%");
        List<CcSysDept> subDepts = deptMapper.selectList(wrapper);
        for (CcSysDept temp : subDepts) {
            temp.deleteById();
        }

        dept.deleteById();
    }
}
