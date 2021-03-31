package com.cdn.springaoplog.dao;

import com.cdn.springaoplog.entity.UserinfoPo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * dao层
 *
 * @author itar
 * @email wuhandzy@gmail.com
 * @date 2018-12-21 05:39:43
 * @since jdk1.8
 */
@Mapper
public interface UserinfoDao {


    Page<UserinfoPo> selectPaged(RowBounds rowBounds);

    UserinfoPo selectByPrimaryKey(Integer userid);

    Integer deleteByPrimaryKey(Integer userid);

    Integer insert(UserinfoPo userinfo);

    Integer insertSelective(UserinfoPo userinfo);

    Integer insertSelectiveIgnore(UserinfoPo userinfo);

    Integer updateByPrimaryKeySelective(UserinfoPo userinfo);

    Integer updateByPrimaryKey(UserinfoPo userinfo);

    Integer batchInsert(List<UserinfoPo> list);

    Integer batchUpdate(List<UserinfoPo> list);

    /**
     * 存在即更新
     *
     * @param
     * @return
     */
    Integer upsert(UserinfoPo userinfo);

    /**
     * 存在即更新，可选择具体属性
     *
     * @param
     * @return
     */
    Integer upsertSelective(UserinfoPo userinfo);

    List<UserinfoPo> query();

    Long queryTotal();

    Integer deleteBatch(List<Integer> list);


}