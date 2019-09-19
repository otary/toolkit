package cn.chenzw.toolkit.authentication;


import cn.chenzw.toolkit.commons.ColorUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Test {

    /**
     * 生成图形验证码
     *
     * @return
     */
    public static void generate() throws IOException {
        int width = 120;
        int height = 62;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(ColorUtils.randomColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(ColorUtils.randomColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;


            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }
        System.out.println(sRand);

        g.dispose();

        FileOutputStream fos = new FileOutputStream(new File("a.jpg"));

        ImageIO.write(image, "JPEG", fos);

        //return new ImageCode(image, sRand, 60);

    }



    public static void main(String[] args) throws IOException {
        generate();
    }

}
