/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.tools.ikvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author shannah
 */
public class IKVMClassPreprocessor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        if (args.length == 0) {
            args = new String[] { "build/classes/com/codename1/tools/ikvm/tests/TestClass.class"};
        }
        for (String arg : args) {
            parse(arg);
        }
    }
    
    
    private static void parse(String arg) throws Exception {
        File f = new File(arg);
        if (f.isDirectory()) {
            for (File child : f.listFiles()) {
                parse(child.getAbsolutePath());
            }
        } else if (f.exists() && f.getName().endsWith(".class")) {
            Parser.parse(f);
        } else if (f.exists() && f.getName().endsWith(".jar")) {
            ZipFile zip = new ZipFile(f);
            File tmpOut = File.createTempFile(f.getName(), ".jar");
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(tmpOut));
            try {
                Enumeration<? extends ZipEntry> entries = zip.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    zos.putNextEntry(entry);
                    InputStream zis = zip.getInputStream(entry);
                    if (entry.getName().endsWith(".class")) {
                        Parser.parse(zis, zos);
                    } else {
                        Parser.copy(zis, zos, 8192);
                    }
                    zos.closeEntry();
                }
                
            } finally {
                try {
                    zos.close();
                } catch (Exception ex){}
                Files.move(tmpOut.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } else {
            System.out.println("Skipping file "+f);
        }
    }
}
