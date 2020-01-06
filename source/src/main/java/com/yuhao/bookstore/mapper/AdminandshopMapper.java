package com.yuhao.bookstore.mapper;

import com.yuhao.bookstore.entity.Adminandshop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminandshopMapper {
    int deleteByPrimaryKey(@Param("adminid") String adminid, @Param("shopid") String shopid);

    int insert(Adminandshop record);

    int insertSelective(Adminandshop record);

    Adminandshop selectByPrimaryKey(@Param("adminid") String adminid, @Param("shopid") String shopid);

    int updateByPrimaryKeySelective(Adminandshop record);

    int updateByPrimaryKey(Adminandshop record);
}