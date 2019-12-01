//package com.springboot.demo.controller;
//
//import com.springboot.demo.service.MenuService;
//import com.springboot.demo.service.impl.MenuServiceImpl;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
//@Service
//public class MyThread implements Runnable{
//
//
//    private int sum;
//    private int new_sum;
//    private boolean stopMe = true;
//    public void stopMe() {
//        stopMe = false;
//    }
//
//    @Override
//    public void run()  {
//        MenuServiceImpl menuService = new MenuServiceImpl();
//        sum= menuService.count();
//        WebSocketServlet wbs=new WebSocketServlet();
//        while(stopMe){
//            new_sum= menuService.count();
//            if(sum!=new_sum){
//                System.out.println("change");
//                sum=new_sum;
//                wbs.onMessage(sum);
//            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }
//}