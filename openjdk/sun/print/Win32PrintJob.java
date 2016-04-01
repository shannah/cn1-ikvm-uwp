/*
 * Copyright (c) 2000, 2013, Oracle and/or its affiliates. All rights reserved.
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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.print.CancelablePrintJob;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSetUtilities;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashPrintJobAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintJobAttribute;
import javax.print.attribute.PrintJobAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Chromaticity;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.Destination;
import javax.print.attribute.standard.DocumentName;
import javax.print.attribute.standard.Fidelity;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.JobOriginatingUserName;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.MediaTray;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PageRanges;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.print.attribute.standard.PrinterState;
import javax.print.attribute.standard.PrinterStateReason;
import javax.print.attribute.standard.PrinterStateReasons;
import javax.print.attribute.standard.RequestingUserName;
import javax.print.attribute.standard.SheetCollate;
import javax.print.event.PrintJobAttributeListener;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;

import sun.awt.windows.WPrinterJob;

/**
 * @author Volker Berlin
 */
public class Win32PrintJob implements CancelablePrintJob{

    Win32PrintJob(Win32PrintService service, PrintPeer peer){
        throw new Error("Not Implemented");
    }


    @Override
    public PrintService getPrintService(){
         throw new Error("Not Implemented");
    }


    @Override
    public PrintJobAttributeSet getAttributes(){
        throw new Error("Not Implemented");
    }


    @Override
    public void addPrintJobListener(PrintJobListener listener){
         throw new Error("Not Implemented");
    }


    @Override
    public void removePrintJobListener(PrintJobListener listener){
        throw new Error("Not Implemented");
    }


    /* Closes any stream already retrieved for the data.
     * We want to avoid unnecessarily asking the Doc to create a stream only
     * to get a reference in order to close it because the job failed.
     * If the representation class is itself a "stream", this
     * closes that stream too.
     */
    private void closeDataStreams() {
        // TODO
    }

    private void notifyEvent(int reason) {

        throw new Error("Not Implemented");
    }

    @Override
    public void addPrintJobAttributeListener(PrintJobAttributeListener listener, PrintJobAttributeSet attributes){
         throw new Error("Not Implemented");
    }


    @Override
    public void removePrintJobAttributeListener(PrintJobAttributeListener listener){
        throw new Error("Not Implemented");
    }


    @Override
    public void print(Doc doc, PrintRequestAttributeSet attributes) throws PrintException{

       throw new Error("Not Implemented");
    }  

    @Override
    public void cancel() throws PrintException{
        throw new Error("Not Implemented");
    }
}
