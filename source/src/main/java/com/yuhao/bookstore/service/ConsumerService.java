package com.yuhao.bookstore.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.yuhao.bookstore.entity.Consumer;
import com.yuhao.bookstore.mapper.ConsumerMapper;

import java.util.List;

@Service
public class ConsumerService{

    @Resource
    private ConsumerMapper consumerMapper;

    
    public int deleteByPrimaryKey(String csutel) {
        return consumerMapper.deleteByPrimaryKey(csutel);
    }

    
    public int insert(Consumer record) {
        return consumerMapper.insert(record);
    }

    
    public int insertSelective(Consumer record) {
        return consumerMapper.insertSelective(record);
    }

    
    public Consumer selectByPrimaryKey(String csutel) {
        return consumerMapper.selectByPrimaryKey(csutel);
    }

    
    public int updateByPrimaryKeySelective(Consumer record) {
        return consumerMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Consumer record) {
        return consumerMapper.updateByPrimaryKey(record);
    }

    public boolean validateUserLogin(String Tel, String pass){
        Consumer consumer =  consumerMapper.findOneByCsutel(Tel);

        if(consumer != null && pass.equals(consumer.getCsupass())){
            return true;
        }
        return false;
    }

    public Consumer getConsumerByPK(String tel){
        return consumerMapper.findOneByCsutel(tel);
    }

    public List<String> getConsumerPassByTel(String tel){
        return consumerMapper.selectcsupassByCsutel(tel);
    }

    public Consumer selectOneByCsutelAndCsuemail(String tel, String email){
        return consumerMapper.selectOneByCsutelAndCsuemail(tel, email);
    }
}
