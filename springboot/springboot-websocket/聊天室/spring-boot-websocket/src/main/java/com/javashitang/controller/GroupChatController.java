package com.javashitang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint(value = "/groupChat/{sid}/{username}", decoders = {LayuiDataDecoder.class}, encoders = {LayuiDataEncoder.class})
public class GroupChatController {

    // 保存 组id->组成员 的映射关系
    private static ConcurrentHashMap<String, List<Session>> groupMemberInfoMap = new ConcurrentHashMap<>();

    // 收到消息调用的方法，群成员发送消息
    @OnMessage
    public void onMessage(@PathParam("sid") String sid,
                          @PathParam("username") String username, String message) throws IOException {
        List<Session> sessionList = groupMemberInfoMap.get(sid);
        // 先一个群组内的成员发送消息
        LayuiData layuiData = new LayuiData();
        layuiData.setCount(sessionList.size());
        layuiData.setCode(200);
        layuiData.setMsg(username+":"+message);
        sendMessage(layuiData, sid);
    }

    // 建立连接调用的方法，群成员加入
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid, @PathParam("username") String username) throws IOException, EncodeException {
        List<Session> sessionList = groupMemberInfoMap.get(sid);
        if (sessionList == null) {
            sessionList = new ArrayList<>();
            groupMemberInfoMap.put(sid, sessionList);
        }
        sessionList.add(session);
        LayuiData layuiData = new LayuiData();
        layuiData.setCount(sessionList.size());
        layuiData.setCode(200);
        layuiData.setMsg("=================欢迎" + username + "加入===================");
        sendMessage(layuiData, sid);
        log.info("Connection connected");
        log.info("sid: {}, sessionList size: {}", sid, sessionList.size());
    }

    // 关闭连接调用的方法，群成员退出
    @OnClose
    public void onClose(Session session, @PathParam("sid") String sid, @PathParam("username") String username) {
        List<Session> sessionList = groupMemberInfoMap.get(sid);
        sessionList.remove(session);
        LayuiData layuiData = new LayuiData();
        layuiData.setCount(sessionList.size());
        layuiData.setCode(200);
        layuiData.setMsg("=================" + username + "退出===================");
        sendMessage(layuiData, sid);
        log.info("Connection closed");
        log.info("sid: {}, sessionList size: {}", sid, sessionList.size());
    }

    // 传输消息错误调用的方法
    @OnError
    public void OnError(Throwable error) {
        log.info("Connection error");
    }


    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     *
     * @throws IOException 发送自定义信号
     */
    public void sendMessage(LayuiData layuiData, String sid)  {
        List<Session> sessionList = groupMemberInfoMap.get(sid);
        for (Session session : sessionList) {
            try {
                session.getBasicRemote().sendObject(layuiData);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }
}
