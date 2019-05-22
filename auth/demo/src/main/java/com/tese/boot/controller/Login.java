package com.tese.boot.controller;

import com.tese.boot.entity.User;
import com.tese.boot.entity.pojo.LoginReturn;
import com.tese.boot.mapper.UserMapper;
import com.tese.boot.util.BaseResponseEntity;
import com.tese.boot.util.Constant;
import com.tese.boot.util.EmptyUtil;
import com.tese.boot.util.JsonUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 功能简述：
 *
 * @author CDN
 * @create 2019-05-20 17:29
 * @VERSION 1.0.0
 */
@RestController
@CrossOrigin
public class Login {

    @Resource
    UserMapper userMapper;


    @RequestMapping("login")
    public String login(User user, HttpServletRequest request) {
        List<LoginReturn> loginReturn = null;
        String id = userMapper.selectId(user);
        if (EmptyUtil.isEmpty(id)) {
            return JsonUtil.toJSon(new BaseResponseEntity(Constant.NO_USER));
        } else {
            HttpSession session = request.getSession();
            loginReturn = userMapper.selectlogin(user);
            session.setAttribute(user.getLoginName(),loginReturn);
        }
        if (loginReturn == null) {
            return JsonUtil.toJSon(new BaseResponseEntity(Constant.CODE_EMPTY));
        }
        return JsonUtil.toJSon(new BaseResponseEntity(loginReturn, loginReturn.size()));
    }


    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: CDN
     * @Date: 2019/5/20
     */
    @RequestMapping("acc")
    public String addAcc(HttpServletRequest request){
        List<User> u=userMapper. selectAcc();
        return JsonUtil.toJSon(new BaseResponseEntity(u,u.size()));
    }

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: CDN
 * @Date: 2019/5/20
 */
@RequestMapping("auth")
public String auth(Integer id){
    List<LoginReturn> loginReturn = userMapper.selectByid(id);
    return JsonUtil.toJSon(new BaseResponseEntity(loginReturn, loginReturn.size()));
}
}
