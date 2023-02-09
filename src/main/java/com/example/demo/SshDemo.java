package com.example.demo;

import com.example.demo.local.Ignore;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ChannelSession;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.future.ConnectFuture;
import org.apache.sshd.client.session.ClientSession;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SshDemo {
    public static void main(String[] args) {

        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        // 使用sshd连接远程服务器
        // 1. 创建SshClient对象
        try (SshClient sshClient = SshClient.setUpDefaultClient();) {
            // 2. 启动SshClient
            sshClient.start();

            // 3. 连接远程服务器
            ConnectFuture future = sshClient.connect("root", Ignore.HOST, 22);
            future.await(5000);
            ClientSession session = future.getSession();
            session.addPasswordIdentity(Ignore.PASSWORD);


            if (!session.auth().verify(5000).isSuccess()) {
                throw new IOException("验证失败");
            }

            // 4. 提示用户输入命令
            System.out.println("连接成功，请输入命令：");

            // 5. 获取用户输入的命令
            Scanner scanner = new Scanner(System.in);

            // 6. 执行命令
            try (ClientChannel channel = session.createShellChannel()) {

                // 开启通道
                channel.open().verify(10000);

                threadPool.submit(() -> {
                    while (scanner.hasNext()) {
                        String command = scanner.nextLine();
                        if (command.equals("exit")) {
                            threadPool.shutdown();
                            channel.close(false);
                            break;
                        }
                        try {
                            OutputStream pipedIn = channel.getInvertedIn();
                            pipedIn.write((command + "\n").getBytes());
                            pipedIn.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                threadPool.submit(() -> {
                    try {
                        channel.getInvertedOut().transferTo(System.out);
                        channel.getInvertedErr().transferTo(System.out);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                // 等待关闭信号，保证通道不会关闭
                channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED), 0L);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // 7. 关闭连接
            sshClient.stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
