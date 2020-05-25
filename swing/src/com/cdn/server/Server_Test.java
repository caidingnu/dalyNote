package com.cdn.server;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class Server_Test extends Thread {
    ServerSocket server = null;
    Socket socket = null;
    JFrame jf = null;
    JLabel label1 = null;
    JPanel jPanel = null;
    int x = 0;
    int y = 0;

    public Server_Test(int port) {
        try {
            server = new ServerSocket(port);
            jf = new JFrame();
            jf.setTitle("服务端");
            jf.setLocationRelativeTo(null);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setBounds(100, 200, 300, 300);
            jPanel = new JPanel();
            jf.add(jPanel);
            jf.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            label1 = new JLabel(getdate() + "  等待客户端连接...");
            jPanel.add(label1);
            jf.add(jPanel);
            jf.setVisible(true);
            while (true) {
                socket = server.accept();
                label1 = new JLabel(getdate() + "  客户端 （" + socket.getInetAddress().getHostAddress() + "） 连接成功...");
                jPanel.add(label1);
                jf.add(jPanel);
                jPanel.setVisible(true);
                jf.setVisible(true);
                new sendMessThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getdate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String result = format.format(date);
        return result;
    }

    class sendMessThread extends Thread {
        private Socket socket;
        private DataInputStream dataInputStream;
        private FileOutputStream fos;

        public sendMessThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream in = socket.getInputStream();
                dataInputStream = new DataInputStream(in);
                int len = 0;
                byte[] buf = new byte[dataInputStream.readInt()];
                System.out.println(dataInputStream.readUTF());
                try {
                    while ((len = in.read(buf)) != -1) {
                        File file = new File("./src/com/cdn/server/file/" + socket.getInetAddress().toString() + "@" + UUID.randomUUID().toString() + ".txt");
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(buf, 0, len);
                        fileOutputStream.flush();
                    }
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 函数入口
    public static void main(String[] args) {
        Server_Test server = new Server_Test(88);
        server.start();
    }
}


