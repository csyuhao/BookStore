package com.yuhao.bookstore.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.yuhao.bookstore.mapper.AdminMapper;
import com.yuhao.bookstore.entity.Admin;
@Service
public class AdminService{

    @Resource
    private AdminMapper adminMapper;

    
    public int deleteByPrimaryKey(String adminid) {
        return adminMapper.deleteByPrimaryKey(adminid);
    }

    
    public int insert(Admin record) {
        return adminMapper.insert(record);
    }

    
    public int insertSelective(Admin record) {
        return adminMapper.insertSelective(record);
    }

    
    public Admin selectByPrimaryKey(String adminid) {
        return adminMapper.selectByPrimaryKey(adminid);
    }

    
    public int updateByPrimaryKeySelective(Admin record) {
        return adminMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Admin record) {
        return adminMapper.updateByPrimaryKey(record);
    }

}
