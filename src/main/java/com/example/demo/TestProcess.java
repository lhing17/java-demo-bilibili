package com.example.demo;

import fj.F;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestProcess {

    public static void main(String[] args) throws IOException, InterruptedException {

        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);


        Scanner scanner = new Scanner(System.in);
        File workDir = new File(".");
        Process p = Runtime.getRuntime().exec("/bin/zsh", null, workDir);

        threadPool.submit(() -> {
            while (scanner.hasNext()) {
                String command = scanner.nextLine();
                if (command.equals("exit")) {
                    p.destroy();

                    break;
                }
                try {
                    PrintWriter pw = new PrintWriter(p.getOutputStream());
                    pw.println(command);
                    pw.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.submit(() -> {
            try {
                p.getInputStream().transferTo(System.out);
                p.getErrorStream().transferTo(System.out);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        p.waitFor();
        threadPool.shutdown();

//        p.waitFor();



//        while (scanner.hasNext()) {
//            String s = scanner.nextLine();
//            String[] s1 = s.split(" ");
//            if ("cd".equals(s1[0])) {
//                workDir = new File(s1[1]);
//            } else {
//
//
//                PrintWriter pw = new PrintWriter(p.getOutputStream());
//                pw.println(s);
//                pw.close();
//
//                // 阻塞当前Java线程直至子进程执行完成
//                p.waitFor();
//                p.getInputStream().transferTo(System.out);
//                p.getErrorStream().transferTo(System.out);
//
//                // 销毁子进程
//                p.destroy();
//            }
//
//        }
    }
}
