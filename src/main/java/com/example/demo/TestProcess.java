package com.example.demo;

import fj.F;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TestProcess {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        File workDir = new File(".");

        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            String[] s1 = s.split(" ");
            if ("cd".equals(s1[0])) {
                workDir = new File(s1[1]);
            } else {
                Process p = Runtime.getRuntime().exec("/bin/zsh", null, workDir);

                PrintWriter pw = new PrintWriter(p.getOutputStream());
                pw.println(s);
                pw.close();

                // 阻塞当前Java线程直至子进程执行完成
                p.waitFor();
                p.getInputStream().transferTo(System.out);
                p.getErrorStream().transferTo(System.out);

                // 销毁子进程
                p.destroy();
            }

        }
    }
}
