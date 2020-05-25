package com.cdn.service;

import com.cdn.service.common.Constant;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class FileTransferClient extends Socket {


    private Socket client;
    private FileInputStream fis;
    private DataOutputStream dos;

    /**
     * 构造函数<br/>
     * 与服务器建立连接
     *
     * @throws Exception
     */
    public FileTransferClient() throws Exception {
        super(Constant.SERVER_IP, Constant.SERVER_PORT);
        this.client = this;
        System.out.println("Cliect[port:" + client.getLocalPort() + "] 成功连接服务端");
    }

    /**
     * 向服务端传输文件
     *
     * @throws Exception
     */
    public void sendFile(String path) throws Exception {
        try {
            File file = new File(path);
            if (file.exists()) {
                fis = new FileInputStream(file);
                dos = new DataOutputStream(client.getOutputStream());
                // 文件名和长度
                dos.writeUTF(file.getName());
                dos.flush();
                dos.writeLong(file.length());
                dos.flush();
                // 开始传输文件
                System.out.println("======== 开始传输文件 ========");
                byte[] bytes = new byte[1024];
                int length = 0;
                long progress = 0;
                while ((length = fis.read(bytes, 0, bytes.length)) != -1) {
                    dos.write(bytes, 0, length);
                    dos.flush();
                    progress += length;
                    System.out.print("| " + (100 * progress / file.length()) + "% |");
                }
                System.out.println();
                System.out.println("======== 文件传输成功 ========");
                showMsg("文件传输成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null)
                fis.close();
            if (dos != null)
                dos.close();
            client.close();
        }
    }

    public void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 入口
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        JFrame jf = new JFrame();
        jf.setTitle("文件管理");
        jf.setBounds(800, 300, 300, 200);
        jf.setLocationRelativeTo(null);
        JMenuBar jmb = new JMenuBar();
        JMenu fileMenu = new JMenu("文件");
        JMenuItem openFileItem = new JMenuItem("新建");
        JMenuItem closeItem = new JMenuItem("保存");
        JMenuItem closeAllItem = new JMenuItem("打开");
        JMenuItem delItem = new JMenuItem("删除");
        fileMenu.add(openFileItem);
        fileMenu.addSeparator();//增加分割线！！！
        fileMenu.add(closeItem);
        fileMenu.addSeparator();//增加分割线！！！
        fileMenu.add(closeAllItem);
        fileMenu.addSeparator();//增加分割线！！！
        fileMenu.add(delItem);
        jmb.add(fileMenu);

        jf.setJMenuBar(jmb);//注意加入菜单栏的方法，不能用add!!
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);


        JPanel jp1 = new JPanel(); //创建面板
        FileTransferClient f = new FileTransferClient();
        f.action(openFileItem, jp1, jf);
        f.action(closeItem, jp1, jf);
        f.action(closeAllItem, jp1, jf);
        f.action(delItem, jp1, jf);

    }


    public void action(JMenuItem jMenuItem, JPanel jp1, JFrame jFrame) {
        jMenuItem.addActionListener(e -> {
            switch (e.getActionCommand()) {
                case "新建":
                    System.out.println("新建--");
                    //定义面板
                    //上部组件
                    JTextArea jta1 = new JTextArea();    //创建多行文本框
                    jta1.setMargin(new Insets(20, 30, 40, 50));
                    jta1.setName("cdn----");
                    jta1.setLineWrap(true);    //设置多行文本框自动换行
                    jp1.setLayout(new BorderLayout());    //设置面板布局
                    jp1.add(jta1);
                    jFrame.add(jp1);
                    jFrame.setVisible(true);
                    jp1.show();
                    break;
                case "保存":
                    System.out.println("保存--");
                    Component[] components = jp1.getComponents();
                    if (components.length > 0) {
                        for (Component component : components) {
                            if (component instanceof JTextArea) {
                                JTextArea component1 = (JTextArea) component;
                                String text = component1.getText();
                                if (text.length() > 0) {
                                    System.out.println(text);
                                    try {
                                        FileTransferClient client = new FileTransferClient(); // 启动客户端连接
                                        String path = "./" + UUID.randomUUID().toString() + ".txt";
                                        File file = new File(path);
                                        FileWriter fileWriter = null;
                                        try {
                                            fileWriter = new FileWriter(file);
                                            fileWriter.write(text);
                                            fileWriter.flush();
                                        } catch (IOException r) {
                                            r.printStackTrace();
                                        } finally {
                                            try {
                                                if (fileWriter != null) {
                                                    fileWriter.close();
                                                }
                                            } catch (IOException e7) {
                                                e7.printStackTrace();
                                            }
                                        }
                                        client.sendFile(path); // 传输文件
                                        file.delete();
                                    } catch (Exception e4) {
                                        e4.printStackTrace();
                                    }
                                } else {
                                    showMsg("保存的内容不能为空");
                                }
                                break;
                            }
                        }
                    }
                    jp1.removeAll();
                    jp1.hide();
                    break;
                case "打开":
                    System.out.println("打开--");
                    try {
                        OutputStream outputStream = client.getOutputStream();
//                                        outputStream.write(text.getBytes());
//                                        outputStream.flush();
                        DataOutputStream dataInputStream = new DataOutputStream(outputStream);
                        dataInputStream.writeUTF("读取");
                        dataInputStream.write("哈哈哈哈哈哈".getBytes());
                        dataInputStream.flush();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                case "删除":
                    System.out.println("删除--");
                    break;
                default:
                    break;
            }

        });
    }
}