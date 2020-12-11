package com.cdn.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CDN
 * @desc
 * @date 2020/12/11 11:38
 */
public class Util {

   public  List<Integer> list= new ArrayList<Integer>();

   public String get(){

       return "自定义表达式 ";
   }
   public String set(int a){

       return "自定义表达式 "+a;
   }

    /**
     * 序号
     * @param a
     * @return
     */
   public String xuhao(int a){
       list.add(a);
       return "序号 "+list.size();
   }



}
