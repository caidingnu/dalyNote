package com.springboot.demo.controller;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

//在相对路径中发布端点websocket
@Component
@ServerEndpoint("/websocket")
public class WebSocketServlet {
//    MyThread thread1=new MyThread();
//
//    Thread thread=new Thread(thread1);
    //用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServlet> webSocketSet = new CopyOnWriteArraySet<>();
    private  javax.websocket.Session session=null;

    /**
     * @ClassName: onOpen
     * @Description: 开启连接的操作
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session=session;
        webSocketSet.add(this);
        System.out.println(webSocketSet.size());
        //开启一个线程对数据库中的数据进行轮询
//        thread.start();

    }

    /**
     * @ClassName: onClose
     * @Description: 连接关闭的操作
     */
    @OnClose
    public void onClose(){
//        thread1.stopMe();
        webSocketSet.remove(this);
    }

    /**
     * @ClassName: onMessage
     *      哪里需要推送就调用onMessage
     *
     * @Description: 给服务器发送消息告知数据库发生变化
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("发生变化"+message);
        try {
            sendMessage(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @ClassName: OnError
     * @Description: 出错的操作
     */
    @OnError
    public void onError(Throwable error){
        System.out.println(error);
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @throws IOException
     * 发送自定义信号，“1”表示告诉前台，数据库发生改变了，需要刷新
     *
     *
     */
    public void sendMessage(String message) throws IOException{
        //群发消息
        for(WebSocketServlet item: webSocketSet){
            item.session.getBasicRemote().sendText(message);
        }
    }
}