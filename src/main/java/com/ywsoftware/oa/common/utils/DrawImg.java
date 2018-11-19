package com.ywsoftware.oa.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class DrawImg {

    public static String drawImg(ByteArrayOutputStream output) {
        String code = "";
        for (int i = 0; i < 4; i++) {
            code += randomChar();
        }
        int width = 70;
        int height = 25;
        BufferedImage bfi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("terminus-font", Font.PLAIN, 20);
        Graphics2D g2d = bfi.createGraphics();
        g2d.setFont(font);
        Color color = new Color(66, 2, 82);
        g2d.setColor(color);
        g2d.setBackground(new Color(226, 226, 240));
        g2d.clearRect(0, 0, width, height);
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        double xValue = (width - bounds.getWidth()) / 2;
        double yValue = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = yValue - ascent;
        g2d.drawString(code, (int) xValue, (int) baseY);
        g2d.dispose();
        try {
            ImageIO.write(bfi, "jpg", output);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return code;
    }

    private static char randomChar() {
        Random rdm = new Random();
        String str = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        return str.charAt(rdm.nextInt(str.length()));
    }
}
