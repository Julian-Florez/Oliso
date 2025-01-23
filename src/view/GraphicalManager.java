package view;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public abstract class GraphicalManager {

    /**
     * Resizes an ImageIcon to the specified width and height.
     *
     * @param imageIcon The ImageIcon to be resized.
     * @param width     The desired width of the resized image.
     * @param height    The desired height of the resized image.
     * @return The resized ImageIcon.
     */
    static public ImageIcon resizeImage(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /**
     * Paints the given image with the specified color and resizes it to the specified width and height.
     *
     * @param imageIcon The ImageIcon to be painted.
     * @param color The color to be applied to the image. If null, the default color is black.
     * @param width The desired width of the resized image.
     * @param height The desired height of the resized image.
     * @return The resized ImageIcon with the painted image.
     */
    static public ImageIcon paintImage(ImageIcon imageIcon, Color color, int width, int height) {
        Image image = imageIcon.getImage();
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(image, 0, 0, null);
        
        // Convert hexadecimal color to RGB values
        if (color == null) {
            color = Color.BLACK;
        }
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                int pixel = bufferedImage.getRGB(x, y);
                if (pixel == Color.WHITE.getRGB()) {
                    bufferedImage.setRGB(x, y, new Color(red, green, blue).getRGB());
                }
            }
        }

        // Enable antialiasing
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        return resizeImage(new ImageIcon(bufferedImage), width, height);
    }

    /**
     * Merges two image icons into a single image icon.
     * 
     * @param imageIcon1 The first image icon to merge.
     * @param imageIcon2 The second image icon to merge.
     * @param width      The desired width of the merged image.
     * @param height     The desired height of the merged image.
     * @return           The merged image icon.
     */
    static public ImageIcon mergeImages(Icon imageIcon1, Icon imageIcon2, int width, int height) {
        Image image1 = resizeImage((ImageIcon) imageIcon1, 500, 500).getImage();
        Image image2 = resizeImage((ImageIcon) imageIcon2, 500, 500).getImage();
        BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.drawImage(image1, 0, 0, null);
        graphics.drawImage(image2, 0, 0, null);
        ImageIcon image = new ImageIcon(bufferedImage);
        image = resizeImage(image, width, height);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return image;
    }
}