package com.yuhao.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/usr/login")
    public String usrLogin(Model model){
        return "usr/login";
    }

    @RequestMapping("/usr/register")
    public String usrRegister(){
        return "usr/register";
    }

    @RequestMapping("/usr/forget")
    public String usrForget(){
        return "usr/forget";
    }

    @RequestMapping("/usr/bookDetail")
    public String usrBookDetail(){
        return "usr/bookDetail";
    }

    @RequestMapping("/usr/count")
    public String count() {
        return "usr/count";
    }

    @RequestMapping("/usr/cart")
    public String cart() {
        return "usr/cart";
    }

    @RequestMapping("/usr/index")
    public String mainPage() {
        return "usr/index";
    }

    @RequestMapping("/usr/order")
    public String order() {
        return "usr/order";
    }

    @RequestMapping("/shop/login")
    public String shopLogin(){  return "shop/login";}

    @RequestMapping("/shop/dashboard")
    public String shopDashboard(){  return "/shop/dashboard";}

    @RequestMapping("/shop/nav")
    public String shopNav(){  return "/shop/nav";}

    @RequestMapping("/shop/itemManage")
    public String shopItemManager(){
        return "shop/item/itemManage";
    }

    @RequestMapping("/shop/footer")
    public String shopFooter(){
        return "/shop/footer";
    }
}
