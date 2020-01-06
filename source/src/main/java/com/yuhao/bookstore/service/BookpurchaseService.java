package com.yuhao.bookstore.service;

import com.yuhao.bookstore.entity.Book;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.swing.*;

import com.yuhao.bookstore.mapper.BookpurchaseMapper;
import com.yuhao.bookstore.entity.Bookpurchase;

import java.util.List;
import java.util.Map;

@Service
public class BookpurchaseService {

    @Resource
    private BookpurchaseMapper bookpurchaseMapper;


    public int deleteByPrimaryKey(String ordernumber) {
        return bookpurchaseMapper.deleteByPrimaryKey(ordernumber);
    }


    public int insert(Bookpurchase record) {
        return bookpurchaseMapper.insertSelective(record);
    }


    public int insertSelective(Bookpurchase record) {
        return bookpurchaseMapper.insertSelective(record);
    }


    public Bookpurchase selectByPrimaryKey(String ordernumber) {
        return bookpurchaseMapper.selectByPrimaryKey(ordernumber);
    }


    public int updateByPrimaryKeySelective(Bookpurchase record) {
        return bookpurchaseMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Bookpurchase record) {
        return bookpurchaseMapper.updateByPrimaryKey(record);
    }

    public List<Bookpurchase> findAllByShopid(String shopId) {
        return bookpurchaseMapper.findAllByShopid(shopId);
    }

    public List<Bookpurchase> findAllByCsutelAndStatusEx(String csutel, Integer status){
        return bookpurchaseMapper.findAllByCsutelAndStatusEx(csutel, status);
    }

    public List<Bookpurchase> selectAllByBookidAndCsutelAndStatus(String bookId, String csuTel, Integer status){
        return bookpurchaseMapper.selectAllByBookidAndCsutel(bookId, csuTel, status);
    }

    public int deleteByOrdernumber(String orderNumber){
        return bookpurchaseMapper.deleteByOrdernumber(orderNumber);
    }

    public Bookpurchase findOneByOrdernumberEx(String orderNumber){
        return bookpurchaseMapper.findOneByOrdernumberEx(orderNumber);
    }

    public Integer buyBook(Integer book_num, String order_number, String order_time){
        return bookpurchaseMapper.buyBook(book_num, order_number, order_time);
    }

    public List<Bookpurchase> selectAllByCsutelAndStatus(String csuTel, Integer status){
        return bookpurchaseMapper.selectAllByCsutelAndStatus(csuTel, status);
    }
}



