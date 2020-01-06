package com.yuhao.bookstore.entity;

import java.io.Serializable;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Bookpurchase implements Serializable {
    private String ordernumber;

    private String bookid;

    private String bookname;

    private String csutel;

    private String shopid;

    private Integer paymenttype;

    private Integer num;

    private Double totalmoney;

    private Integer status;

    private String time;

    private Book book;

    private static final long serialVersionUID = 1L;

    public String getStatusStr(){
        switch (status) {
            case 0:
                return "状态未知";
            case 1:
                return "未付款";
            case 2:
                return "已付款";
            case 3:
                return "未发货";
            case 4:
                return "已发货";
            case 5:
                return "交易成功";
            case 6:
                return "交易关闭";
            default:
                throw new IllegalStateException("Unexpected value: " + status);
        }
    }

    public String getPaymentTypeStr(){
        switch (paymenttype) {
            case 0:
                return "状态未知";
            case 1:
                return "在线支付";
            case 2:
                return "货到付款";
            default:
                throw new IllegalStateException("Unexpected value: " + paymenttype);
        }
    }

    public String getDateStr(){
        if (!time.equals("")) {
            System.out.println(time);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            ParsePosition pos = new ParsePosition(0);
            Date date = formatter.parse(time, pos);
            SimpleDateFormat new_formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new_formatter.format(date);
        }
        return "未完成";
    }
}