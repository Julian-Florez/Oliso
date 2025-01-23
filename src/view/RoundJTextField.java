package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;

/**
 * A custom JTextField with rounded corners.
 */
public class RoundJTextField extends JTextField {
    private Shape shape;

    /**
     * Constructs a new RoundJTextField with the specified size.
     *
     * @param size the number of columns to display.
     */
    public RoundJTextField(int size) {
        super(size);
        setOpaque(false); // As suggested by @AVD in comment.
    }

    /**
     * Paints the component with rounded background.
     *
     * @param g the Graphics object to paint on.
     */
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        super.paintComponent(g);
    }

    /**
     * Paints the border with rounded corners.
     *
     * @param g the Graphics object to paint on.
     */
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(190, 148, 196, 255));
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
    }

    /**
     * Checks if the specified coordinates are contained within the shape of this component.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     * @return true if the shape contains the point, false otherwise.
     */
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
        }
        return shape.contains(x, y);
    }
}