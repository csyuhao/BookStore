package com.yuhao.bookstore.service;

import com.yuhao.bookstore.entity.Consumer;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.yuhao.bookstore.entity.Shop;
import com.yuhao.bookstore.mapper.ShopMapper;

@Service
public class ShopService {

    @Resource
    private ShopMapper shopMapper;


    public int deleteByPrimaryKey(String shopid) {
        return shopMapper.deleteByPrimaryKey(shopid);
    }


    public int insert(Shop record) {
        return shopMapper.insert(record);
    }


    public int insertSelective(Shop record) {
        return shopMapper.insertSelective(record);
    }


    public Shop selectByPrimaryKey(String shopid) {
        return shopMapper.selectByPrimaryKey(shopid);
    }


    public int updateByPrimaryKeySelective(Shop record) {
        return shopMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(Shop record) {
        return shopMapper.updateByPrimaryKey(record);
    }

    public boolean validateUserLogin(String shopId, String pass){
        Shop shop = shopMapper.findOneByShopid(shopId);

        if(shop != null && pass.equals(shop.getShoppass())){
            return true;
        }
        return false;
    }

    public Shop getShopByPK(String shopId){
        return shopMapper.findOneByShopid(shopId);
    }
}

