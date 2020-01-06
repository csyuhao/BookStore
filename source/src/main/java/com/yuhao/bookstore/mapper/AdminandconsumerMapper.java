package com.yuhao.bookstore.mapper;

import com.yuhao.bookstore.entity.Adminandconsumer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminandconsumerMapper {
    int deleteByPrimaryKey(@Param("csutel") String csutel, @Param("adminid") String adminid);

    int insert(Adminandconsumer record);

    int insertSelective(Adminandconsumer record);

    Adminandconsumer selectByPrimaryKey(@Param("csutel") String csutel, @Param("adminid") String adminid);

    int updateByPrimaryKeySelective(Adminandconsumer record);

    int updateByPrimaryKey(Adminandconsumer record);
}