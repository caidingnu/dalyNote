package com.example.demo.controller;

import com.example.demo.annotation.SwitchDataSource;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author CDN
 * @desc
 * @date 2020/07/09 13:45
 */
@RestController
public class TestController {


    @Autowired
    UserMapper userMapper;

    @Autowired
    ProductMapper productMapper;


    /**
     * 不加数据源注解，使用默认数据源
     *
     * @return
     */
    @GetMapping("userList")
    public Object user() {
        return userMapper.selectByExample(null);
    }

    /**
     * des:  数据源  1
     * param:
     * <p>
     * return:
     * author: CDN
     * date: 2020/7/9
     */
    @GetMapping("insertUser")
    @Transactional
    public int insertUser() {
        User user = new User();
        user.setName("是是是" + UUID.randomUUID());
        int insert = userMapper.insert(user);
//       if (insert==1){
        //        测试事务
//           int x=1/0;
//       }

        return insert;
    }

//    =============   数据源  2   操作  =======================

    /**
     * desc:  @SwitchDataSource 指定数据源
     * param:
     * return:
     * author: CDN
     * date: 2020-7-9
     */
    @GetMapping("productList")
    @SwitchDataSource("slave")
    public Object list() {
        return productMapper.selectByExample(null);
    }


    /**
     * 数据源  slave
     *
     * @return
     */
    @GetMapping("insertPro")
    @SwitchDataSource("slave")
    @Transactional
    public int insertPro() {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString().substring(0, 5));
        product.setName("电视" + UUID.randomUUID().toString().substring(0, 4));
        int insert = productMapper.insert(product);
//        if (insert==1){
//        测试事务
//            int x=1/0;
//        }
        return insert;
    }
}
