package com.yuhao.bookstore.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Adminandshop implements Serializable {
    private String adminid;

    private String shopid;

    private String update;

    private static final long serialVersionUID = 1L;
}