package com.yuhao.bookstore.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Shop implements Serializable {
    private String shopid;

    private String shoppass;

    private String shopname;

    private String shopadd;

    private String shoptel;

    private String date;

    private static final long serialVersionUID = 1L;
}