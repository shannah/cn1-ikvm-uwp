/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.tools.ikvm;

import com.codename1.tools.ikvm.Parser.VerifyException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
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
        String classPath = System.getProperty("preprocessor.class.path", null);
        
        System.out.println("Running IKVMClassPreprocessor");
        System.out.println("preprocessor.class.path="+classPath);
        if (args.length == 0) {
            try {
                Files.copy(new File("/Users/shannah/cn1_files/CodenameOne-git/CodenameOne/dist/CodenameOne.jar").toPath(), new File("dist/TSTCodenameOne.jar").toPath(), StandardCopyOption.REPLACE_EXISTING);
                //args = new String[] { "build/classes/com/codename1/tools/ikvm/tests/TestClass.class", "dist/TSTCodenameOne.jar"};
                if (classPath == null) {
                    classPath = "/Users/shannah/cn1_files/CodenameOne-git/CodenameOne/dist/CodenameOne.jar";
                }
            } catch (Exception ex){
                System.err.println("Failed to copy test jars.  THis may not be steve's computer");
            }
            Parser.verify = true;
            args = new String[] { "build/classes/com/codename1/tools/ikvm/tests/TestClass.class", "dist/TSTCodenameOne.jar"};
        }
        
        ClassLoader classLoader = IKVMClassPreprocessor.class.getClassLoader();
        if (classPath != null) {
            List<URL> urls = new ArrayList<URL>();
            StringTokenizer tok = new StringTokenizer(classPath, File.pathSeparator);
            while (tok.hasMoreTokens()) {
                urls.add(new File(tok.nextToken()).toURL());
            }
            classLoader = new URLClassLoader(urls.toArray(new URL[urls.size()]), classLoader);
            
        }
        
        for (String arg : args) {
            parse(arg, classLoader);
        }
    }
    
    
    private static void parse(String arg, ClassLoader classLoader) throws Exception {
        File f = new File(arg);
        if (f.isDirectory()) {
            for (File child : f.listFiles()) {
                parse(child.getAbsolutePath(), classLoader);
            }
        } else if (f.exists() && f.getName().endsWith(".class")) {
            try {
                Parser.parse(f, classLoader);
            } catch (VerifyException ex) {
                System.err.println("Failed to verify class "+f);
                throw ex;
            }
        } else if (f.exists() && f.getName().endsWith(".jar")) {
            File tmpOut;
            try (ZipFile zip = new ZipFile(f)) {
                tmpOut = File.createTempFile(f.getName(), ".jar");
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(tmpOut));
                try {
                    Enumeration<? extends ZipEntry> entries = zip.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry entry = entries.nextElement();
                        ZipEntry newEntry = new ZipEntry(entry.getName());
                        zos.putNextEntry(newEntry);
                        InputStream zis = zip.getInputStream(entry);
                        try {
                            if (entry.getName().endsWith(".class")) {
                                //System.out.println("Parsing entry "+entry);
                                try {
                                    Parser.parse(zis, zos, classLoader);
                                } catch (VerifyException vex) {
                                    System.err.println("Failed to verify class "+entry.getName());
                                    throw vex;
                                }
                            } else {
                                //System.out.println("Copying entry "+entry);
                                Parser.copy(zis, zos, 8192);
                            }
                        } finally {
                            zis.close();
                        }
                        zos.closeEntry();
                    }

                } finally {
                    try {
                        zos.close();
                    } catch (Exception ex){}
                    
                }
                
            }
            try {
                Files.move(tmpOut.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (FileAlreadyExistsException ex) {
                if (f.isDirectory()) {
                    delTree(f);
                    tmpOut.renameTo(f);
                }
            }
        } else {
            //System.out.println("Skipping file "+f);
        }
    }
    public static void delTree(File f) {
        for (String current : f.list()) {
            File ff = new File(f, current);
            if (ff.isDirectory()) {
                delTree(f);
            }
            ff.setWritable(true);
            ff.delete();
        }
    }
}
