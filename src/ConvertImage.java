import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;

public class ConvertImage {
    BufferedImage img = null;
    File f = null;
    int pixels[][][] = new int[3][3][4];

    //read image
    public int[][][] LoadImage(){
        try {
            f = new File("C:\\Users\\el_re\\Desktop\\IA\\punto0.png");
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }

        //get image width and height
        int width = img.getWidth();
        int height = img.getHeight();

        //convert to grayscale
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                pixels[y][x][0] = r;
                pixels[y][x][1] = g;
                pixels[y][x][2] = b;

                if ((r == 0 && g == 0 && b == 0) || (r == 255 && g == 255 && b == 255)){
                    pixels[y][x][3] = 1;
                } else {
                    pixels[y][x][3] = 0;
                }

                System.out.println("rgb: " + r + ", " + g + ", " + b);
            }
        }
        return pixels;
    }
    public int[][][] LoadImage(String archivo){
        try {
            f = new File(archivo);
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }

        //get image width and height
        int width = img.getWidth();
        int height = img.getHeight();

        //convert to grayscale
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                pixels[y][x][0] = r;
                pixels[y][x][1] = g;
                pixels[y][x][2] = b;

                if ((r == 0 && g == 0 && b == 0)){
                    pixels[y][x][3] = 1;
                } else {
                    pixels[y][x][3] = 0;
                }

                System.out.println("rgb: " + r + ", " + g + ", " + b);
            }
        }
        return pixels;
    }

    public void writeImage(String ruta_origen, String ruta_destino) {

        try {
            f = new File(ruta_origen);
            img = ImageIO.read(f);
            ImageIO.write(img, "png", new File(ruta_destino));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}