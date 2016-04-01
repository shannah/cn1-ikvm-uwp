/*
 * Copyright (c) 2000, 2006, Oracle and/or its affiliates. All rights reserved.
 * Copyright (C) 2009, 2012 Volker Berlin (i-net software)
 * Copyright (C) 2010, 2011 Karsten Heinrich (i-net software)
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
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
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package sun.print;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.ServiceUIFactory;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.AttributeSetUtilities;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.ColorSupported;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.CopiesSupported;
import javax.print.attribute.standard.Destination;
import javax.print.attribute.standard.Fidelity;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.MediaTray;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PageRanges;
import javax.print.attribute.standard.PrintQuality;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.print.attribute.standard.PrinterName;
import javax.print.attribute.standard.PrinterResolution;
import javax.print.attribute.standard.PrinterState;
import javax.print.attribute.standard.PrinterStateReasons;
import javax.print.attribute.standard.QueuedJobCount;
import javax.print.attribute.standard.RequestingUserName;
import javax.print.attribute.standard.SheetCollate;
import javax.print.attribute.standard.Sides;
import javax.print.event.PrintServiceAttributeListener;

/**
 * @author Volker Berlin
 */
public class Win32PrintService implements PrintService {
	

    public Win32PrintService(String name, PrintPeer peer){
         throw new Error("Not Implemented");
    }


    @Override
    public String getName(){
        throw new Error("Not Implemented");
    }


    private PrinterName getPrinterName(){
         throw new Error("Not Implemented");
    }


    public void wakeNotifier() {
         throw new Error("Not Implemented");
    }
    

    @Override
    public void addPrintServiceAttributeListener(PrintServiceAttributeListener listener){
       throw new Error("Not Implemented");
    }


    @Override
    public void removePrintServiceAttributeListener(PrintServiceAttributeListener listener){
        throw new Error("Not Implemented");
    }


    @Override
    public DocPrintJob createPrintJob(){
        throw new Error("Not Implemented");
    }


    @Override
    public <T extends PrintServiceAttribute>T getAttribute(Class<T> category){
         throw new Error("Not Implemented");
    }


    @Override
    public PrintServiceAttributeSet getAttributes(){
         throw new Error("Not Implemented");
    }


    @Override
    public Object getDefaultAttributeValue(Class<? extends Attribute> category){
     throw new Error("Not Implemented");
    }


    @Override
    public ServiceUIFactory getServiceUIFactory(){
        return null;
    }


    @Override
    public Class<?>[] getSupportedAttributeCategories(){
        throw new Error("Not Implemented");
    }


    @Override
    public Object getSupportedAttributeValues(Class<? extends Attribute> category, DocFlavor flavor, AttributeSet attributes){
    	 throw new Error("Not Implemented");
    }


    @Override
    public DocFlavor[] getSupportedDocFlavors(){
        throw new Error("Not Implemented");
    }


    @Override
    public AttributeSet getUnsupportedAttributes(DocFlavor flavor, AttributeSet attributes){
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public boolean isAttributeCategorySupported(Class<? extends Attribute> category){
    	 throw new Error("Not Implemented");
    }


    private boolean isPostScriptFlavor(DocFlavor flavor) {
         throw new Error("Not Implemented");
    }

    private boolean isPSDocAttr(Class category) {
        throw new Error("Not Implemented");
    }

    private boolean isAutoSense(DocFlavor flavor) {
         throw new Error("Not Implemented");
    }

    @Override
    public boolean isAttributeValueSupported(Attribute attr, DocFlavor flavor, AttributeSet attributes){
         throw new Error("Not Implemented");
    }


    @Override
    public boolean isDocFlavorSupported(DocFlavor flavor){
        throw new Error("Not Implemented");
    }


    @Override
    public String toString(){
        throw new Error("Not Implemented");
    }


    @Override
    public boolean equals(Object obj){
      throw new Error("Not Implemented");
    }


    @Override
    public int hashCode(){
         throw new Error("Not Implemented");
    }  		
}
