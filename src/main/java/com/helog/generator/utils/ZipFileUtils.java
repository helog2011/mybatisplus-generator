package com.helog.generator.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
public class ZipFileUtils {
    private static final Logger logger = LoggerFactory.getLogger(ZipFileUtils.class);

    private static ZipFileUtils instance = new ZipFileUtils();
    private static final int BUFFEREDSIZE = 1024;

    public static ZipFileUtils getInstance() {
        return instance;
    }

    public ZipFileUtils() {
    }

    private void zip(String zipFileName, File inputFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        this.zip(out, inputFile, "");
        System.out.println("zip done");
        out.close();
    }

    private void zip(ZipOutputStream out, File f, String base) throws Exception {
        int i;
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";

            for(i = 0; i < fl.length; ++i) {
                this.zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            System.out.println(base);

            while((i = in.read()) != -1) {
                out.write(i);
            }

            in.close();
        }

    }

    public synchronized void zip(String inputFilename, String zipFilename) throws IOException {
        this.zip(new File(inputFilename), zipFilename);
    }

    public synchronized void zip(File inputFile, String zipFilename) throws IOException {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFilename));

        try {
            this.zip(inputFile, out, "");
        } catch (IOException var8) {
            throw var8;
        } finally {
            out.close();
        }

    }

    public synchronized void zip(File inputFile, ZipOutputStream out, String base) throws IOException {
        int c;
        if (inputFile.isDirectory()) {
            File[] inputFiles = inputFile.listFiles();
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";

            for(c = 0; c < inputFiles.length; ++c) {
                this.zip(inputFiles[c], out, base + inputFiles[c].getName());
            }
        } else {
            if (base.length() > 0) {
                out.putNextEntry(new ZipEntry(base));
            } else {
                out.putNextEntry(new ZipEntry(inputFile.getName()));
            }

            FileInputStream in = new FileInputStream(inputFile);

            try {
                byte[] by = new byte[1024];

                while((c = in.read(by)) != -1) {
                    out.write(by, 0, c);
                }
            } catch (IOException var10) {
                throw var10;
            } finally {
                in.close();
            }
        }

    }

    public synchronized void unzip(String zipFilename, String outputDirectory) throws IOException {
        File outFile = new File(outputDirectory);
        if (!outFile.exists()) {
            outFile.mkdirs();
        }

        ZipFile zipFile = new ZipFile(zipFilename);
        Enumeration en = zipFile.getEntries();
        ZipEntry zipEntry = null;

        while(true) {
            while(en.hasMoreElements()) {
                zipEntry = (ZipEntry)en.nextElement();
                if (zipEntry.isDirectory()) {
                    String dirName = zipEntry.getName();
                    dirName = dirName.substring(0, dirName.length() - 1);
                    File f = new File(outFile.getPath() + File.separator + dirName);
                    f.mkdirs();
                } else {
                    File f = new File(outFile.getPath() + File.separator + zipEntry.getName());
                    f.createNewFile();
                    InputStream in = zipFile.getInputStream(zipEntry);
                    FileOutputStream out = new FileOutputStream(f);

                    try {
                        byte[] by = new byte[1024];

                        int c;
                        while((c = in.read(by)) != -1) {
                            out.write(by, 0, c);
                        }
                    } catch (IOException var15) {
                        throw var15;
                    } finally {
                        out.close();
                        in.close();
                    }
                }
            }

            return;
        }
    }
}

