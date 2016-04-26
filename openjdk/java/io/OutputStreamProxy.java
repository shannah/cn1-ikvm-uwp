/*
 * Copyright (c) 1994, 2013, Oracle and/or its affiliates. All rights reserved.
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

package java.io;



public
class OutputStreamProxy extends OutputStream
{
   private cli.System.IO.Stream internalStream;
        public OutputStreamProxy(cli.System.IO.Stream internalStream)
        {
            //base.@this();
            this.internalStream = internalStream;
        }

        public void close() throws IOException
        {
            if (internalStream == null)
            {
                return;
            }
            try
            {
				if (false) throw new cli.System.Exception();
                synchronized (internalStream) {
                    internalStream.Dispose();
				}
            }
            catch (cli.System.Exception e)
            {
                //internalStream = null;
				throw new IOException(e.get_Message());
            }
            internalStream = null;
        }

        public void flush() throws IOException
        {
            if (internalStream == null)
            {
                return;
            }
            try
            {
				if (false) throw new cli.System.Exception();
                synchronized (internalStream) {
                    internalStream.Flush();
					}
            }
            catch (cli.System.Exception e)
            {
				throw new IOException(e.get_Message());
                // internalStream = null;
            }
        }

        public void write(byte[] n1) throws IOException
        {
			try {
				if (false) throw new cli.System.Exception();
				write(n1, 0, n1.length);
		    } catch (cli.System.Exception e) {
				throw new IOException(e.get_Message());
			}
        }

        public void write(byte[] n1, int n2, int n3) throws IOException 
        {
			try {
				if (false) throw new cli.System.Exception();
				if (internalStream != null)
					synchronized (internalStream) {
						internalStream.Write(n1, n2, n3);
				}
			} catch (cli.System.Exception e) {
				throw new IOException(e.get_Message());
			}
        }

        public void write(int n1) throws IOException
        {
			try {
				if (false) throw new cli.System.Exception();
				if (internalStream != null) {
					synchronized (internalStream) {
						internalStream.WriteByte((byte)n1);
					}
				}
			} catch (cli.System.Exception e) {
				throw new IOException(e.get_Message());
			}

        }

}
