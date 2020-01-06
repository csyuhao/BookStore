package com.yuhao.bookstore.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.yuhao.bookstore.mapper.BookMapper;
import com.yuhao.bookstore.entity.Book;

import java.util.List;

@Service
public class BookService {

    @Resource
    private BookMapper bookMapper;


    public int deleteByPrimaryKey(String bookid) {
        return bookMapper.deleteByPrimaryKey(bookid);
    }


    public int insert(Book record) {
        return bookMapper.insert(record);
    }


    public int insertSelective(Book record) {
        return bookMapper.insertSelective(record);
    }


    public Book selectByPrimaryKey(String bookid) {
        return bookMapper.selectOneByBookid(bookid);
    }


    public int updateByPrimaryKeySelective(Book record) {
        return bookMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Book record) {
        return bookMapper.updateByPrimaryKey(record);
    }

    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    public List<Book> findAllByShopid(String shopid) {
        return bookMapper.findAllByBookshopid(shopid);
    }

    public List<Book> findAllByBookshopidAndBookdeletedFalse(String shopId) {
        return bookMapper.findAllByBookshopidAndBookdeletedFalse(shopId);
    }

    public int updateBookdeletedByBookid(Boolean mark, String bookId) {
        return bookMapper.updateBookdeletedByBookid(mark, bookId);
    }

    public int updateByBookid(Book book, String bookId) {
        return bookMapper.updateByBookid(book, bookId);
    }

    public Integer findBookstockByBookid(String bookId){
        return bookMapper.findOneBookstockByBookid(bookId);
    }

    public List<Book> findAllByBookdeletedFalse(){
        return bookMapper.findAllByBookdeletedFalse();
    }
}



