
package view;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import model.Square;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;

/**
 * The GameWindow class represents the game window of the Oliso game.
 * It provides methods for setting up the game window, handling mode selection,
 * and color selection.
 */
public class GameWindow {

    private JFrame frame = new JFrame("Oliso");
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private ArrayList<JRadioButton> pieces = new ArrayList<JRadioButton>();
    private ArrayList<JTextField> names = new ArrayList<JTextField>();
    private ArrayList<Color> colors = new ArrayList<Color>(Arrays.asList(new Color(252,122,87,255), new Color(18,64,128,255), new Color(6,214,160,255), new Color(255,224,102,255)));
    private ArrayList<JButton> colorIcons = new ArrayList<JButton>(Arrays.asList(new JButton(), new JButton(), new JButton(), new JButton()));
    private ArrayList<Boolean> smallMerged = new ArrayList<Boolean>();
    private ArrayList<Boolean> mediumMerged = new ArrayList<Boolean>();
    private ArrayList<Boolean> largeMerged = new ArrayList<Boolean>();
    private ArrayList<JPanel> piecepanels = new ArrayList<JPanel>();
    private ArrayList<JButton> shopbuttons = new ArrayList<JButton>();
    private ArrayList<String> baseTypes = new ArrayList<String>(Arrays.asList("circle", "flower", "star", "rounded", "sun"));
    private model.GameModel game;
    private JLabel background;
    private JLabel backcolor;
    private JButton playbutton;
    private JButton backbutton;
    private JButton shopbutton;
    private JButton shopbackbutton;
    private JButton againbutton;
    private JButton passButton;
    private JButton button1v1;
    private JButton button2v2;
    private JButton button4v4;
    private JPanel buttonpanel;
    private JPanel boardpanel;
    private Font customfont;
    private String selectedBase = "circle";
    private int width;

    /**
     * Constructs a new GameWindow object.
     *
     * @param game the GameModel object representing the game
     */
    public GameWindow(model.GameModel game) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/icon.png")), 64, 64).getImage());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        this.game = game;
        width = frame.getWidth();

        while (width < 100) {
            width = frame.getWidth();
            try {
                Thread.sleep(10); // Wait for 10 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
                background.setIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/background.png")), frame.getWidth()+50, frame.getHeight()+50));
                backcolor.setIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/backgroundColor.png")), frame.getWidth()+50, frame.getHeight()+50));
            }
        });

        this.background = new JLabel();
        this.backcolor = new JLabel();
        try {
            loadFont();
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        background.setIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/background.png")), frame.getWidth()+50, frame.getHeight()+50));
        backcolor.setIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/backgroundColor.png")), frame.getWidth()+50, frame.getHeight()+50));
        modeSelection();
    }

    /**
     * Clears the names, smallMerged, mediumMerged, and largeMerged lists.
     * Initializes the smallMerged, mediumMerged, and largeMerged lists with false values.
     * Removes all components from the frame's content pane.
     * Sets the layout of the frame to GridBagLayout.
     * Creates a panel with GridBagLayout and sets it as opaque.
     * Creates and configures buttons with icons and sets them as opaque.
     * Adds the buttons and other components to the panel using GridBagConstraints.
     * Adds the panel and background to the frame's content pane.
     * Revalidates and repaints the frame.
     */
    public void modeSelection() {
        names.clear();
        smallMerged.clear();
        mediumMerged.clear();
        largeMerged.clear();

        for (int i = 0; i < 9; i++) {
            smallMerged.add(false);
        }

        for (int i = 0; i < 9; i++) {
            mediumMerged.add(false);
        }

        for (int i = 0; i < 9; i++) {
            largeMerged.add(false);
        }

        frame.getContentPane().removeAll();

        frame.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);

        button4v4 = new JButton();
        button4v4.setIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/4v4.png")), getWidth()/8, getWidth()/16));
        button4v4.setBorderPainted(false);
        button4v4.setContentAreaFilled(false);
        button4v4.setRolloverIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/4v4Hover.png")), getWidth()/8, getWidth()/16));
        button4v4.setPressedIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/4v4Hover.png")), getWidth()/8, getWidth()/16));
        button4v4.setOpaque(false);

        button1v1 = new JButton();
        button1v1.setIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/1v1.png")), getWidth()/8, getWidth()/16));
        button1v1.setBorderPainted(false);
        button1v1.setContentAreaFilled(false);
        button1v1.setRolloverIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/1v1Hover.png")), getWidth()/8, getWidth()/16));
        button1v1.setPressedIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/1v1Hover.png")), getWidth()/8, getWidth()/16));
        button1v1.setOpaque(false);

        button2v2 = new JButton();
        button2v2.setIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/2v2.png")), getWidth()/8, getWidth()/16));
        button2v2.setBorderPainted(false);
        button2v2.setContentAreaFilled(false);
        button2v2.setRolloverIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/2v2Hover.png")), getWidth()/8, getWidth()/16));
        button2v2.setPressedIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/2v2Hover.png")), getWidth()/8, getWidth()/16));
        button2v2.setOpaque(false);

        shopbutton = new JButton();
        shopbutton.setIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/shop.png")), getWidth()/16, getWidth()/16));
        shopbutton.setBorderPainted(false);
        shopbutton.setContentAreaFilled(false);
        shopbutton.setRolloverIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/shopHover.png")), getWidth()/16, getWidth()/16));
        shopbutton.setPressedIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/shopHover.png")), getWidth()/16, getWidth()/16));
        shopbutton.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(new JLabel(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/logo.png")), getWidth()/2, getWidth()/4)), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(button4v4,gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(button1v1,gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        panel.add(button2v2,gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.insets.set(50,50,50,50);
        panel.add(shopbutton,gbc);


        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets.set(0,0,0,0);
        frame.add(panel,gbc);
        frame.add(background,gbc);
        frame.revalidate();
        frame.repaint();
    }


    /**
     * Loads a custom font for the game window.
     *
     * @throws FontFormatException if the font file is not in the correct format.
     * @throws IOException if an I/O error occurs while reading the font file.
     */
    public <InputStream> void loadFont() throws FontFormatException, IOException{
        try{
            InputStream is = (InputStream) getClass().getClassLoader().getResourceAsStream("assets/menu/SaedaBold.otf");
            customfont = Font.createFont(Font.TRUETYPE_FONT, (java.io.InputStream) is);
        } catch (IOException | FontFormatException e) {
            InputStream is = (InputStream) getClass().getClassLoader().getResourceAsStream("src/assets/menu/SaedaBold.otf");
            customfont = Font.createFont(Font.TRUETYPE_FONT, (java.io.InputStream) is);
        }
        customfont = customfont.deriveFont(Font.PLAIN, 16);
    }

    /**
     * Sets up the color selection screen in the game window.
     *
     * @param players the number of players
     * @param mode the game mode
     */
    public void ColorSelection(int players, int mode) {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        playbutton = new JButton(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/play.png")), getWidth()/8, getWidth()/16));
        playbutton.setBorderPainted(false);
        playbutton.setContentAreaFilled(false);
        playbutton.setRolloverIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/playHover.png")), getWidth()/8, getWidth()/16));
        playbutton.setPressedIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/playHover.png")), getWidth()/8, getWidth()/16));
        playbutton.setOpaque(false);


        backbutton = new JButton(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/back.png")), getWidth()/16, getWidth()/32));
        backbutton.setBorderPainted(false);
        backbutton.setContentAreaFilled(false);
        backbutton.setRolloverIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/backHover.png")), getWidth()/16, getWidth()/32));
        backbutton.setPressedIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/backHover.png")), getWidth()/16, getWidth()/32));
        backbutton.setOpaque(false);

        buttonpanel = new JPanel();
        buttonpanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.ipadx = 150;
        gbc.ipady = 50;
        gbc.insets.set(20,20,20,20);

        for (int i = 1; i <= players; i++) {
            final int j = i;
            JTextField text = new RoundJTextField(100);
            text.setForeground(new Color(32,25,66,255));
            text.setBackground(new Color(94,84,142,255));
            text.setFont(customfont);

            String playmode = game.getMode() == 2 ? "Team" : "Player";
            text.setText(playmode + " " + i + " name");
            text.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    text.setText("");
                }

                public void focusLost(java.awt.event.FocusEvent evt) {
                    if (text.getText().equals("")) {
                        text.setText(playmode + " " + j + " name");
                    }
                }
            });


            names.add(text);
            gbc.gridx = i-1;
            gbc.gridy = 1;
            buttonpanel.add(text,gbc);
        }

        gbc.ipadx = 0;
        gbc.ipady = 0;

        for (int i = 0; i < players; i++) {
            final int j = i;
            JButton edit = new JButton();
            edit.setIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/edit.png")), getWidth()/13, getWidth()/13));
            edit.setOpaque(false);
            edit.setContentAreaFilled(false);
            edit.setBorderPainted(false);
            edit.setRolloverIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/editHover.png")), getWidth()/13, getWidth()/13));
            edit.setPressedIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/editHover.png")), getWidth()/13, getWidth()/13));
            edit.addActionListener(e -> {
                Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
                if (newColor != null){
                    colors.set(j,newColor);
                }
                else{
                    colors.set(j,Color.BLACK);
                }
                updateColors(j);
                frame.revalidate();
                frame.repaint();
            });

            gbc.gridx = i;
            gbc.gridy = 2;
            buttonpanel.add(edit,gbc);
        }

        for (int i = 0; i < players; i++) {
            JButton button = new JButton();
            button.setIcon(GraphicalManager.paintImage(new ImageIcon(getClass().getResource("/assets/bases/"+ selectedBase +"/mediumPiece.png")), colors.get(i), getWidth()/13, getWidth()/13));
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            colorIcons.set(i, button);

            gbc.gridx = i;
            gbc.gridy = 3;
            buttonpanel.add(button,gbc);
        }

        panel.setOpaque(false);
        buttonpanel.setOpaque(false);


        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(buttonpanel,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(playbutton,gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets.set(20,20,20,20);
        panel.add(backbutton,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets.set(0,0,0,0);
        frame.add(panel,gbc);
        frame.add(backcolor,gbc);

        frame.revalidate();
        frame.repaint();
    }

    /**
     * Returns the play button of the game window.
     *
     * @return the play button
     */
    public JButton getPlayButton() {
        return playbutton;
    }


    /**
     * Returns the back button of the game window.
     *
     * @return the back button of the game window
     */
    public JButton getBackButton() {
        return backbutton;
    }

    /**
     * Returns the shop button.
     *
     * @return the shop button
     */
    public JButton getShopButton() {
        return shopbutton;
    }

    /**
     * Updates the colors of the icons in the game window.
     *
     * @param i the index of the color icon to update
     */
    public void updateColors(int i) {
        colorIcons.get(i).setIcon(GraphicalManager.paintImage(new ImageIcon(getClass().getResource("/assets/bases/"+ selectedBase +"/mediumPiece.png")), getColors().get(i), getWidth()/13, getWidth()/13));
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Returns the list of names entered by the user.
     *
     * @return the list of names entered by the user
     */
    public ArrayList<JTextField> getNames() {
        return names;
    }

    /**
     * Returns the list of colors.
     *
     * @return the list of colors
     */
    public ArrayList<Color> getColors() {
        return colors;
    }

    /**
     * Returns the list of base types.
     *
     * @return the list of base types
     */
    public ArrayList<String> getBaseTypes() {
        return baseTypes;
    }

    /**
     * Displays an error message indicating that the colors must be different.
     */
    public void showColorError(){
        JOptionPane.showMessageDialog(frame, "Colors must be different");
    }

    /**
     * Sets the game model for the game window.
     *
     * @param game the game model to set
     */
    public void setGame(model.GameModel game) {
        this.game = game;
    }

    /**
     * Returns the JButton object for the 4v4 button.
     *
     * @return the JButton object for the 4v4 button
     */
    public JButton getButton4v4() {
        return button4v4;
    }

    /**
     * Returns the button for 1v1 mode.
     *
     * @return the button for 1v1 mode
     */
    public JButton getButton1v1() {
        return button1v1;
    }

    /**
     * Returns the button for 2v2 game mode.
     *
     * @return the button for 2v2 game mode
     */
    public JButton getButton2v2() {
        return button2v2;
    }

    /**
     * Displays the shop window with buttons for different base types.
     * Removes all components from the frame and adds the necessary components for the shop window.
     * The buttons are created dynamically based on the base types.
     */
    public void shopwindow(){
        frame.getContentPane().removeAll();
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel backpanel = new JPanel();
        backpanel.setLayout(new GridLayout(2,3));

        shopbackbutton = new JButton();
        shopbackbutton.setIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/back.png")), getWidth()/16, getWidth()/32));
        shopbackbutton.setBorderPainted(false);
        shopbackbutton.setContentAreaFilled(false);
        shopbackbutton.setRolloverIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/backHover.png")), getWidth()/16, getWidth()/32));
        shopbackbutton.setPressedIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/backHover.png")), getWidth()/16, getWidth()/32));
        shopbackbutton.setOpaque(false);

        backpanel.add(shopbackbutton);

        backpanel.setOpaque(false);

        for(int i=0; i<5; i++){
            JButton button = new JButton();
            button.setIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/bases/" + baseTypes.get(i) +"/example.png")), getWidth()/8, getWidth()/8));
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setOpaque(false);
            button.setRolloverIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/bases/" + baseTypes.get(i) +"/exampleHover.png")), getWidth()/8, getWidth()/8));
            button.setPressedIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/bases/" + baseTypes.get(i) +"/exampleHover.png")), getWidth()/8, getWidth()/8));
            backpanel.add(button, gbc);
            shopbuttons.add(button);
        }


        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(backpanel, gbc);
        frame.add(backcolor, gbc);

        frame.revalidate();
        frame.repaint();
    }

    /**
     * Returns the shop back button.
     *
     * @return the shop back button
     */
    public JButton getShopBackButton() {
        return shopbackbutton;
    }

    /**
     * Returns the list of shop buttons.
     *
     * @return the list of shop buttons
     */
    public ArrayList<JButton> getShopButtons() {
        return shopbuttons;
    }

    /**
     * Clears the list of shop buttons.
     */
    public void clearShopButtons() {
        shopbuttons.clear();
    }

    /**
     * Displays the game window with the given game model.
     *
     * @param game The game model to be displayed.
     */
    public void showGame(model.GameModel game) {
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().removeAll();
        frame.repaint();
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel backpanel = new JPanel();
        backpanel.setLayout(new GridBagLayout());
        buttonpanel = new JPanel();
        boardpanel = new JPanel();
        boardpanel.setLayout(new GridLayout(3,3));
        ButtonGroup group = new ButtonGroup();
        buttonpanel.setLayout(new GridLayout(3,3));

        for(int i=0; i<9; i++) {
            JButton button = new JButton();
            button.setIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/bases/"+ selectedBase +"/base.png")), getWidth()/13, getWidth()/13));
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            boardpanel.add(button);
            buttons.add(button);
        }

        for(int i=0; i<9; i++) {
            JRadioButton button = new JRadioButton();
            button.setEnabled(game.getUser(game.getTurn().getActualPlayer()).getPieces().get(i).isAvaliable());
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            setImage(button, i, getWidth(), game.getTurn().getActualPlayer());
            group.add(button);
            buttonpanel.add(button);
            pieces.add(button);
        }

        for(int i=0; i<4; i++) {

            ButtonGroup piecegroup = new ButtonGroup();
            JPanel piecepanel = new JPanel();
            piecepanel.setLayout(new GridLayout(3,3));
            piecepanel.setOpaque(false);

            for(int j=0; j<9; j++) {
                JRadioButton button = new JRadioButton();
                button.setEnabled(game.getUser(i).getPieces().get(j).isAvaliable());
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                setImage(button, j, getWidth()/2, i);
                piecegroup.add(button);
                piecepanel.add(button);
            }
            piecepanels.add(piecepanel);
        }

        backpanel.setOpaque(false);
        boardpanel.setOpaque(false);
        buttonpanel.setOpaque(false);

        passButton = new JButton();
        passButton.setIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/pass.png")), getWidth()/16, getWidth()/32));
        passButton.setBorderPainted(false);
        passButton.setContentAreaFilled(false);
        passButton.setRolloverIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/passHover.png")), getWidth()/16, getWidth()/32));
        passButton.setPressedIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/passHover.png")), getWidth()/16, getWidth()/32));
        passButton.setOpaque(false);


        gbc.gridx = 1;
        gbc.gridy = 1;
        backpanel.add(boardpanel, gbc);


        gbc.gridx = 1;
        gbc.gridy = 2;
        backpanel.add(buttonpanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        backpanel.add(passButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets.set(50,50,50,50);
        backpanel.add(piecepanels.get(0), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        backpanel.add(piecepanels.get(2), gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        backpanel.add(piecepanels.get(1), gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        backpanel.add(piecepanels.get(3), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets.set(0,0,0,0);
        frame.add(backpanel, gbc);
        frame.add(backcolor, gbc);

        frame.revalidate();
        frame.repaint();
        update(game);
    }

    /**
     * Returns the width of the game window.
     *
     * @return the width of the game window
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the image of a given JRadioButton based on the provided parameters.
     *
     * @param button the JRadioButton to set the image for
     * @param i the index used to determine the image size
     * @param size the size of the image
     * @param user the user identifier
     */
    public void setImage(JRadioButton button, int i, int size, int user){

        ImageIcon icon;
        String iconsize;

        switch (i) {
            case 0:
            case 1:
            case 2:
                iconsize = "smallPiece.png";
                break;
            case 3:
            case 4:
            case 5:
                iconsize = "mediumPiece.png";
                break;
            case 6:
            case 7:
            case 8:
                iconsize = "bigPiece.png";
                break;
            default:
                iconsize = "base.png";

                break;
        }
        icon = GraphicalManager.paintImage(new ImageIcon(getClass().getResource("/assets/bases/"+selectedBase+"/"+iconsize)), game.getUser(user).getColor(), size/13, size/13);
        button.setIcon(icon);


    }

    /**
     * Updates the game window with the current state of the game.
     *
     * @param game The game model containing the current state of the game.
     */
    public void update(model.GameModel game) {
        this.width = frame.getWidth();
        for(int i=0; i<9; i++) {
            JButton button = buttons.get(i);
            Square square = game.getBoard().getSquares().get(i/3).get(i%3);
            if(!square.isSmall_avaliable() && !smallMerged.get(i)){
                button.setIcon(GraphicalManager.mergeImages(button.getIcon(), square.getSmallPiece().getIcon(), getWidth()/13, getWidth()/13));
                smallMerged.set(i, true);
            };
            if(!square.isMedium_avaliable() && !mediumMerged.get(i)){
                button.setIcon(GraphicalManager.mergeImages(button.getIcon(), square.getMediumPiece().getIcon(), getWidth()/13, getWidth()/13));
                mediumMerged.set(i, true);
            };
            if(!square.isLarge_avaliable() && !largeMerged.get(i)){
                button.setIcon(GraphicalManager.mergeImages(button.getIcon(), square.getLargePiece().getIcon(), getWidth()/13, getWidth()/13));
                largeMerged.set(i, true);
            };
        }

        for(int i=0; i<9; i++) {
            JRadioButton button = pieces.get(i);
            setImage(button, i, getWidth(), game.getTurn().getActualPlayer());
            pieces.get(i).setEnabled(game.getUser(game.getTurn().getActualPlayer()).getPieces().get(i).isAvaliable());
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                JRadioButton button = (JRadioButton) piecepanels.get(i).getComponent(j);
                button.setEnabled(game.getUser(i).getPieces().get(j).isAvaliable());
                setImage(button, j, getWidth()/2, i);
            }
        }

        frame.revalidate();
    }

    /**
     * Returns the selected base.
     *
     * @return the selected base as a String.
     */
    public String getSelectedBase() {
        return selectedBase;
    }

    /**
     * Sets the selected base for the game.
     *
     * @param selectedBase the selected base to set
     */
    public void setSelectedBase(String selectedBase) {
        this.selectedBase = selectedBase;
    }

    /**
     * Returns the list of buttons in the game window.
     *
     * @return the list of buttons
     */
    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    /**
     * Returns the pass button.
     *
     * @return the pass button
     */
    public JButton getPassButton() {
        return passButton;
    }

    /**
     * Clears the buttons and pieces lists.
     */
    public void clearButtons() {
        buttons.clear();
        pieces.clear();
    }

    /**
     * Returns the list of JRadioButtons representing the pieces in the game.
     *
     * @return the list of JRadioButtons representing the pieces
     */
    public ArrayList<JRadioButton> getPieces() {
        return pieces;
    }

    /**
     * Returns the "Again" button of the game window.
     *
     * @return the "Again" button of the game window
     */
    public JButton getAgainButton() {
        return againbutton;
    }

    /**
     * Checks if the game has been won or ended in a draw and updates the game window accordingly.
     * If the game has been won, it displays the winner and provides an option to play again.
     * If the game has ended in a draw, it displays a message indicating a draw and provides an option to play again.
     */
    public void checkWin() {
        if(game.getWin()) {

            JPanel board = boardpanel;
            frame.getContentPane().removeAll();
            frame.setLayout(new GridBagLayout());
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            JButton text = new JButton();
            String mode = game.getMode() == 2 ? "Team" : "Player";
            text.setContentAreaFilled(false);
            text.setBorderPainted(false);
            text.setForeground(new Color(32,25,66,255));
            text.setFont(customfont.deriveFont(Font.PLAIN, width/30));
            text.setText( mode +" "+ game.getWinner() + " wins!");

            againbutton = new JButton(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/again.png")), getWidth()/4, getWidth()/8));
            againbutton.setBorderPainted(false);
            againbutton.setContentAreaFilled(false);
            againbutton.setRolloverIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/againHover.png")), getWidth()/4, getWidth()/8));
            againbutton.setPressedIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/againHover.png")), getWidth()/4, getWidth()/8));
            againbutton.setOpaque(false);

            panel.setOpaque(false);
            text.setOpaque(false);
            againbutton.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 1;
            gbc.gridy = 1;
            panel.add(text, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            panel.add(againbutton,gbc);

            gbc.gridx = 2;
            gbc.gridy = 2;
            panel.add(board,gbc);

            frame.add(panel, gbc);
            frame.add(backcolor, gbc);

            frame.revalidate();
            frame.repaint();
        }

        else if(game.getDraw()) {

            JPanel board = boardpanel;
            frame.getContentPane().removeAll();
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            frame.setLayout(new GridBagLayout());
            JButton text = new JButton();
            text.setContentAreaFilled(false);
            text.setBorderPainted(false);
            text.setForeground(new Color(32,25,66,255));
            text.setFont(customfont.deriveFont(Font.PLAIN, 50));
            text.setText("Draw!");


            againbutton = new JButton(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/again.png")), getWidth()/4, getWidth()/8));
            againbutton.setBorderPainted(false);
            againbutton.setContentAreaFilled(false);
            againbutton.setRolloverIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/againHover.png")), getWidth()/4, getWidth()/8));
            againbutton.setPressedIcon(GraphicalManager.resizeImage(new ImageIcon(getClass().getResource("/assets/menu/againHover.png")), getWidth()/4, getWidth()/8));
            againbutton.setOpaque(false);


            panel.setOpaque(false);

            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 1;
            gbc.gridy = 1;
            panel.add(text,gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            panel.add(againbutton,gbc);

            gbc.gridx = 2;
            gbc.gridy = 2;
            panel.add(board,gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            frame.add(panel, gbc);
            frame.add(backcolor, gbc);
            frame.repaint();
            frame.revalidate();
        }
    }
}
