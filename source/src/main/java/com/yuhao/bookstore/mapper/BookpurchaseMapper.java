package com.yuhao.bookstore.mapper;

import com.yuhao.bookstore.entity.Bookpurchase;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BookpurchaseMapper {
    int deleteByPrimaryKey(String ordernumber);

    int insertSelective(Bookpurchase record);

    Bookpurchase selectByPrimaryKey(String ordernumber);

    int updateByPrimaryKeySelective(Bookpurchase record);

    int updateByPrimaryKey(Bookpurchase record);

    List<Bookpurchase> findAllByShopid(@Param("shopid") String shopid);

    List<Bookpurchase> findAllByCsutelAndStatus(@Param("csutel")String csutel,@Param("status")Integer status);


    List<Bookpurchase> findAllByCsutelAndStatusEx(@Param("csutel")String csutel,@Param("status")Integer status);

    List<Bookpurchase> selectAllByBookid(@Param("bookid")String bookid);

    List<Bookpurchase> selectAllByBookidAndCsutel(@Param("bookid")String bookid,@Param("csutel")String csutel, @Param("status")Integer status);

    int deleteByOrdernumber(@Param("ordernumber")String ordernumber);

    Bookpurchase findOneByOrdernumberEx(String orderNumber);

    Integer buyBook(Integer book_num, String order_number, String order_time);

    List<Bookpurchase> selectAllByCsutelAndStatus(@Param("csutel")String csutel,@Param("status")Integer status);


}