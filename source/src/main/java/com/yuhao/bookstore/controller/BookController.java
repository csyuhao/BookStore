package com.yuhao.bookstore.controller;

import com.google.gson.Gson;
import com.yuhao.bookstore.entity.Book;
import com.yuhao.bookstore.entity.Consumer;
import com.yuhao.bookstore.entity.Shop;
import com.yuhao.bookstore.service.BookService;
import com.yuhao.bookstore.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    private String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();


    @RequestMapping(value = "/shop/itemEdit", method = RequestMethod.POST)
    public String shopItemEdit(@RequestParam Map map, @RequestParam MultipartFile file, HttpSession httpSession, Model model){
        String file_path = null;
        try{
           /*
            * Save book cover
            */
           List<String> suffixes = new ArrayList<>();
           suffixes.add(".jpg");
           suffixes.add(".jpeg");
           suffixes.add(".png");

           String dir = path + File.separator + "cover";
           File parent = new File(dir);
           if (!parent.exists()){
               parent.mkdirs();
           }

           String originalFilename = file.getOriginalFilename();

           if (!originalFilename.equals("")) {
               String filename = UUID.randomUUID().toString();

               String suffix = "";
               int beginIndex = originalFilename.indexOf('.');
               if (beginIndex != -1) {
                   suffix = originalFilename.substring(beginIndex);
               }
               if (!suffixes.contains(suffix)) {
                   model.addAttribute("error", "上传文件只能是jpg, jpeg, png中的一种！");
                   return "/shop/item/itemManage";
               }

               File dest = new File(parent, filename + suffix);
               file.transferTo(dest);
               file_path = "cover" + File.separator + filename + suffix;
           }
        }catch (IOException e){
           model.addAttribute("error", "上传文件失败！");
           return "/shop/item/itemManage";
        }

        Shop shop = (Shop) httpSession.getAttribute("shop");
        int stock = Integer.parseInt((String) map.get("num"));
        String title = (String) map.get("title");
        String price = (String) map.get("price");
        String info = (String) map.get("sellPoint");
        String bookId = (String) map.get("id");

        Book book = new Book();
        book.setBookname(title);
        book.setBookprice(new Double(price));
        book.setBookinfor(info);
        book.setBookimage(file_path);
        book.setBookstock(stock);

        if (bookService.updateByBookid(book, bookId) == 1) {
            List<Book> itemList = bookService.findAllByBookshopidAndBookdeletedFalse(shop.getShopid());
            httpSession.setAttribute("itemList", itemList);
            model.addAttribute("error", "图书信息更新成功！");
        }
        else{
            List<Book> itemList = bookService.findAllByBookshopidAndBookdeletedFalse(shop.getShopid());
            httpSession.setAttribute("itemList", itemList);
            model.addAttribute("error", "图书信息更新失败！");
        }
        return "/shop/item/itemManage";
    }

    @ResponseBody
    @RequestMapping(value = "/usr/getAllBookSimples", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String getUsrBookSimples(@RequestParam Map map, Model model){
        Gson gson = new Gson();
        Map<String, List<Book>> rmap = new HashMap<>();
        List<Book> books = bookService.findAllByBookdeletedFalse();
        rmap.put("bookSimpleList", books);
        return gson.toJson(rmap);
    }

    @ResponseBody
    @RequestMapping(value = "/usr/getOneBookDetail", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String getUsrBookDetail(@RequestParam Map map, HttpSession httpSession, Model model){
        String bookId = (String) map.get("bookid");
        Book book = bookService.selectByPrimaryKey(bookId);

        Consumer consumer = (Consumer) httpSession.getAttribute("user");

        String addr = "北京市";
        if(consumer != null){
            addr = consumer.getCsuadd();
        }

        Map<String, Book> rmap_book = new HashMap<>();
        Map<String, String> rmap_addr = new HashMap<>();
        Map<String, String> error = new HashMap<>();

        Gson gson = new Gson();
        if (book == null){
            error.put("error", "不存在该图书！");
            return gson.toJson(error);
        }

        rmap_book.put("book", book);
        rmap_addr.put("addr", addr);

        List<String> res = new ArrayList<>();
        res.add(gson.toJson(rmap_book));
        res.add(gson.toJson(rmap_addr));
        return res.toString();
    }
}
