package com.cdn.common.entity;

import com.cdn.common.utils.Util;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.jxls.util.TransformerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @author CDN
 * @desc
 * @date 2020/12/11 15:17
 */
public class ExeExport {

    public static void main(String[] args) throws IOException {
        new ExeExport().userCommond();
    }

    /**
     * desc:  常规的导出
     * param:
     * author: CDN
     * date: 2020/12/11
     */
    public void exportCommon() {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Employee employee = new Employee();
            employee.setBirthDate(new Date());
            employee.setPayment(i);
            employee.setBonus(i);
            employees.add(employee);
        }
        Base base = new Base();
        base.setName("项目名称");
        base.setEmployeesList(employees);

        try (InputStream is = Employee.class.getClassLoader().getResourceAsStream("a.xlsx")) {
            try (OutputStream os = new FileOutputStream("target/object_collection_output.xls")) {
                Context context = new Context();
                context.putVar("employees", base);
                JxlsHelper.getInstance().processTemplate(is, os, context);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * desc:  用户自定义指令  方式1
     * param:
     * author: CDN
     * date: 2020/12/11
     */
    public void userCommond() {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Employee employee = new Employee();
            employee.setBirthDate(new Date());
            employee.setPayment(i);
            employee.setBonus(i);
            employees.add(employee);
        }
        Base base = new Base();
        base.setName("项目名称");
        base.setEmployeesList(employees);

        try (InputStream is = Employee.class.getClassLoader().getResourceAsStream("a.xlsx")) {
            try (OutputStream os = new FileOutputStream("target/object_collection_output.xls")) {
                Context context = new Context();
                context.putVar("employees", base);

                JxlsHelper jxlsHelper = JxlsHelper.getInstance();
                Transformer transformer = jxlsHelper.createTransformer(is, os);
                JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) transformer.getTransformationConfig()
                        .getExpressionEvaluator();
                Map<String, Object> functionMap = new HashMap<>();
                functionMap.put("util", new Util());   //自定义命名空间  使用   ${util:get()}

                JexlBuilder jb = new JexlBuilder();
                jb.namespaces(functionMap);
                JexlEngine je = jb.create();
                evaluator.setJexlEngine(je);

                jxlsHelper.setUseFastFormulaProcessor(false).processTemplate(context, transformer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * desc:  用户自定义指令  方式2
     * param:
     * author: CDN
     * date: 2020/12/11
     */
    public void userCommond2() throws IOException {
        try (InputStream is = Employee.class.getClassLoader().getResourceAsStream("a.xlsx")) {
            try (OutputStream os = new FileOutputStream("target/object_collection_output.xls")) {
                List<Employee> employees = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    Employee employee = new Employee();
                    employee.setBirthDate(new Date());
                    employee.setPayment(i);
                    employee.setBonus(i);
                    employees.add(employee);
                }
                Base base = new Base();
                base.setName("项目名称");
                base.setEmployeesList(employees);

                Transformer transformer = TransformerFactory.createTransformer(is, os);
                AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
                List<Area> xlsAreaList = areaBuilder.build();
                Area xlsArea = xlsAreaList.get(0);
                Context context = new Context();
                context.putVar("x", 5);
                context.putVar("y", 10);
                context.putVar("employees", base);

                JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) transformer.getTransformationConfig().getExpressionEvaluator();
                Map<String, Object> functionMap = new HashMap<>();
                functionMap.put("util", new Util());  //自定义命名空间  使用   ${util:get()}
                JexlEngine customJexlEngine = new JexlBuilder().namespaces(functionMap).create();
                evaluator.setJexlEngine(customJexlEngine);
                xlsArea.applyAt(new CellRef("Sheet1!A1"), context);    // Sheet1的A1行开始
                transformer.write();
            }
        }
    }
}
