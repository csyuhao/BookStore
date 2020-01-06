package com.yuhao.bookstore.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Adminandconsumer implements Serializable {
    private String csutel;

    private String adminid;

    private String update;

    private static final long serialVersionUID = 1L;
}