package com.springboot.demo.controller;

import com.springboot.demo.entity.LayuiData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Description: websocket消息发送类。服务端主动发送
 * @Author: Gentle
 * @date 2018/9/5  19:30
 */
@RestController
public class SocketController {
    @Resource
    WebSocketServlet myWebSocket;

    @RequestMapping("many")
    public String helloManyWebSocket() throws IOException {
        //向所有人发送消息
        LayuiData layuiData = new LayuiData();
        layuiData.setCount(0);
        layuiData.setCode(400);
        layuiData.setMsg("\"你好~！\"");
        myWebSocket.sendMessage(layuiData);
        return "发送成功";
    }

    @RequestMapping("one")
    public String helloOneWebSocket(String sessionId) throws IOException {
        //向某个人发送消息
        myWebSocket.sendMessage(sessionId, "你好~！，单个用户");

        return "发送成功";
    }


}