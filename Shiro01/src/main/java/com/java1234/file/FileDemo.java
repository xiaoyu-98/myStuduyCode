package com.java1234.file;

import java.io.File;
import java.io.IOException;

/**
 * 类描述:
 *
 * @author xiaoyu
 * on 2020/6/10
 */
public class FileDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\1my study\\1我的博客文章");
//        boolean newFile = file.createNewFile();
//        System.out.println(newFile);
    /*    String[] list = file.list();
        for (String s : list) {
            System.out.println(s);
        }*/
        getFiles(file);
    }

    static  void getFiles(File dir){
        System.out.println(dir);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getFiles(file);
            }else {
                System.out.println(file);
            }
        }
    }
}
