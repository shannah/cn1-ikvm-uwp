/*
  Copyright (C) 2009 Volker Berlin (i-net software)

  This software is provided 'as-is', without any express or implied
  warranty.  In no event will the authors be held liable for any damages
  arising from the use of this software.

  Permission is granted to anyone to use this software for any purpose,
  including commercial applications, and to alter it and redistribute it
  freely, subject to the following restrictions:

  1. The origin of this software must not be misrepresented; you must not
     claim that you wrote the original software. If you use this software
     in a product, an acknowledgment in the product documentation would be
     appreciated but is not required.
  2. Altered source versions must be plainly marked as such, and must not be
     misrepresented as being the original software.
  3. This notice may not be removed or altered from any source distribution.

  Jeroen Frijters
  jeroen@frijters.net
  
 */
package sun.print;

import ikvm.awt.IkvmToolkit;

import java.awt.Toolkit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import javax.print.DocFlavor;
import javax.print.MultiDocPrintService;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;

public class Win32PrintServiceLookup extends PrintServiceLookup {
    
    /* Want the PrintService which is default print service to have
     * equality of reference with the equivalent in list of print services
     * This isn't required by the API and there's a risk doing this will
     * lead people to assume its guaranteed.
     */
    public synchronized PrintService[] getPrintServices() {
        throw new Error("Not Implemented");
    }

    private synchronized void refreshServices() {
       throw new Error("Not Implemented");
    }


    public synchronized PrintService getPrintServiceByName(String name) {
		throw new Error("Not Implemented");
    }

    boolean matchingService(PrintService service,
                            PrintServiceAttributeSet serviceSet) {
         throw new Error("Not Implemented");
    }

    public PrintService[] getPrintServices(DocFlavor flavor,
                                           AttributeSet attributes) {

        throw new Error("Not Implemented");
    }

    /*
     * return empty array as don't support multi docs
     */
    public MultiDocPrintService[]
        getMultiDocPrintServices(DocFlavor[] flavors,
                                 AttributeSet attributes) {
 throw new Error("Not Implemented");
    }


    public synchronized PrintService getDefaultPrintService() {
      throw new Error("Not Implemented");
     }
}
