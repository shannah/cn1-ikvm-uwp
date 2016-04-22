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
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 *
 * @author shannah
 */
public class Parser {
    //ClassNode classNode;
    
    /**
     * Parses an input stream with a class file, and writes the transformed class
     * to the output stream.  If no transformation occurs, it will simply copy 
     * the unchanged class to the output stream.
     * @param input The input stream containing a class.
     * @param output An output stream to write the transformed class to.
     * @throws Exception 
     */
    public static void parse(InputStream input, OutputStream output) throws Exception {
        byte[] bytes = parse(input);
        if (bytes != null) {
            output.write(bytes);
        } else {
            copy(input, output, 8192);
        }
    }
    
    /**
     * Parses an InputStream containing a class.
     * @param input The input stream with a class.
     * @return If a transformation occurred, the bytes for the changed class will be returned.  Otherwise null will be returned.
     * @throws Exception 
     */
    public static byte[] parse(InputStream input) throws Exception {
        ClassReader r = new ClassReader(input);
        Parser p = new Parser();
        //ClassWriter w = new ClassWriter(r, 0);
        ClassNode classNode = new ClassNode();
        //p.classNode = classNode;
        
        r.accept(classNode, 0);
        //r.accept(p, ClassReader.EXPAND_FRAMES)
        
        
        List<MethodNode> methodsToAdd = new ArrayList<MethodNode>();
        int methodNum = 0;
        for (Object o : classNode.methods) {
            methodNum++;
            MethodNode methodNode = (MethodNode)o;
            boolean synchronizedMethod = (methodNode.access & Opcodes.ACC_SYNCHRONIZED) == Opcodes.ACC_SYNCHRONIZED;
            if (synchronizedMethod) {
                // Check for a try statement
                final boolean[] tryCatchFound = new boolean[1];
                //System.out.println("Found sync method "+methodNode.name+". Checking for try blocks");
                methodNode.accept(new MethodVisitor(Opcodes.ASM5) {

                    @Override
                    public void visitTryCatchBlock(Label label, Label label1, Label label2, String string) {
                        tryCatchFound[0] = true;
                    }
                    
                    
                    
                });
                if (!tryCatchFound[0]) {
                    continue;
                }
                System.out.println("Transforming method "+methodNode.name+" of class "+classNode.name);
                MethodDescriptor md = new MethodDescriptor(methodNode.name, methodNode.desc);
                methodNode.access = methodNode.access & ~Opcodes.ACC_SYNCHRONIZED;
                String privateMethodName = (md.constructor?"___cn1init__":methodNode.name) + "___cn1sync"+(methodNum);
                MethodNode syncMethod = new MethodNode(
                        methodNode.access | Opcodes.ACC_SYNCHRONIZED | Opcodes.ACC_PRIVATE, 
                        methodNode.name, methodNode.desc,
                        methodNode.signature,
                        (String[]) methodNode.exceptions.toArray(new String[methodNode.exceptions.size()]));
                
                methodNode.name = privateMethodName;
                int argIndex=0;
                if (!md.staticMethod) {
                    syncMethod.instructions.add(new VarInsnNode(Opcodes.AALOAD, argIndex++));
                }
                
                
                for (ByteCodeMethodArg arg : md.arguments) {
                    switch (arg.type) {
                        case 'L':
                            syncMethod.instructions.add(new VarInsnNode(Opcodes.ALOAD, argIndex++));
                            break;
                        case 'S':
                        case 'I':
                        case 'B':
                        case 'Z':   
                        case 'C':
                            syncMethod.instructions.add(new VarInsnNode(Opcodes.ILOAD, argIndex++));
                            break;
                        case 'J':
                            syncMethod.instructions.add(new VarInsnNode(Opcodes.LLOAD, argIndex++));
                            break;
                        case 'F':
                            syncMethod.instructions.add(new VarInsnNode(Opcodes.FLOAD, argIndex++));
                            break;
                        case 'D':
                           syncMethod.instructions.add(new VarInsnNode(Opcodes.DLOAD, argIndex++));
                            break;
                        default:
                            throw new IllegalArgumentException("Unsupported argument type "+arg.type);
                    }
                }
                
                if (md.staticMethod) {
                    syncMethod.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC , classNode.name, privateMethodName, methodNode.desc));
                } else {
                    syncMethod.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, classNode.name, privateMethodName, methodNode.desc));
                }
                
                if (md.returnType != null) {
                    switch (md.returnType.type) {
                        case 'L':
                            syncMethod.instructions.add(new InsnNode(Opcodes.ARETURN));
                            break;
                        case 'S':
                        case 'I':
                        case 'B':
                        case 'Z':   
                        case 'C':
                            syncMethod.instructions.add(new InsnNode(Opcodes.IRETURN));
                            break;
                        case 'J':
                            syncMethod.instructions.add(new InsnNode(Opcodes.LRETURN));
                            break;
                        case 'F':
                            syncMethod.instructions.add(new InsnNode(Opcodes.FRETURN));
                            break;
                        case 'D':
                           syncMethod.instructions.add(new InsnNode(Opcodes.DRETURN));
                            break;
                        case 'V':
                            break;
                        default:
                            throw new IllegalArgumentException("Unsupported argument type "+md.returnType.type);
                    }
                } else {
                    syncMethod.instructions.add(new InsnNode(Opcodes.DRETURN));
                }
                
                methodsToAdd.add(syncMethod);
                
            }
        }
        if (!methodsToAdd.isEmpty()) {
            System.out.println("Transforming "+methodsToAdd.size()+" synchronized methods in "+classNode.name);
            classNode.methods.addAll(methodsToAdd);
            ClassWriter w = new ClassWriter(0);
            classNode.accept(w);
            return w.toByteArray();
        }
        return null;
    }
    
    /**
     * Parse a .class file. If changes are required, it will overwrite the same
     * file with the changed file contents.
     * @param sourceFile A .class file to transform.
     * @throws Exception 
     */
    public static void parse(File sourceFile) throws Exception {
        byte[] newBytes = parse(new FileInputStream(sourceFile));
        if (newBytes != null) {
            try (FileOutputStream fos = new FileOutputStream(sourceFile)) {
                fos.write(newBytes);
            }
        }
    }


   
    /*
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        boolean synchronizedMethod = (access & Opcodes.ACC_SYNCHRONIZED) == Opcodes.ACC_SYNCHRONIZED;
        if (synchronizedMethod) {
            System.out.println("Found Synchronized Method: name="+name+", desc="+desc+", signature="+signature+", execeptions="+Arrays.toString(exceptions));
        }
        //BytecodeMethod mtd = new BytecodeMethod(clsName, access, name, desc, signature, exceptions);
        //cls.addMethod(mtd);
        //JSRInlinerAdapter a = new JSRInlinerAdapter(new MethodVisitorWrapper(super.visitMethod(access, name, desc, signature, exceptions), mtd), access, name, desc, signature, exceptions);
        return super.visitMethod(access, name, desc, signature, exceptions);
    }*/
    
    
    private static class MethodDescriptor {
        List<ByteCodeMethodArg> arguments = new ArrayList<ByteCodeMethodArg>();
        ByteCodeMethodArg returnType;
        boolean constructor;
        boolean staticMethod;
        
        private MethodDescriptor(String methodName, String desc) {
            int pos = desc.lastIndexOf(')');
            if(methodName.equals("<init>")) {
                methodName = "__INIT__";
                constructor = true;
                returnType = new ByteCodeMethodArg('V', 0);
            } else {
                if(methodName.equals("<clinit>")) {
                    methodName = "__CLINIT__";
                    returnType = new ByteCodeMethodArg('V', 0);
                    staticMethod = true;
                } else {            
                    String retType = desc.substring(pos + 1);
                    if(retType.equals("V")) {
                        returnType = new ByteCodeMethodArg('V', 0);
                    } else {
                        int dim = 0;
                        while(retType.startsWith("[")) {
                            retType = retType.substring(1);
                            dim++;
                        }
                        char currentType = retType.charAt(0);
                        switch(currentType) {
                            case 'L':
                                
                            case 'I':
                                
                            case 'J':
                               
                            case 'B':
                                
                            case 'S':
                                
                            case 'F':
                                
                            case 'D':
                               
                            case 'Z':
                               
                            case 'C':
                                returnType = new ByteCodeMethodArg(currentType, dim);
                                break;
                        }
                    }
                }
            }

            int currentArrayDim = 0;
            desc = desc.substring(1, pos);
            for(int i = 0 ; i < desc.length() ; i++) {
                char currentType = desc.charAt(i);
                switch(currentType) {
                    case '[':
                        // array of...
                        currentArrayDim++;
                        continue;
                    case 'L':
                        
                    case 'I':
                        
                    case 'J':
                        
                    case 'B':
                        
                    case 'S':
                        
                    case 'F':
                        
                    case 'D':
                        
                    case 'Z':
                        
                    case 'C':
                        arguments.add(new ByteCodeMethodArg(currentType, currentArrayDim));
                        break;
                }
                currentArrayDim = 0;
            }
            
        }
    }
    
    private static class ByteCodeMethodArg {
        boolean isPrimitive;
        int dim;
        char type;
        
        private ByteCodeMethodArg(char type, int dim) {
            this.type = type;
            this.dim = dim;
        }
        
        private ByteCodeMethodArg(String type, int dim) {
            this.type = 'L';
            this.dim = dim;
        }
    }
    
    /**
     * Copy the input stream into the output stream, closes both streams when
     * finishing or in a case of an exception
     *
     * @param i source
     * @param o destination
     * @param bufferSize the size of the buffer, which should be a power of 2
     * large enoguh
     */
    public static void copy(InputStream i, OutputStream o, int bufferSize) throws IOException {
        try {
            byte[] buffer = new byte[bufferSize];
            int size = i.read(buffer);
            while (size > -1) {
                o.write(buffer, 0, size);
                size = i.read(buffer);
            }
        } finally {
            cleanup(o);
            cleanup(i);
        }
    }

    /**
     * Closes the object (connection, stream etc.) without throwing any
     * exception, even if the object is null
     *
     * @param o Connection, Stream or other closeable object
     */
    public static void cleanup(Object o) {
        try {
            if (o instanceof OutputStream) {
                ((OutputStream) o).close();
                return;
            }
            if (o instanceof InputStream) {
                ((InputStream) o).close();
                return;
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
    
}
