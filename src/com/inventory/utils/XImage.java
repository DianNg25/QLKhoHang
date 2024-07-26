package com.inventory.utils;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;
public class XImage {
    public void saveExcel(String filePath) {
        File tempFile = new File(filePath + "_temp");
        try {
            Files.copy(new File(filePath).toPath(), tempFile.toPath());
            FileInputStream fis = new FileInputStream(tempFile);
            // Perform operations
            fis.close();
            tempFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}