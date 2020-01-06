package com.yuhao.bookstore.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Admin implements Serializable {
    private String adminid;

    private String adminpass;

    private String admintel;

    private static final long serialVersionUID = 1L;
}