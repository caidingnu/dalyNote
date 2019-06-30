package com.study.privoder.mapper.provider;

import com.study.privoder.entity.Emp;
import org.apache.ibatis.javassist.runtime.Desc;
import org.apache.ibatis.jdbc.SQL;
import org.thymeleaf.util.StringUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * desc：
 * author CDN
 * create 2019-06-29 16:05
 * version 1.0.0
 */
public class EmpProvider {
    private String tableName = "emp";
    private String emp_id = "emp_id";
    private String emp_name = "emp_name";
    private String emp_age = "emp_age";
    private String sex = "sex";


    //    或者查询
    public String or(Emp emp) {
        return new SQL() {{
            SELECT("*");
            FROM(tableName);
            WHERE("emp_name=#{empName}");
            OR();
            WHERE("emp_name=" + "蔡定努" + "");
        }}.toString();
    }


    //    and查询
    public String and(Emp emp) {
        return new SQL() {{
            SELECT("*");
            FROM(tableName);
            WHERE("emp_name=#{empName}");
            if (emp.getEmpAge() != null) {
                AND();
                WHERE("emp_age=#{empAge}");
            }
        }}.toString();
    }


    /**
     * desc:
     * param:
     * author: CDN
     * date: 2019/6/29
     */
    public String add(Emp emp) {
        return new SQL() {{
            INSERT_INTO(tableName);
            VALUES("emp_id", "#{empId}");
            VALUES("emp_name", "#{empName}");
            VALUES("emp_age", "#{empAge}");
            VALUES("sex", "#{sex}");
        }}.toString();
    }

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/6/29
     */
    public String all(String keyword) {
        return new SQL() {{
            SELECT("*");
            FROM(tableName);
            if (null != keyword) {
                WHERE("emp_name like '%" + keyword + "%'");
            }
            ORDER_BY("emp_id desc");
        }}.toString();
    }

    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/6/29
     */
    public String condition(String name) {

        return new SQL() {{
            SELECT("*");
            FROM(tableName);
//            WHERE("emp_name=#{name}");
            WHERE(emp_name+"=#{name}");
        }}.toString();
    }


    /**
     * desc:
     * param:
     * author: CDN
     * date: 2019/6/29
     */
    public String delete(String id) {
        return new SQL() {{
            DELETE_FROM(tableName);
            WHERE("emp_id=#{id}");
        }}.toString();
    }


    /**
     * desc:
     * param:
     * author: CDN
     * date: 2019/6/29
     */

    public String update(Emp emp) {
        return new SQL() {{
            UPDATE(tableName);
            if (!StringUtils.isEmpty(emp.getEmpName())) {
                SET("emp_name=#{empName}");
            }
            if (!StringUtils.isEmpty(emp.getEmpAge())) {
                SET("emp_age=#{empAge}");
            }
            if (!StringUtils.isEmpty(emp.getSex())) {
                SET("sex = #{sex}");
            }
            WHERE("emp_id =#{empId}");
        }}.toString();
    }


    /**
     * 批量插入
     *
     * @param map
     * @return
     */
    public String batchAdd(Map map) {
        List<Emp> urlBlack = (List<Emp>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO emp ");
        sb.append("(emp_id, emp_name, emp_age, sex) ");
        sb.append("VALUES ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].empId},#'{'list[{0}].empName},#'{'list[{0}].empAge},#'{'list[{0}].sex})");
        for (int i = 0; i < urlBlack.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < urlBlack.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
