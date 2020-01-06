package com.yuhao.bookstore.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.yuhao.bookstore.mapper.AdminandconsumerMapper;
import com.yuhao.bookstore.entity.Adminandconsumer;

@Service
public class AdminandconsumerService {

    @Resource
    private AdminandconsumerMapper adminandconsumerMapper;


    public int deleteByPrimaryKey(String csutel, String adminid) {
        return adminandconsumerMapper.deleteByPrimaryKey(csutel, adminid);
    }


    public int insert(Adminandconsumer record) {
        return adminandconsumerMapper.insert(record);
    }


    public int insertSelective(Adminandconsumer record) {
        return adminandconsumerMapper.insertSelective(record);
    }


    public Adminandconsumer selectByPrimaryKey(String csutel, String adminid) {
        return adminandconsumerMapper.selectByPrimaryKey(csutel, adminid);
    }


    public int updateByPrimaryKeySelective(Adminandconsumer record) {
        return adminandconsumerMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Adminandconsumer record) {
        return adminandconsumerMapper.updateByPrimaryKey(record);
    }

}



