package com.bmac.ffan.scheme.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bmac.ffan.common.persistence.dao.FileReceiveMapper;
import com.bmac.ffan.scheme.model.CcFileReceive;
import com.bmac.ffan.scheme.service.IFileReceiveService;

/**
 * 接收日志信息Service
 * @author xiongrl
 *
 */
@Service
public class FileReceiveServiceImpl extends ServiceImpl<FileReceiveMapper, CcFileReceive> implements IFileReceiveService {

}
