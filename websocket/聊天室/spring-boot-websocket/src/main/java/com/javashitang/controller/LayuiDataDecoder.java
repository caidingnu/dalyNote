package com.javashitang.controller;

import com.alibaba.fastjson.JSON;

import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;

public class LayuiDataDecoder implements javax.websocket.Decoder.Text<LayuiData> {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(EndpointConfig arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public LayuiData decode(String layuiData) throws DecodeException {
        return JSON.parseObject(layuiData, LayuiData.class);
    }

    @Override
    public boolean willDecode(String arg0) {
        return true;
    }

}