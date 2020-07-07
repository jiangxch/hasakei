package com.github.jiangxch.hassakei.common.util;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author: jiangxch
 * @date: 2020/7/7 11:10
 */
@Slf4j
public class FileUtil {

    private static int READ_FILE_BUFFER_SIZE = 512;
    private static int FILE_EOF = -1;

    public static File getFile(String resource) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(resource);
        assert url != null: "please check your resource path="+resource;
        File file = new File(url.getFile());
        assert file.exists() : "please check your resource path="+resource;
        return file;
    }

    public static String readFile(File file, Charset charset) {
        try(FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[READ_FILE_BUFFER_SIZE];
            int len = 0;
            while ((len = fis.read(buffer)) != FILE_EOF) {
                baos.write(buffer,0,len);
            }
            return new String(baos.toByteArray(),charset);
        } catch (FileNotFoundException e) {
            log.error("file can't found,ex={}",e.getMessage());
        } catch (IOException e) {
            log.error("read file error,ex={}",e.getMessage());
        }
        return StringUtil.EMPTY;
    }

    public static String readFile(File file) {
        return readFile(file,Charset.defaultCharset());
    }

    public static String readFile(String resource) {
        return readFile(getFile(resource),Charset.defaultCharset());
    }
}
