package com.battlingtube.util;


import ij.ImagePlus;
import ij.io.FileSaver;
import ij.io.Opener;
import ij.plugin.JpegWriter;
import ij.process.ImageProcessor;

import java.io.File;

public class ImageOptimizer {

    static public boolean resize(String source, String destination, int size, int quality) throws Exception {
        File file = new File(source).getAbsoluteFile();
        Opener opener = new Opener();
        ImagePlus imagePlus = opener.openImage(file.getParent() + File.separator, file.getName());
        FileSaver fileSaver = new FileSaver(imagePlus);
        ImageProcessor imageProcessor = imagePlus.getProcessor();
        int width = imageProcessor.getWidth();
        int height = imageProcessor.getHeight();
        if ((width > size) || (height > size)) {
            if (width > height) {
                height = (size * height) / width;
                width = size;
            } else {
                width = (size * width) / height;
                height = size;
            }
        }
        imagePlus.setProcessor("1", imageProcessor.resize(width, height));
        if (FileSaver.okForJpeg(imagePlus)) {
            JpegWriter.setQuality(quality);
            fileSaver.saveAsJpeg(destination);
            return true;
        }

        return false;
    }
}
