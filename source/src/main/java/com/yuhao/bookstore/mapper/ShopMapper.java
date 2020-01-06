package com.yuhao.bookstore.mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhao.bookstore.entity.Shop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper {
    int deleteByPrimaryKey(String shopid);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(String shopid);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    Shop findOneByShopid(@Param("shopid")String shopid);

    Shop findOneByShopidAndShoptel(@Param("shopid")String shopid,@Param("shoptel")String shoptel);

}