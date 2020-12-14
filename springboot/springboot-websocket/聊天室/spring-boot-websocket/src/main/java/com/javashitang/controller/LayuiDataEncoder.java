package com.javashitang.controller;

import com.alibaba.fastjson.JSON;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;

public class LayuiDataEncoder implements javax.websocket.Encoder.Text<LayuiData> {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(EndpointConfig arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public String encode(LayuiData user) throws EncodeException {
        return JSON.toJSONString(user);
    }

}