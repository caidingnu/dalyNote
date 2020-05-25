package com.cdn.client;

import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Basis {
     Socket socket = null;

    public Basis() throws IOException {
        socket = new Socket("127.0.0.1", 88);
    }

    public static void main(String[] args) throws IOException {
        new Basis();

    }


    /**
     * desc:
     * param:
     * author: CDN
     * date: 2020-5-25
     */



}