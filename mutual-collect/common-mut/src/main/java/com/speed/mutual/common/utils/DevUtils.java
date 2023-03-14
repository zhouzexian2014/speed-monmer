package com.speed.mutual.common.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 自定义工具类
 * @author joey
 */
public class DevUtils {
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /** 驼峰转下划线*/
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }


    /**
     * 下载文件
     * @param filePath 文件路径
     * @param fileName 下载文件名
     * @throws IOException
     */
    public static void downloadFile(String filePath, String fileName) throws IOException {
        File f = new File(filePath);
        if(f.exists()){
            HttpServletResponse response = getResponse();
            //下载
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes(),"iso-8859-1"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            OutputStream outputStream = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            clear(bis);
            clear(fis);
        }
    }

    /**
     * 复制文件
     * @param filePath1
     * @param filePath2
     * @throws IOException
     */
    public static void copyFile(String filePath1, String filePath2) throws IOException {
        File f = new File(filePath1);
        if(f.exists()){
            File o = new File(filePath2);
            if(!o.exists()){
                o.getParentFile().mkdirs();
                o.createNewFile();
            }
            FileInputStream in = new FileInputStream(f);
            FileOutputStream out = new FileOutputStream(o);
            IoUtil.copy(in,out);
            clear(in,out);
        }
    }

    /**
     * 关闭流
     * @param in
     * @param out
     */
    private static void clear(InputStream in, OutputStream out) {
        clear(in);
        clear(out);
    }
    public static void clear(OutputStream out) {
        if(out!=null){
            try {
                out.close();
            } catch (IOException e) {
            }
        }
    }
    public static void clear(InputStream in) {
        if(in!=null){
            try {
                in.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 压缩文件
     *
     * @param sourceFilePath 源文件路径
     * @param zipFilePath    压缩后文件存储路径
     * @param zipFilename    压缩文件名
     */
    public static void compressToZip(String sourceFilePath, String zipFilePath, String zipFilename) {
        File sourceFile = new File(sourceFilePath);
        File zipPath = new File(zipFilePath);
        if (!zipPath.exists()) {
            zipPath.mkdirs();
        }
        File zipFile = new File(zipPath + File.separator + zipFilename);
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            writeZip(sourceFile, "", zos);
            deleteDir(sourceFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    /**
     * 遍历所有文件，压缩
     *
     * @param file       源文件目录
     * @param parentPath 压缩文件目录
     * @param zos        文件流
     */
    public static void writeZip(File file, String parentPath, ZipOutputStream zos) {
        if (file.isDirectory()) {
            //目录
            parentPath += file.getName() + File.separator;
            File[] files = file.listFiles();
            for (File f : files) {
                writeZip(f, parentPath, zos);
            }
        } else {
            //文件
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                //指定zip文件夹
                ZipEntry zipEntry = new ZipEntry(parentPath + file.getName());
                zos.putNextEntry(zipEntry);
                int len;
                byte[] buffer = new byte[1024 * 10];
                while ((len = bis.read(buffer, 0, buffer.length)) != -1) {
                    zos.write(buffer, 0, len);
                    zos.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage(), e.getCause());
            }
        }
    }

    /**
     * 删除文件夹
     *
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        //删除空文件夹
        return dir.delete();
    }

    /**
     * 获取当前req
     * @return
     */
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    /**
     * 获取当前resp
     * @return
     */
    public static HttpServletResponse getResponse(){
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
    }
    /**
     * 获取当前req访问地址
     * @return
     */
    public static String getRequestURI(){
        return getRequest().getRequestURI();
    }

    /**
     * 截取过长字符串
     * @param str
     * @param length
     * @return
     */
    public static String limitStr(String str, int length){
        if(StrUtil.isEmpty(str)){
            return str;
        }
        if(str.trim().length()>length){
            return str.trim().substring(0,length);
        }
        return str.trim();
    }

    public static String obj2Str(Object str) {
        if(str==null){
            return null;
        }
        return String.valueOf(str);
    }


    public static String toJSONString(Object args) {
        if(args!=null){
            return JSONUtil.toJsonPrettyStr(args);
        }
        return "";
    }
}
