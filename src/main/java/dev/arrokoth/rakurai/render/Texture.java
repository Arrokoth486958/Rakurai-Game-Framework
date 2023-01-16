package dev.arrokoth.rakurai.render;

import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class Texture {
    protected final int texId;

    protected Texture(int texId) {
        this.texId = texId;
    }

    public static Texture loadTexture(URL url) throws Exception {
        return convert(ImageIO.read(url));
    }

    public static Texture convert(BufferedImage image) {
        int texId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texId);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        BufferedImage awtImage = image;
        int w = awtImage.getWidth();
        int h = awtImage.getHeight();

        ByteBuffer pixels = BufferUtils.createByteBuffer(w * h * Integer.BYTES);
        int[] rawPixels = new int[w * h];
        awtImage.getRGB(0, 0, w, h, rawPixels, 0, w);

        for (int i = 0; i < rawPixels.length; ++i) {
            int a = rawPixels[i] >> 24 & 0xFF;
            int r = rawPixels[i] >> 16 & 0xFF;
            int g = rawPixels[i] >> 8 & 0xFF;
            int b = rawPixels[i] & 0xFF;
            rawPixels[i] = a << 24 | b << 16 | g << 8 | r;
        }

        pixels.asIntBuffer().put(rawPixels);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
        return new Texture(texId);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, texId);
    }

    public void unBind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public static Texture getNull() {
        return new Texture(0);
    }
}
