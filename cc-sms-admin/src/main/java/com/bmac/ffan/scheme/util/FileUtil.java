//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bmac.ffan.scheme.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bmac.ffan.modular.basebusiness.util.CRC16;

public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public FileUtil() {
    }

    public static List<String> FindFile(String path, int fileType, String matchFileStr) {
        ArrayList fileList = new ArrayList();
        File file = new File(path);
        file.exists();
        if(file.isDirectory()) {
            ;
        }

        if(file.isDirectory() && fileType == 2) {
            File[] list = file.listFiles(new FileNameFilterImpl(file, matchFileStr));
            if(list.length != 0) {
                System.out.println("list length:" + list.length);
                File[] var9 = list;
                int var8 = list.length;

                for(int var7 = 0; var7 < var8; ++var7) {
                    File f = var9[var7];
                    System.out.println(f.getName());
                    fileList.add(f.getName());
                }
            }
        }

        return fileList;
    }

    public static List<File> FindFile(String path) {
        new ArrayList();
        File file = new File(path);
        ArrayList fileListTmp = new ArrayList();
        if(!file.exists()) {
            logger.error(path + ",this path not exist!");
        } else {
            List fileList = Arrays.asList(file.listFiles());
            logger.info("file path：" + file.getAbsolutePath());
            Iterator var5 = fileList.iterator();

            while(var5.hasNext()) {
                File filetmp = (File)var5.next();
                if(!filetmp.isDirectory()) {
                    fileListTmp.add(filetmp);
                }
            }
        }

        return fileListTmp;
    }

    public static List<File> FindFile(String path, String matchFileStr) {
        ArrayList fileList = new ArrayList();
        File file = new File(path);
        if(!file.exists()) {
            logger.error("this path not exist!");
        }

        File[] list = file.listFiles(new FileNameFilterImpl(file, matchFileStr));
        if(list.length != 0) {
            File[] var8 = list;
            int var7 = list.length;

            for(int var6 = 0; var6 < var7; ++var6) {
                File f = var8[var6];
                if(!f.isDirectory()) {
                    fileList.add(f);
                }
            }
        }

        return fileList;
    }

    public static int FindFileNumber(String path) {
        int fileNumber = 0;
        File file = new File(path);
        if(!file.exists()) {
            logger.error("this path not exist!");
        }

        if(!file.isDirectory()) {
            logger.error("this path is not directory!");
        } else {
            File[] fileList = file.listFiles();
            fileNumber = fileList.length;
        }

        return fileNumber;
    }

    public static boolean moveFile(String oldpath, String newpath, String fileName) {
        File newfilePath = new File(newpath);
        if(!newfilePath.exists()) {
            newfilePath.mkdirs();
        }

        File oldfile = new File(oldpath + fileName);
        File newfile = new File(newpath + fileName);
        boolean success = oldfile.renameTo(newfile);
        logger.info("move from " + oldpath + fileName + " to " + newpath + fileName + " is " + success);
        return success;
    }

    public static boolean moveFile(String oldpath, String newpath, String oldFileName, String newFileName) {
        File newfilePath = new File(newpath);
        if(!newfilePath.exists()) {
            newfilePath.mkdirs();
        }

        File oldfile = new File(oldpath + oldFileName);
        File newfile = new File(newpath + newFileName);
        boolean success = oldfile.renameTo(newfile);
        logger.info("move from " + oldpath + oldFileName + " to " + newpath + newFileName + " is " + success);
        return success;
    }

    public static void writeFile(String pathFileName, String context, String encoding) throws IOException {
        File file = new File(pathFileName);
        RandomAccessFile randomAccessFile = null;

        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0L);

            try {
                randomAccessFile.write(context.toString().getBytes(encoding));
            } catch (UnsupportedEncodingException var14) {
                var14.printStackTrace();
            }
        } catch (IOException var15) {
            var15.printStackTrace();
            throw var15;
        } finally {
            if(randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException var13) {
                    var13.printStackTrace();
                }
            }

        }

    }

    public static FileData getFileData(File file, int startIndex, int readlength, int fileSize, RandomAccessFile randomAccessFile) throws Exception {
        boolean returnFileSize = false;
        FileData fd = new FileData();
        boolean lengthtmp = false;
        int lengthtmp1;
        if(startIndex + readlength <= fileSize) {
            lengthtmp1 = readlength;
        } else {
            lengthtmp1 = fileSize - startIndex;
        }

        byte[] b = new byte[lengthtmp1];
        randomAccessFile.seek((long)startIndex);

        try {
            int returnFileSize1 = randomAccessFile.read(b);
            fd.setReturnFileSize(returnFileSize1);
            fd.setFileDataStr(new String(b));
            fd.setFileDateByte(b);
        } catch (Exception var10) {
            logger.error("file is not exist!");
        }

        return fd;
    }

    public static boolean writeFileData(File file, int startIndex, int fileSize, RandomAccessFile randomAccessFile, byte[] writeData) throws Exception {
        randomAccessFile.seek((long)startIndex);
        boolean writestat = false;

        try {
            randomAccessFile.write(writeData);
            writestat = true;
            return writestat;
        } catch (Exception var7) {
            logger.error("filedata write fail");
            throw var7;
        }
    }

    public static boolean deleteFile(String path, String fileName) {
        File file = new File(path + fileName);
        return file.delete();
    }

    public static boolean copyFile(String oldPath, String newPath, String oldFileName, String newFileName) {
        File newfilePath = new File(newPath);
        if(!newfilePath.exists()) {
            newfilePath.mkdirs();
        }

        boolean bytesread = true;
        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        try {
            File e = new File(oldPath + oldFileName);
            if(!e.exists()) {
                logger.error("源文件不存在!");
            } else {
                in = new BufferedInputStream(new FileInputStream(oldPath + oldFileName));
                out = new BufferedOutputStream(new FileOutputStream(newPath + newFileName));
                byte[] b = new byte[2048];

                int bytesread1;
                while((bytesread1 = in.read(b)) != -1) {
                    out.write(b, 0, bytesread1);
                }

                out.flush();
                in.close();
                out.close();
            }

            return true;
        } catch (Exception var18) {
            logger.error("copy file [" + oldFileName + "] error!", var18);
        } finally {
            try {
                if(in != null) {
                    in.close();
                }

                if(out != null) {
                    out.close();
                }
            } catch (IOException var17) {
                logger.error("close stream error!", var17);
            }

        }

        return false;
    }

    public static byte[] readBinaryFile(String fileName) {
        File readFile = new File(fileName);
        if(!readFile.exists()) {
            return null;
        } else {
            boolean bytesread = true;
            BufferedInputStream in = null;
            byte[] data = null;

            try {
                in = new BufferedInputStream(new FileInputStream(readFile));
                byte[] e = new byte[2048];

                int bytesread1;
                while((bytesread1 = in.read(e)) != -1) {
                    if(data == null) {
                        data = NumberStringUtil.getBytes(e, 0, bytesread1);
                    } else {
                        data = NumberStringUtil.byteArrayAdd(data, NumberStringUtil.getBytes(e, 0, bytesread1));
                    }
                }

                in.close();
            } catch (FileNotFoundException var16) {
                var16.printStackTrace();
            } catch (IOException var17) {
                var17.printStackTrace();
            } finally {
                try {
                    if(in != null) {
                        in.close();
                    }
                } catch (IOException var15) {
                    logger.error("close stream error!", var15);
                }

            }

            return data;
        }
    }

    public static boolean write(String filePath, String content) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(content);
            return true;
        } catch (Exception var12) {
            logger.error("write file [" + filePath + "] error!", var12);
        } finally {
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException var11) {
                    bw = null;
                }
            }

        }

        return false;
    }

    public static void main(String[] arg) {
        String fileName = "D:\\busposfirmware_1005.apk";
        byte[] data = readBinaryFile(fileName);
        System.out.println("size:" + data.length);//1232
//        System.out.println("------------------------------------");
//        String content = NumberStringUtil.bytesToHexString(data);
        String crc = CRC16.CRC_16(data);
//        System.out.println(content);
        System.out.println(crc);
    }
}
