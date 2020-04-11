package com.cdn.springaoplog.controller;

import com.cdn.springaoplog.base.BaseResponseEntity;
import com.cdn.springaoplog.dao.UserinfoDao;
import com.cdn.springaoplog.entity.Result;
import com.cdn.springaoplog.entity.UserinfoPo;
import com.cdn.springaoplog.service.UserinfoService;
import com.cdn.springaoplog.utils.JsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * @date 2018-12-21 05:39:43
 * @since jdk 1.8
 */
@RestController
@RequestMapping("/userinfo")
@CrossOrigin("*")
public class UserinfoController {
    @Autowired
    private UserinfoService userinfoService;

    @Resource
    private UserinfoDao userinfoDao;

    /**
     * 分页查询数据
     */
    @RequestMapping("/select_paged")
    public String selectPaged(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        PageHelper.startPage(page, pageSize);
        List<UserinfoPo> list = userinfoDao.query();
        PageInfo<UserinfoPo> p = new PageInfo<UserinfoPo>(list);
        long total = p.getTotal();
        int sum = (int) total;
        List<UserinfoPo> points = p.getList();
        return JsonUtil.toJSon(new BaseResponseEntity(points, sum));//返回成功
    }

    /**
     * 通过id查询
     *
     * @return
     */
    @RequestMapping("/select_by_id")
    public Result<UserinfoPo> selectByPrimaryKey(Integer userid) {
        Result<UserinfoPo> result = new Result<>();
        UserinfoPo po = userinfoService.selectByPrimaryKey(2);
        result.setData(po);
        return result;
    }

    /**
     * 通过ID删除
     *
     * @return
     */
    @RequestMapping("/delete_by_id")
    public Result<Integer> deleteByPrimaryKey(String userid) {
        Integer uid = Integer.valueOf(userid);
        System.out.println(userid);
        Result<Integer> result = new Result<>();
        Integer num = userinfoService.deleteByPrimaryKey(uid);
        result.setData(num);
        return result;
    }

    /**
     * 新增数据
     *
     * @return
     */
    @RequestMapping("/save_userinfo")
    public Result<Integer> insert(UserinfoPo userinfo) {
        Result<Integer> result = new Result<>();
        Integer num = userinfoService.insertSelective(userinfo);
        result.setData(num);
        return result;
    }

    /**
     * 修改数据
     *
     * @return
     */
    @RequestMapping("/update_userinfo")
    public Result<Integer> updateByPrimaryKeySelective(UserinfoPo userinfo) {
        Result<Integer> result = new Result<>();
        Integer num = userinfoService.updateByPrimaryKeySelective(userinfo);
        result.setData(num);
        return result;
    }


    /**
     * 查询列表
     *
     * @return
     */
    @RequestMapping("/query_list")
    public String queryByCondition(UserinfoPo userinfo) {


        return null;
    }

}
