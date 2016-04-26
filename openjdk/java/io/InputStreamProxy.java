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
class InputStreamProxy extends InputStream
{
  private cli.System.IO.Stream internalStream;
        private long markedPosition = 0;

        public InputStreamProxy(cli.System.IO.Stream internalStream)
        {
            //base.@this();
            this.internalStream = internalStream;
        }

        public int available() throws IOException
        {
            return 0;
        }

        //public override void close()
        //{
        //    internalStream.Dispose();
        // }

        public void close() throws IOException 
        {
			try {
				if (false) throw new cli.System.Exception();
				internalStream.Dispose();
			} catch (cli.System.Exception ex) {
				throw new IOException("Failed to dispose proxy stream");
			}
            
        }

        public boolean markSupported()
        {
            return internalStream.get_CanSeek();
        }
        public void mark(int readlimit)
        {
            markedPosition = internalStream.get_Position();
        }

        public int read() throws IOException
        {
			try {
				if (false) throw new cli.System.Exception();
				return internalStream.ReadByte();
			} catch (cli.System.Exception ex) {
				throw new IOException("Failed to read proxy stream");
			}
            
        }

        public int read(byte[] n1) throws IOException
        {

			try {
				if (false) throw new cli.System.Exception();
				return read(n1, 0, n1.length);
			} catch (cli.System.Exception ex) {
				throw new IOException("Failed to read proxy stream");
			}
            

        }

        public int read(byte[] sb, int n2, int n3) throws IOException
        {
			try {
				if (false) throw new cli.System.Exception();
				int v = internalStream.Read(sb, n2, n3);
				if (v <= 0)
				{
					return -1;

				}
				return v;
			} catch (cli.System.Exception ex) {
				throw new IOException("Failed to read proxy stream");
			}

            
        }

        public void reset() throws IOException
        {
			try {
				if (false) throw new cli.System.Exception();
				internalStream.Seek(markedPosition, cli.System.IO.SeekOrigin.wrap( cli.System.IO.SeekOrigin.Begin));
			} catch (cli.System.Exception ex) {
				throw new IOException("Failed to dispose proxy stream");
			}
            
        }

        public long skip(long n) throws IOException
        {
			try {
				if (false) throw new cli.System.Exception();
				internalStream.Seek(n, cli.System.IO.SeekOrigin.wrap(cli.System.IO.SeekOrigin.Current));
				return n;
			} catch (cli.System.Exception ex) {
				throw new IOException("Failed to seek proxy stream");
			}
            
        }

}
