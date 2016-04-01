/*
  Copyright (C) 2008 - 2013 Volker Berlin (i-net software)

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
package com.sun.imageio.plugins.jpeg;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

import javax.imageio.*;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;

/**
 * A image reader implementation that is calling the .NET API for reading the JPEG image.
 */
class JPEGImageReader extends ImageReader{
    
    private BufferedImage image;

    /**
     * Default constructor, Sun compatible.
     */
    protected JPEGImageReader(ImageReaderSpi originatingProvider){
        super(originatingProvider);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight(int imageIndex) throws IOException{
         throw new Error("Not Implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IIOMetadata getImageMetadata(int imageIndex){
        throw new Error("Not Implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<ImageTypeSpecifier> getImageTypes(int imageIndex){
        throw new Error("Not Implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumImages(boolean allowSearch){
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IIOMetadata getStreamMetadata(){
        throw new Error("Not Implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth(int imageIndex) throws IOException{
       throw new Error("Not Implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException{
         throw new Error("Not Implemented");
    }
    
    /**
     * Read the image with .NET API if not already read.
     */
 

}