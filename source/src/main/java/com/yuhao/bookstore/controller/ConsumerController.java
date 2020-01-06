package com.yuhao.bookstore.controller;

import com.yuhao.bookstore.entity.Consumer;
import com.yuhao.bookstore.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String Sender; //读取配置文件中的参数

    public ConsumerService getConsumerService() {
        return consumerService;
    }

    public void setConsumerService(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @RequestMapping(value = "/usr/login", method = RequestMethod.POST)
    public String usrLogin(@RequestParam Map map, Model model){
        String username = (String) map.get("username");
        String password = (String) map.get("password");

        if (consumerService.validateUserLogin(username, password)){
            Consumer consumer = consumerService.getConsumerByPK(username);
            httpSession.setAttribute("user", consumer);
            return "redirect:/usr/index";
        }else{
            model.addAttribute("error", "用户名或密码错误，请重新登录！");
            return "/usr/login";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/usr/getLoginCustomer", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String getLoginCustomer(HttpSession httpSession){
        Gson gson = new Gson();
        Map<String, Consumer> rMap = new HashMap<String, Consumer>();
        rMap.put("loginCustomer", (Consumer)httpSession.getAttribute("user"));
        return gson.toJson(rMap);
    }

    @RequestMapping(value = "/usr/forget", method = RequestMethod.POST)
    public String getUserForget(@RequestParam Map map, Model model){
        String tel = (String) map.get("userName");
        String email = (String) map.get("email");

        Consumer consumer = consumerService.selectOneByCsutelAndCsuemail(tel, email);

        if (consumer == null){
            model.addAttribute("error", "帐号不存在或邮箱不正确！");
            return "usr/forget";
        }else{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(Sender);
            message.setTo(email);
            message.setSubject("YH网上书店-密码找回");
            StringBuilder sb = new StringBuilder();
            sb.append(consumer.getCsuname() + "用户您好！您的注册密码是：" + consumer.getCsupass() + "。感谢您使用YH网上书店！");
            message.setText(sb.toString());
            mailSender.send(message);
            model.addAttribute("error", "密码已发到您的邮箱,请查收！");
        }
        return "usr/login";
    }

    @RequestMapping(value = "/usr/register", method = RequestMethod.POST)
    public String usrRegister(@RequestParam Map map, Model model) {
        String tel = (String) map.get("username");
        String email = (String) map.get("email");
        String pass = (String) map.get("password");
        String name = "刘森";

        Consumer consumer = new Consumer();

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = formatter.format(currentTime);

        consumer.setCsuadd("北京理工大学");
        consumer.setCsubalance(0.0);
        consumer.setCsupass(pass);
        consumer.setCsutel(tel);
        consumer.setCsuemail(email);
        consumer.setDate(date);
        consumer.setCsuname(name);

        if (consumerService.insert(consumer) == 1){
            model.addAttribute("error", "注册成功！");
        }else{
            model.addAttribute("error", "注册失败！");
        }
        return "redirect:/usr/login";
    }
}
