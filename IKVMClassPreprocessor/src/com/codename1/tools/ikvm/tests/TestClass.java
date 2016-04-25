/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.tools.ikvm.tests;

import java.io.IOException;

/**
 *
 * @author shannah
 */
public class TestClass {
    public void someFunc() {
        
    }
    
    public synchronized void someSyncFunc() {
        
    }
    
    public synchronized int someSyncFuncWithIntReturn() {
        return 0;
    }
    
    public synchronized String someSyncFuncWithStringReturnAndIntInput(int input) {
        return "0";
    }
    
    private String testSync(String arg1, int arg2, short arg3, long arg4, boolean arg5, char arg6, byte arg7) throws IOException {
        return "foo";
    }
    
    public synchronized String testSync_sync(String arg1, int arg2, short arg3, long arg4, boolean arg5, char arg6, byte arg7) throws IOException {
        return testSync(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }
    
    private void vtestSync(String arg1, int arg2, short arg3, long arg4, boolean arg5, char arg6, byte arg7) throws IOException {
        
    }
    
    public synchronized void vtestSync_sync(String arg1, int arg2, short arg3, long arg4, boolean arg5, char arg6, byte arg7) throws IOException {
        vtestSync(arg1, arg2, arg3, arg4, arg5, arg6, arg7);
    }
    
    public synchronized void foobar() {
        try {
            int i =0;
        } catch (Exception ex) {
            
        }
    }
    
    public static synchronized void sfoobar() {
        try {
            int i =0;
        } catch (Exception ex) {
            
        }
    }
    
    public synchronized void foobarwarray(long l, long l2) {
        try {
            int i =0;
        } catch (Exception ex) {
            
        }
    }
    public synchronized void foobarwarray(double l, double l2) {
        try {
            int i =0;
        } catch (Exception ex) {
            
        }
    }
    
    public synchronized void foobarwarray(double l, long l2, byte[] b1, int[] b2, Object foo, String bar) {
        try {
            int i =0;
        } catch (Exception ex) {
            
        }
    }


}
