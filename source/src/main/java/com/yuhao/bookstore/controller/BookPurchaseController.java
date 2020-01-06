package com.yuhao.bookstore.controller;

import com.google.gson.Gson;
import com.yuhao.bookstore.entity.Book;
import com.yuhao.bookstore.entity.Bookpurchase;
import com.yuhao.bookstore.entity.Consumer;
import com.yuhao.bookstore.entity.Shop;
import com.yuhao.bookstore.service.BookService;
import com.yuhao.bookstore.service.BookpurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookPurchaseController {

    @Autowired
    private BookpurchaseService bookpurchaseService;

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/shop/orderManage", method = RequestMethod.GET)
    public String shopOrderManage(@RequestParam Map map, HttpSession httpSession, Model model){
        Shop shop = (Shop) httpSession.getAttribute("shop");
        String shopId = shop.getShopid();
        List<Bookpurchase> orderList = bookpurchaseService.findAllByShopid(shopId);
        httpSession.setAttribute("orderList", orderList);
        return "shop/order/orderManage";
    }

    @RequestMapping(value = "/shop/orderDetails", method = RequestMethod.GET)
    public String shopOrderDetail(@RequestParam Map map, Model model){
        String orderId = (String)map.get("orderId");
        Bookpurchase order = bookpurchaseService.selectByPrimaryKey(orderId);
        model.addAttribute("order", order);
        return "shop/order/orderDetails";
    }

    @ResponseBody
    @RequestMapping(value = "/usr/addCart", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String usrAddCart(@RequestParam Map map, HttpSession httpSession, Model model){
        String bookId = (String)map.get("bookid");
        Consumer consumer = (Consumer) httpSession.getAttribute("user");
        if (bookId != null && consumer != null) {
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String orderNum = formatter.format(currentTime);
            if (bookpurchaseService.selectAllByBookidAndCsutelAndStatus(bookId, consumer.getCsutel(), 0).size() > 0){
                Gson gson = new Gson();
                Map<String, String> rmap = new HashMap<>();
                rmap.put("added", "false");
                rmap.put("reason", "该书籍已经在购物车中！");
                return gson.toJson(rmap);
            }

            if (bookService.findBookstockByBookid(bookId) < 1){
                Gson gson = new Gson();
                Map<String, String> rmap = new HashMap<>();
                rmap.put("added", "false");
                rmap.put("reason", "库存不足!");
                return gson.toJson(rmap);
            }
            Book book = bookService.selectByPrimaryKey(bookId);
            Bookpurchase order = new Bookpurchase();
            order.setBookid(book.getBookid());
            order.setBookname(book.getBookname());
            order.setCsutel(consumer.getCsutel());
            order.setOrdernumber(orderNum);
            order.setStatus(0);
            order.setShopid(book.getBookshopid());
            order.setNum(1);
            order.setPaymenttype(0);
            order.setTime("0");
            order.setTotalmoney(0.0);
            if (bookpurchaseService.insert(order) == 1){
                Gson gson = new Gson();
                Map<String, String> rmap = new HashMap<>();
                rmap.put("added", "true");
                rmap.put("reason", "添加成功!");
                return gson.toJson(rmap);
            }
        }

        Gson gson = new Gson();
        Map<String, String> rmap = new HashMap<>();
        rmap.put("added", "false");
        rmap.put("reason", "添加失败！");
        return gson.toJson(rmap);
    }

    @ResponseBody
    @RequestMapping(value = "/usr/purchase", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String usrPurchase(@RequestParam Map map, HttpSession httpSession, Model model){
        Consumer consumer = (Consumer)httpSession.getAttribute("user");
        String csuTel = consumer.getCsutel();
        List<Bookpurchase> orders = bookpurchaseService.findAllByCsutelAndStatusEx(csuTel, 0);
        Map<String, List<Bookpurchase>> rmap = new HashMap<>();
        rmap.put("orders", orders);
        Gson gson = new Gson();
        return gson.toJson(rmap);
    }

    @ResponseBody
    @RequestMapping(value = "/usr/deleteOneItem", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String usrDeleteOneItem(@RequestParam Map map, Model model){
        String orderNumber = (String) map.get("id");
        Map<String, String> rmap = new HashMap<>();
        if (bookpurchaseService.deleteByOrdernumber(orderNumber) == 1){
            rmap.put("error", "删除成功！");
        }else{
            rmap.put("error", "删除失败！");
        }
        Gson gson = new Gson();
        return gson.toJson(rmap);
    }

    @ResponseBody
    @RequestMapping(value = "/usr/oneOrderInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String usrOneOrderInfo(@RequestParam Map map, Model model){
        String orderNumber = (String) map.get("id");
        Map<String, Bookpurchase> rmap = new HashMap<>();
        Bookpurchase bookpurchase = bookpurchaseService.findOneByOrdernumberEx(orderNumber);
        rmap.put("info", bookpurchase);
        Gson gson = new Gson();
        return gson.toJson(rmap);
    }

    @ResponseBody
    @RequestMapping(value = "/usr/buyBook", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String usrByBook(@RequestParam Map map, Model model){
        String orderNumber = (String) map.get("id");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date today = new Date();
        String time = format.format(today);

        Integer mark = bookpurchaseService.buyBook(1, orderNumber, time);

        Map<String, String> rmap = new HashMap<>();
        Gson gson = new Gson();
        if (mark != null && mark == 1) {
            rmap.put("info", "true");
        }else{
            rmap.put("info", "false");
        }
        return gson.toJson(rmap);
    }

    @ResponseBody
    @RequestMapping(value = "/usr/historyOrder", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String usrHistoryOrder(@RequestParam Map map, HttpSession httpSession, Model model){
        Consumer consumer = (Consumer)httpSession.getAttribute("user");
        List<Bookpurchase> orders = bookpurchaseService.selectAllByCsutelAndStatus(consumer.getCsutel(), 5);
        Map<String, List<Bookpurchase>> rmap = new HashMap<>();
        rmap.put("info", orders);
        Gson gson = new Gson();
        return gson.toJson(rmap);
    }

    @ResponseBody
    @RequestMapping(value = "/usr/addCount", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String usrAddCont(@RequestParam Map map, HttpSession httpSession, Model model){
        String bookId = (String)map.get("bookid");
        Consumer consumer = (Consumer) httpSession.getAttribute("user");
        if (bookId != null && consumer != null) {
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String orderNum = formatter.format(currentTime);
            if (bookpurchaseService.selectAllByBookidAndCsutelAndStatus(bookId, consumer.getCsutel(), 0).size() > 0){
                Gson gson = new Gson();
                Map<String, String> rmap = new HashMap<>();
                rmap.put("added", "false");
                rmap.put("reason", "该书籍已经在购物车中！");
                return gson.toJson(rmap);
            }

            if (bookService.findBookstockByBookid(bookId) < 1){
                Gson gson = new Gson();
                Map<String, String> rmap = new HashMap<>();
                rmap.put("added", "false");
                rmap.put("reason", "库存不足!");
                return gson.toJson(rmap);
            }
            Book book = bookService.selectByPrimaryKey(bookId);
            Bookpurchase order = new Bookpurchase();
            order.setBookid(book.getBookid());
            order.setBookname(book.getBookname());
            order.setCsutel(consumer.getCsutel());
            order.setOrdernumber(orderNum);
            order.setStatus(0);
            order.setShopid(book.getBookshopid());
            order.setNum(1);
            order.setPaymenttype(0);
            order.setTime("0");
            order.setTotalmoney(0.0);
            if (bookpurchaseService.insert(order) == 1){
                Gson gson = new Gson();
                Map<String, String> rmap = new HashMap<>();
                rmap.put("added", "true");
                rmap.put("orderNumber", orderNum);
                return gson.toJson(rmap);
            }
        }

        Gson gson = new Gson();
        Map<String, String> rmap = new HashMap<>();
        rmap.put("added", "false");
        rmap.put("reason", "添加失败！");
        return gson.toJson(rmap);
    }
}
