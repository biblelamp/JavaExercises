// from https://evileg.com/ru/post/402/

interface ImageReader {
    DecodedImage getDecodeImage();
}

class DecodedImage {
    private String image;

    public DecodedImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return image + ": is decoded";
    }
}

class GifReader implements ImageReader {
    private DecodedImage decodedImage;

    public GifReader(String image) {
        decodedImage = new DecodedImage(image);
    }

    @Override
    public DecodedImage getDecodeImage() {
        return decodedImage;
    }
}

class JpegReader implements ImageReader {
    private DecodedImage decodedImage;

    public JpegReader(String image) {
        decodedImage = new DecodedImage(image);
    }

    @Override
    public DecodedImage getDecodeImage() {
        return decodedImage;
    }
}

public class FactoryMethodExample {
    public static void main(String[] args) {
        ImageReader reader = null;
        String[] files = {"image.gif", "image.jpeg"};

        for (String file : files) {
            if (file.endsWith("gif")) {
                reader = new GifReader(file);
            }
            if (file.endsWith("jpeg")) {
                reader = new JpegReader(file);
            }
            DecodedImage decodedImage = reader.getDecodeImage();
            System.out.println(decodedImage);
        }
    }
}