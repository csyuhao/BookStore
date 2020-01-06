package com.yuhao.bookstore.mapper;

import com.yuhao.bookstore.entity.Book;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

@Mapper
public interface BookMapper {
    int deleteByPrimaryKey(String bookid);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(String bookid);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    Book selectOneByBookid(@Param("bookid") String bookid);

    List<Book> findAll();

    List<Book> findAllByBookshopid(@Param("bookshopid") String bookshopid);

    List<Book> findAllByBookshopidAndBookdeletedFalse(@Param("bookshopid") String bookshopid);

    int updateBookdeletedByBookid(@Param("updatedBookdeleted") Boolean updatedBookdeleted, @Param("bookid") String bookid);

    int updateByBookid(@Param("updated") Book updated, @Param("bookid") String bookid);

    List<Integer> findBookstockByBookid(@Param("bookid")String bookid);

    Integer findOneBookstockByBookid(@Param("bookid")String bookid);

    List<Book> findAllByBookdeletedFalse();


}