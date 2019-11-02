package com.study.privoder.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * desc：
 * author CDN
 * create 2019-06-30 13:12
 * version 1.0.0
 */
public class ComAndEmpProvider {


    /**
     * desc:
     * param:
     * return:
     * author: CDN
     * date: 2019/6/30
     */
    public String inner() {
        return new SQL() {{
            SELECT("*");
            FROM("emp");
            INNER_JOIN("com_emp on emp.emp_id=com_emp.emp_id");
            INNER_JOIN("company on com_emp.company_id=company.company_id");
        }}.toString();
    }

}
