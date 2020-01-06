package com.yuhao.bookstore.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.yuhao.bookstore.entity.Adminandshop;
import com.yuhao.bookstore.mapper.AdminandshopMapper;
@Service
public class AdminandshopService{

    @Resource
    private AdminandshopMapper adminandshopMapper;

    
    public int deleteByPrimaryKey(String adminid,String shopid) {
        return adminandshopMapper.deleteByPrimaryKey(adminid,shopid);
    }

    
    public int insert(Adminandshop record) {
        return adminandshopMapper.insert(record);
    }

    
    public int insertSelective(Adminandshop record) {
        return adminandshopMapper.insertSelective(record);
    }

    
    public Adminandshop selectByPrimaryKey(String adminid,String shopid) {
        return adminandshopMapper.selectByPrimaryKey(adminid,shopid);
    }

    
    public int updateByPrimaryKeySelective(Adminandshop record) {
        return adminandshopMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Adminandshop record) {
        return adminandshopMapper.updateByPrimaryKey(record);
    }

}
