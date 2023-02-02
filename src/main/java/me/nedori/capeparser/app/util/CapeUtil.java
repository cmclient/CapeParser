package me.nedori.capeparser.app.util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CapeUtil {

    public static BufferedImage parseCape(BufferedImage image) {
        int i = 64;
        int j = 32;
        int k = image.getWidth();
        for (int l = image.getHeight(); i < k || j < l; j *= 2){
            i *= 2;
        }
        BufferedImage bufferedimage = new BufferedImage(i, j, 2);
        Graphics graphics = bufferedimage.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return bufferedimage;
    }
}
