package com.baomidou.ant.service.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    @Resource
    private Mapper<T> mapper;


    @Override
    public int deleteByPrimaryKey(Example example) {
        return mapper.deleteByPrimaryKey(example);
    }

    @Override
    public int delete(T t) {
        return mapper.delete(t);
    }

    @Override
    public int insert(T t) {
        return mapper.insert(t);
    }

    @Override
    public int insertSelective(T t) {
        return mapper.insertSelective(t);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int selectCount(T t) {
        return mapper.selectCount(t);
    }

    @Override
    public List<T> select(T t) {
        return mapper.select(t);
    }

    @Override
    public T selectOne(T t) {
        return mapper.selectOne(t);
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return mapper.updateByPrimaryKey(t);
    }

    @Override
    public int updateByPrimaryKeySelective(T t) {
        return mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteByExample(Example example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public List<T> selectByExample(Example example) {
        return mapper.selectByExample(example);
    }

    @Override
    public int selectCountByExample(Example example) {
        return mapper.selectCountByExample(example);
    }

    @Override
    public T selectOneByExample(Example example) {
        return mapper.selectOneByExample(example);
    }

    @Override
    public int updateByExample(T t, Example example) {
        return mapper.updateByExample(t, example);
    }

    @Override
    public int updateByExampleSelective(T t, Example example) {
        return mapper.updateByExampleSelective(t, example);
    }

    @Override
    public List<T> selectByExampleAndRowBounds(Example example, RowBounds rowBounds) {
        return mapper.selectByExampleAndRowBounds(example, rowBounds);
    }

    @Override
    public List<T> selectByRowBounds(T t, RowBounds rowBounds) {
        return mapper.selectByRowBounds(t, rowBounds);
    }

    @Override
    public boolean existsWithPrimaryKey(Example example) {
        return mapper.existsWithPrimaryKey(example);
    }

    @Override
    public T selectByPrimaryKey(Example example) {
        return mapper.selectByPrimaryKey(example);
    }


    /**
     * 通用分页查询
     * 通过Example设置条件
     *
     * @param
     * @return
     */
    @Override
    public PageInfo<T> selectPageInfo(BaseExample example) {

        if (example.getNeedPage()) {
            PageHelper.startPage(example.getPageNum() == null ? 0 : example.getPageNum(), example.getPageSize() == null ? 10 : example.getPageSize());
        }
        List<T> list = null;
        list = mapper.selectByExample(example);
        return new PageInfo<>(list);
    }


}