package com.yuhao.bookstore.controller;

import com.yuhao.bookstore.entity.Book;
import com.yuhao.bookstore.entity.Consumer;
import com.yuhao.bookstore.entity.Shop;
import com.yuhao.bookstore.service.BookService;
import com.yuhao.bookstore.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ShopController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private BookService bookService;

    private String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();

    @RequestMapping(value = "/shop/login", method = RequestMethod.POST)
    public String shopLogin(@RequestParam Map map, Model model){
        String username = (String) map.get("username");
        String password = (String) map.get("password");

        if (shopService.validateUserLogin(username, password)){
            Shop shop = shopService.getShopByPK(username);
            httpSession.setAttribute("shop", shop);
            List<Book> itemList = bookService.findAllByBookshopidAndBookdeletedFalse(shop.getShopid());
            httpSession.setAttribute("itemList", itemList);
            return "redirect:/shop/itemManage";
        }else{
            model.addAttribute("error", "用户名或密码错误，请重新登录！");
            return "/shop/login";
        }
    }

    @RequestMapping(value = "shop/itemManage", method = RequestMethod.POST)
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
            String filename = UUID.randomUUID().toString();

            String suffix = "";
            int beginIndex = originalFilename.indexOf('.');
            if (beginIndex != -1){
                suffix = originalFilename.substring(beginIndex);
            }
            if (beginIndex == 1 || !suffixes.contains(suffix)){
                model.addAttribute("error", "上传文件只能是jpg, jpeg, png中的一种！");
                return "/shop/item/itemManage";
            }

            File dest = new File(parent, filename + suffix);
            file.transferTo(dest);
            file_path = "cover" + File.separator + filename + suffix;
        }catch (IOException e){
            model.addAttribute("error", "上传文件失败！");
            return "/shop/item/itemManage";
        }

        /*
         * Get posted information
         */
        String title = (String) map.get("title");
        String price = (String) map.get("price");
        String ISBN = (String) map.get("ISBN");
        String info = (String) map.get("info");

        /*
         * Generate BookID
         */
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String bookId = formatter.format(currentTime);

        /*
         * Get shopId
         */
        Shop shop = (Shop) httpSession.getAttribute("shop");
        String shopId = shop.getShopid();

        /*
         * Insert new book
         */
        Book book = new Book();
        book.setBookid(bookId);
        book.setBookname(title);
        book.setBookprice(new Double(price));
        book.setBookisbn(ISBN);
        book.setBookinfor(info);
        book.setBookshopid(shopId);
        book.setBookimage(file_path);
        book.setBookstock(0);
        book.setBookdeleted(false);

        if (bookService.insert(book) == 1){
            List<Book> itemList = bookService.findAllByBookshopidAndBookdeletedFalse(shopId);
            httpSession.setAttribute("itemList", itemList);
            model.addAttribute("error", "添加书籍信息成功!");
        }else{
            model.addAttribute("error", "添加书籍信息失败!");
        }

        return "redirect:/shop/itemManage";
    }

    @RequestMapping(value = "/shop/itemEditState", method = RequestMethod.POST)
    public String shopItemEditState(@RequestParam Map map, Model model){
        String id = (String)map.get("bookid");
        Shop shop = (Shop)httpSession.getAttribute("shop");
        if (bookService.updateBookdeletedByBookid(true, id) == 1){
            model.addAttribute("error", "删除图书信息成功！");
            List<Book> itemList = bookService.findAllByBookshopidAndBookdeletedFalse(shop.getShopid());
            httpSession.setAttribute("itemList", itemList);
            return "/shop/item/itemManage";
        }else{
            model.addAttribute("error", "删除图书信息失败！");
            List<Book> itemList = bookService.findAllByBookshopidAndBookdeletedFalse(shop.getShopid());
            httpSession.setAttribute("itemList", itemList);
        }
        return "redirect:/shop/itemManage";
    }

    @RequestMapping(value = "/shop/itemEdit", method=RequestMethod.GET)
    public String shopItemEdit(@RequestParam Map map, Model model){
        String bookid = (String)map.get("bookid");
        Book book = bookService.selectByPrimaryKey(bookid);
        model.addAttribute("item", book);
        return "/shop/item/itemEdit";
    }
}
