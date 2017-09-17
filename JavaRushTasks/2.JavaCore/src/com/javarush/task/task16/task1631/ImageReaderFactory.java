package com.javarush.task.task16.task1631;

import com.javarush.task.task16.task1631.common.*;

/**
 * Created by Sergej Iryupin on 17.09.2017.
 */
public class ImageReaderFactory {

    public static ImageReader getImageReader(ImageTypes imageType) throws IllegalArgumentException {

        if (imageType == ImageTypes.JPG)
            return new JpgReader();

        if (imageType == ImageTypes.BMP)
            return new BmpReader();

        if (imageType == ImageTypes.PNG)
            return new PngReader();

        throw new IllegalArgumentException("Неизвестный тип картинки");
    }
}