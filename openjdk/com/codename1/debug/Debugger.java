/*
 * Copyright (c) 2012, Codename One and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Codename One designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *  
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * 
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Please contact Codename One through http://www.codenameone.com/ if you 
 * need additional information or have any questions.
 */
package com.codename1.debug;

/**
 * A class that can be used to serve as a pseudo debugger for Java source code.
 * See <a href="https://github.com/shannah/CN1UWPPort/wiki/Using-the-injectDebug-flag">wiki page</a> for
 * usage instructions.
 * @author Steve Hannah
 */
public abstract class Debugger {
    private static Debugger debugger;
    public static void setDebugger(Debugger debug) {
        Debugger.debugger = debug;
    }
    
    public abstract void debug(String className, String methodName, int lineNum);
    public static void visitLineNumber(String className, String methodName, int lineNum) {
        if (debugger != null) {
            debugger.debug(className, methodName, lineNum);
        }
    }
    
}
