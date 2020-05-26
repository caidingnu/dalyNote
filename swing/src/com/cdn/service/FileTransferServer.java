package com.cdn.service;

import com.cdn.client.User;
import com.cdn.service.common.Constant;

import javax.swing.*;
import java.io.*;
import java.math.RoundingMode;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class FileTransferServer extends ServerSocket {
    private static DecimalFormat df = null;
    JFrame jf = null;
    JLabel label1 = null;
    JPanel jPanel = null;

    static {
        // 设置数字格式，保留一位有效小数
        df = new DecimalFormat("#0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMinimumFractionDigits(1);
        df.setMaximumFractionDigits(1);
    }

    public FileTransferServer() throws Exception {
        super(Constant.SERVER_PORT);
        jf = new JFrame();
        jf.setTitle("服务端");
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(100, 200, 300, 300);
        jPanel = new JPanel();
        label1 = new JLabel("  等待客户端连接...");
        jPanel.add(label1);
        jf.add(jPanel);
        jf.setVisible(true);
    }

    /**
     * 使用线程处理每个客户端传输的文件
     *
     * @throws Exception
     */
    public void load() throws Exception {
        while (true) {
            // server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
            Socket socket = this.accept();
            String hostAddress = socket.getInetAddress().getHostAddress();
            if (!label1.getText().contains(hostAddress)) {
                label1 = new JLabel("  客户端 （" + hostAddress + "） 连接成功...");
                jPanel.add(label1);
                jf.add(jPanel);
                jPanel.setVisible(true);
                jf.setVisible(true);
            }
            /**
             * 我们的服务端处理客户端的连接请求是同步进行的， 每次接收到来自客户端的连接请求后，
             * 都要先跟当前的客户端通信完之后才能再处理下一个连接请求。 这在并发比较多的情况下会严重影响程序的性能，
             * 为此，我们可以把它改为如下这种异步处理与客户端通信的方式
             */
            // 每接收到一个Socket就建立一个新的线程来处理它
            new Thread(new Task(socket)).start();
        }
    }

    /**
     * 处理客户端传输过来的文件线程类
     */
    class Task implements Runnable {
        private Socket socket;
        private ObjectInputStream objectInputStream;
        private ObjectOutputStream objectOutputStream;
        private FileOutputStream fos;

        public Task(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            int type = 0;
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                // 文件名和长度
                String fileName = objectInputStream.readUTF();
                type = (int) objectInputStream.readInt();
                System.out.println(fileName);
                System.out.println(type);
                switch (type) {
                    case 1:
                        long fileLength = objectInputStream.readLong();
                        File directory = new File(Constant.SERVER_SAVE_PATH);
                        if (!directory.exists()) {
                            directory.mkdir();
                        }
                        File file = new File(directory.getAbsolutePath() + File.separatorChar + socket.getInetAddress() + "@" + fileName);
                        fos = new FileOutputStream(file);
                        // 开始接收文件
                        byte[] bytes = new byte[1024];
                        int length;
                        while ((length = objectInputStream.read(bytes, 0, bytes.length)) != -1) {
                            fos.write(bytes, 0, length);
                            fos.flush();
                        }
                        System.out.println("======== 文件接收成功 [File Name：" + fileName + "] [Size：" + getFormatFileSize(fileLength) + "] ========");
                        break;
                    case 2:
                        System.out.println("222222222222222");
                        File file1 = new File(Constant.SERVER_SAVE_PATH);
                        File[] files = file1.listFiles();
                        assert files != null;
                        List<File> list = Arrays.asList(files);
                        OutputStream outputStream = socket.getOutputStream();
                        objectOutputStream = new ObjectOutputStream(outputStream);
                        objectOutputStream.writeObject(new User(1, "root", "123456", 2));
                        objectOutputStream.flush();
                        objectOutputStream.close();
                        socket.close();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 格式化文件大小
     *
     * @param length
     * @return
     */
    private String getFormatFileSize(long length) {
        double size = ((double) length) / (1 << 30);
        if (size >= 1) {
            return df.format(size) + "GB";
        }
        size = ((double) length) / (1 << 20);
        if (size >= 1) {
            return df.format(size) + "MB";
        }
        size = ((double) length) / (1 << 10);
        if (size >= 1) {
            return df.format(size) + "KB";
        }
        return length + "B";
    }

    /**
     * 入口
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            FileTransferServer server = new FileTransferServer(); // 启动服务端
            server.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}