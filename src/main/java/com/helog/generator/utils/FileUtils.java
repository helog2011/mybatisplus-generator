package com.helog.generator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
/**
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private FileUtils() {
    }

    public static long copyFile(File srcFile, File destDir, String newFileName) {
        long copySizes = 0L;
        if (!srcFile.exists()) {
            System.out.println("源文件不存在");
            copySizes = -1L;
        } else if (!destDir.exists()) {
            System.out.println("目标目录不存在");
            copySizes = -1L;
        } else if (newFileName == null) {
            System.out.println("文件名为null");
            copySizes = -1L;
        } else {
            try {
                BufferedInputStream bin = new BufferedInputStream(new FileInputStream(srcFile));
                BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(new File(destDir, newFileName)));
                int i;
                int b;
                for(i = 0; (b = bin.read()) != -1; ++i) {
                    bout.write(b);
                }

                bout.flush();
                bin.close();
                bout.close();
                copySizes = (long)i;
            } catch (FileNotFoundException var9) {
                var9.printStackTrace();
            } catch (IOException var10) {
                var10.printStackTrace();
            }
        }

        return copySizes;
    }

    public static long copyFile2(File srcFile, File destDir, String newFileName) {
        long copySizes = 0L;
        if (!srcFile.exists()) {
            System.out.println("源文件不存在");
            copySizes = -1L;
        } else if (!destDir.exists()) {
            System.out.println("目标目录不存在");
            copySizes = -1L;
        } else if (newFileName == null) {
            System.out.println("文件名为null");
            copySizes = -1L;
        } else {
            try {
                FileChannel fcin = (new FileInputStream(srcFile)).getChannel();
                FileChannel fcout = (new FileOutputStream(new File(destDir, newFileName))).getChannel();
                long size = fcin.size();
                fcin.transferTo(0L, fcin.size(), fcout);
                fcin.close();
                fcout.close();
                copySizes = size;
            } catch (FileNotFoundException var9) {
                var9.printStackTrace();
            } catch (IOException var10) {
                var10.printStackTrace();
            }
        }

        return copySizes;
    }

    public static boolean DeleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if (!file.exists()) {
            return flag;
        } else {
            return file.isFile() ? deleteFile(sPath) : deleteDirectory(sPath);
        }
    }

    public static boolean DeleteFileByKey(String sPath, String sKey) {
        boolean flag = false;
        File file = new File(sPath);
        if (!file.exists()) {
            return flag;
        } else {
            File temp = null;
            File[] filelist = file.listFiles();

            for(int i = 0; i < filelist.length; ++i) {
                temp = filelist[i];
                if (temp.getName().contains(sKey)) {
                    temp.delete();
                }
            }

            return true;
        }
    }

    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }

        return flag;
    }

    public static boolean deleteDirectory(String sPath) {
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }

        File dirFile = new File(sPath);
        if (dirFile.exists() && dirFile.isDirectory()) {
            boolean flag = true;
            File[] files = dirFile.listFiles();

            for(int i = 0; i < files.length; ++i) {
                if (files[i].isFile()) {
                    flag = deleteFile(files[i].getAbsolutePath());
                    if (!flag) {
                        break;
                    }
                } else {
                    flag = deleteDirectory(files[i].getAbsolutePath());
                    if (!flag) {
                        break;
                    }
                }
            }

            if (!flag) {
                return false;
            } else {
                return dirFile.delete();
            }
        } else {
            return false;
        }
    }
}
