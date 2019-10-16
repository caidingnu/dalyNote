package com.springboot.demo.mapper.provider;

import com.springboot.demo.entity.Menu;
import org.apache.ibatis.jdbc.SQL;
import java.lang.reflect.Field;
import com.springboot.demo.utils.StrUtils;
import java.util.Map;

/**
 * desc：
 * author CDN
 * create 2019-10-16
 * version 1.0.0
 */
public class MenuProvider {

    private final static String tableName = "menu";

      /**
     * desc: 新增
     * param:
     * author: CDN
     * date: 2019-10-16
     */
    public String insert(Map<String, Object> map) {
        SQL sql = new SQL();
        if (!StrUtils.isNullOrEmpty(map)) {
            sql.INSERT_INTO(tableName);
           if (!StrUtils.isNullOrEmpty(map.get("uuid"))){
               sql.VALUES("uuid","#{uuid}");
           }
           if (!StrUtils.isNullOrEmpty(map.get("menunName"))){
               sql.VALUES("menun_name","#{menunName}");
           }
           if (!StrUtils.isNullOrEmpty(map.get("id"))){
               sql.VALUES("id","#{id}");
           }
           if (!StrUtils.isNullOrEmpty(map.get("pid"))){
               sql.VALUES("pid","#{pid}");
           }
           if (!StrUtils.isNullOrEmpty(map.get("createTime"))){
               sql.VALUES("create_time","#{createTime}");
           }
           if (!StrUtils.isNullOrEmpty(map.get("updateTime"))){
               sql.VALUES("update_time","#{updateTime}");
           }
        }
        return sql.toString();
    }

  

  /**
     * desc:条件删除
     * param:
     * return:
     * author: CDN
     * date: 2019-10-16
     */
    public  String deleteByCondition(Map<String, Object> map) {
        SQL sql = new SQL();
        if (!StrUtils.isNullOrEmpty(map)) {
            sql.DELETE_FROM(tableName);
            for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
                sql.WHERE(stringObjectEntry.getKey()+"="+stringObjectEntry.getValue());
            }
        }
        return sql.toString();
    }

    /**
     * desc: 逻辑删除
     * param: 2 删除  1 正常
     * return:
     * author: CDN
     * date: 2019/9/20
     */
    public String logicDelete(Map<String, Object> map) {
        SQL sql = new SQL();
        if (!StrUtils.isNullOrEmpty(map)){
            sql.UPDATE(tableName).SET("pid =2 ").WHERE("pid=#{pid}");
        }
        return sql.toString();
    }



  /**
     * desc: 根据主键更新
     * param:
     * return:
     * author: CDN
     * date: 2019-10-16
     */
    public String update(Menu menu) {
        SQL sql = new SQL();
        if (!StrUtils.isNullOrEmpty(menu)) {
            sql.UPDATE(tableName).WHERE("uuid=#{uuid}");
            for (Field field : menu.getClass().getDeclaredFields()) {
                if("uuid".equals(field.getName())){
                     continue ;
                 }
                if("serialVersionUID".equals(field.getName())){
                     continue ;
                 }
                sql.SET(StrUtils.camel2Underline(field.getName()) + "=#{" + field.getName() + "}");
            }
        }
        return sql.toString();
    }

   /**
     * desc: 查询
     * date: 2019-10-16
     */
    public  String find(Map<String, Object> map) {
        SQL sql = new SQL().SELECT("*").FROM(tableName);
        if (map != null) {
           if (!StrUtils.isNullOrEmpty(map.get("uuid"))){
            sql.WHERE("uuid=#{uuid}");
	        }
           if (!StrUtils.isNullOrEmpty(map.get("menunName"))){
            sql.WHERE("menun_name=#{menunName}");
	        }
           if (!StrUtils.isNullOrEmpty(map.get("id"))){
            sql.WHERE("id=#{id}");
	        }
           if (!StrUtils.isNullOrEmpty(map.get("createTime"))){
            sql.WHERE("create_time=#{createTime}");
	        }
           if (!StrUtils.isNullOrEmpty(map.get("updateTime"))){
            sql.WHERE("update_time=#{updateTime}");
	        }
        }
        return sql.toString();
    }



    /**
     * desc: 查询单条记录
     * author: CDN
     * date: 2019-10-16
     */
    public String findSingle(Map<String, Object> map) {
        SQL sql = new SQL().SELECT("*").FROM(tableName);
        if (!StrUtils.isNullOrEmpty(map)) {
            sql.WHERE("uuid=#{uuid}");
        }
        return sql.toString();
    }

    
}