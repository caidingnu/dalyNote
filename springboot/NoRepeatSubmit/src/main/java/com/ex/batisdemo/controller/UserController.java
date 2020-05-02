package com.ex.batisdemo.controller;

import com.ex.batisdemo.anno.NoRepeatSubmit;
import com.ex.batisdemo.domain.Result;
import com.ex.batisdemo.domain.User;
import com.ex.batisdemo.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 分页查询数据
     */
    @RequestMapping("/select_paged")
    @NoRepeatSubmit
    public Object selectPaged() {
        PageHelper.startPage(0, 1);
        List<User> userPage = userService.selectPaged();
        PageInfo<User> p = new PageInfo<>(userPage);

        Result<Page<User>> pageResult = new Result<>();
        pageResult.setData(p);
        pageResult.setMessage("成功");
        return pageResult;
    }



    /**
     * 通过id查询
     *
     * @return
     */
    @RequestMapping("/select_by_id")
    public Result<User> selectByPrimaryKey(Integer userId) {
        Result<User> result = new Result<>();
        User po = userService.selectByPrimaryKey(userId);
        result.setData(po);
        return result;
    }

    /**
     * 通过ID删除
     *
     * @return
     */
    @RequestMapping("/delete_by_id")
    public Result<Integer> deleteByPrimaryKey(Integer userId) {
        Result<Integer> result = new Result<>();
        Integer num = userService.deleteByPrimaryKey(userId);
        result.setData(num);
        return result;
    }

    /**
     * 新增数据
     *
     * @return
     */
    @RequestMapping("/save_user")
    public Result<Integer> insert(User user) {
        Result<Integer> result = new Result<>();
        Integer num = userService.insertSelective(user);
        result.setData(num);
        return result;
    }

    /**
     * 修改数据
     *
     * @return
     */
    @RequestMapping("/update_user")
    public Result<Integer> updateByPrimaryKeySelective(User user) {
        Result<Integer> result = new Result<>();
        Integer num = userService.updateByPrimaryKeySelective(user);
        result.setData(num);
        return result;
    }


    /**
     * 查询列表
     *
     * @return
     */
    @RequestMapping("/query_list")
    public Result<List<User>> queryByCondition(User user) {
        Result<List<User>> result = new Result<>();
        List<User> list = userService.query(user);
        result.setData(list);
        return result;
    }

}
