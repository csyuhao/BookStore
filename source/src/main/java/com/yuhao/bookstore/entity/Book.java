package com.yuhao.bookstore.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Book implements Serializable {
    private String bookid;

    private String bookname;

    private String bookisbn;

    private String bookinfor;

    private Double bookprice;

    private String bookshopid;

    private Integer bookstock;

    private String bookimage;

    private Boolean bookdeleted;

    private static final long serialVersionUID = 1L;
}