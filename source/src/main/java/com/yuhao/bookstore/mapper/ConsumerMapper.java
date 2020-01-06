package com.yuhao.bookstore.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.yuhao.bookstore.entity.Consumer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConsumerMapper {
    int deleteByPrimaryKey(String csutel);

    int insert(Consumer record);

    int insertSelective(Consumer record);

    Consumer selectByPrimaryKey(String csutel);

    int updateByPrimaryKeySelective(Consumer record);

    int updateByPrimaryKey(Consumer record);

    Consumer findOneByCsutel(@Param("csutel")String csutel);

    List<String> selectcsupassByCsutel(@Param("csutel")String csutel);

    Consumer selectOneByCsutelAndCsuemail(@Param("csutel")String csutel,@Param("csuemail")String csuemail);

}