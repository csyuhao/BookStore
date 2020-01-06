package com.yuhao.bookstore.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Consumer implements Serializable {
    private String csutel;

    private String csupass;

    private String csuname;

    private String csuadd;

    private String csuemail;

    private Double csubalance;

    private String date;

    private static final long serialVersionUID = 1L;
}