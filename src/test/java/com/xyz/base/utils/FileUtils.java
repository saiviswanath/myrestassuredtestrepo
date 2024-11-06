package com.xyz.base.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileUtils {
    public static FileReader getFileReader(String filePath){
        String absoluteFilePath = System.getProperty("user.dir") + filePath;
        try{
            return new FileReader(absoluteFilePath);
        }
        catch (NullPointerException | FileNotFoundException ex){
            throw new RuntimeException("File not found - " + absoluteFilePath + " Trace : " + ex);
        }
    }
}
