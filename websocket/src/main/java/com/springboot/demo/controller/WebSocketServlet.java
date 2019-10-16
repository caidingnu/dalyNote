package com.springboot.demo.controller;

import com.springboot.demo.entity.LayuiData;
import com.springboot.demo.entity.LayuiDataDecoder;
import com.springboot.demo.entity.LayuiDataEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

//在相对路径中发布端点websocket
@Component
@ServerEndpoint(value = "/websocket", decoders = { LayuiDataDecoder.class }, encoders = { LayuiDataEncoder.class })
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
        System.out.println("目前在线人数："+ webSocketSet.size());
        System.out.println(session.getId());
        try {
            LayuiData layuiData=new LayuiData();
            layuiData.setCount(webSocketSet.size());
            layuiData.setCode(200);
            layuiData.setMsg("在线人数");
            sendMessage(layuiData);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     *  都有消息的时候调用
     * @Description: 给服务器发送消息告知数据库发生变化
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("发生变化"+message);
        LayuiData layuiData=new LayuiData();
        layuiData.setCount(webSocketSet.size());
        layuiData.setCode(300);
        layuiData.setMsg(message);
        try {
            sendMessage(layuiData);
        } catch (IOException e) {
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
     * 发送自定义信号
     *
     */
    public void sendMessage(LayuiData layuiData) throws IOException{
        //群发消息,遍历客户端
        for(WebSocketServlet item: webSocketSet){
            try {
//                item.session.getBasicRemote().sendText("sss");
                item.session.getBasicRemote().sendObject(layuiData);
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 用于发送给指定客户端消息
     *
     * @param message
     */
    public void sendMessage(String sessionId, String message) throws IOException {
        Session session = null;
        WebSocketServlet tempWebSocket = null;
        for (WebSocketServlet webSocket : webSocketSet) {
            if (webSocket.session.getId().equals(sessionId)) {
                tempWebSocket = webSocket;
                session = webSocket.session;
                break;
            }
        }
        if (session != null) {
            tempWebSocket.session.getBasicRemote().sendText(message);
        } else {
           System.out.println("没有找到你指定ID的会话：{}"+ sessionId);
        }
    }

    /**
     * springboot内置tomcat的话，需要配一下这个。。如果没有这个对象，无法连接到websocket
     * 别问为什么。。很坑。。。
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        //这个对象说一下，貌似只有服务器是tomcat的时候才需要配置,具体我没有研究
        return new ServerEndpointExporter();
    }
}