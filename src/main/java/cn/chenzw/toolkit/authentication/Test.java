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
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        Graphics2D g = image.createGraphics();

        Random random = new Random();

        g.setColor(ColorUtils.randomColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 50));
        //g.setColor(ColorUtils.randomColor(160, 200));


        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(width);
            int yl = random.nextInt(height);
            g.drawLine(x, y, x + xl, y + yl);

        }
       g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;

            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 20 * i + 11, 50);
        }
        System.out.println(sRand);

        g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
      //  g.setColor(ColorUtils.randomColor(20, 130));
        g.drawString("abc", 32, 30);

        g.dispose();

        FileOutputStream fos = new FileOutputStream(new File("a.jpg"));

        ImageIO.write(image, "JPEG", fos);

    }


    public static void main(String[] args) throws IOException {
        generate();
    }

}
