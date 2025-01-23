package controller;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.TransferHandler;
import java.awt.event.MouseEvent;
import model.*;
import view.*;

/**
 * The GameController class is responsible for controlling the game logic and user interactions in the game.
 * It manages the game model and the game window, and handles events triggered by user actions.
 */
public class GameController {

    private GameModel game;
    private GameWindow window;

    public GameController(GameModel game, GameWindow window) {
        this.game = game;
        this.window = window;
        
        addFirstListener();
    }

    /**
     * Starts a new game by initializing a new GameModel, setting the selected base,
     * and configuring the game window.
     */
    public void newGame() {
        this.game = new GameModel(0, window.getSelectedBase());
        game.setWin(false);
        window.setGame(game);
        window.clearButtons();
        window.modeSelection();
        addFirstListener();
    }

    /**
     * Adds action listeners to the buttons in the window.
     * When a button is clicked, it creates a new game model based on the selected mode and base,
     * sets the game in the window, performs color selection, plays a correct sound,
     * and adds color selection listeners.
     */
    public void addFirstListener() {
        window.getButton4v4().addActionListener(e -> {
            this.game = new GameModel(0, window.getSelectedBase());
            window.setGame(this.game);
            window.ColorSelection(4,0);
            SoundManager.playCorrectSound();
            addColorSelectionListener();
        });

        window.getButton1v1().addActionListener(e -> {
            this.game = new GameModel(1, window.getSelectedBase());
            window.setGame(this.game);
            window.ColorSelection(2,1);
            SoundManager.playCorrectSound();
            addColorSelectionListener();
        });

        window.getButton2v2().addActionListener(e -> {
            this.game = new GameModel(2, window.getSelectedBase());
            window.setGame(this.game);
            window.ColorSelection(2,2);
            SoundManager.playCorrectSound();
            addColorSelectionListener();
        });

        window.getShopButton().addActionListener(e -> {
            window.shopwindow();
            SoundManager.playCorrectSound();
            addShopListeners();
        });
    }

    /**
     * Adds listeners to the shop buttons and shop back button.
     * When a shop button is clicked, it sets the selected base type and switches to the mode selection window.
     * When the shop back button is clicked, it switches back to the mode selection window.
     */
    public void addShopListeners(){
        
        window.getShopBackButton().addActionListener(e -> {
            window.modeSelection();
            window.clearShopButtons();
            SoundManager.playCorrectSound();
            addFirstListener();
        });

        for(int i=0; i<5; i++){
            final int index = i;
            window.getShopButtons().get(i).addActionListener(e -> {
                window.setSelectedBase(window.getBaseTypes().get(index));
                window.modeSelection();
                window.clearShopButtons();
                SoundManager.playCorrectSound();
                addFirstListener();
            });
        }

    }

    /**
     * Adds a color selection listener to the window's play button and back button.
     * When the play button is clicked, it sets the name color based on the game mode,
     * checks if the selected colors are valid, shows the game window if the colors are valid,
     * plays a correct sound, and adds additional listeners.
     * When the back button is clicked, it goes back to the mode selection window,
     * plays a correct sound, and adds the first listener.
     */
    public void addColorSelectionListener() {
        window.getPlayButton().addActionListener(e -> {
            setNameColor(game.getMode());
            if(checkColors(game.getMode())){
                window.showGame(game);
                SoundManager.playCorrectSound();
                addListeners();
            }
            else{
                window.showColorError();
            }
        });

        window.getBackButton().addActionListener(e -> {
            window.modeSelection();
            addFirstListener();
            SoundManager.playCorrectSound();
        });
    }

    /**
     * Checks if the colors chosen by the players are valid based on the game mode.
     * 
     * @param mode the game mode (0, 1, or 2)
     * @return true if the colors are valid, false otherwise
     */
    public boolean checkColors(int mode) {

        switch (mode) {
            case 0:
                for (int i = 0; i < 4; i++) {
                    for (int j = i + 1; j < 4; j++) {
                        if (game.getUser(i).getColor().equals(game.getUser(j).getColor())) {
                            return false;
                        }
                    }
                }
                break;
                
            case 1:
                if (game.getUser(0).getColor().equals(game.getUser(1).getColor())) {
                    return false;
                }
                break;

            case 2:
                for (int i = 0; i < 2; i++) {
                    for (int j = i + 1; j < 2; j++) {
                        if (game.getUser(i).getColor().equals(game.getUser(j).getColor())) {
                            return false;
                        }
                    }
                }
                break;
            default:
                break;
        }

        return true;
    }

    /**
     * Sets the name and color of the players based on the given mode.
     *
     * @param mode the mode indicating how the names and colors should be set
     */
    public void setNameColor(int mode) {
        switch (mode) {
            case 0:
                for (int i = 0; i < 4; i++) {
                    game.getUser(i).setName(window.getNames().get(i).getText());
                    game.getUser(i).setColor(window.getColors().get(i));
                }
                break;
            case 1:
        
                game.getUser(0).setName(window.getNames().get(0).getText());
                game.getUser(0).setColor(window.getColors().get(0));

                game.getUser(1).setName(window.getNames().get(1).getText());
                game.getUser(1).setColor(window.getColors().get(1));

                game.getUser(2).setName(window.getNames().get(0).getText());
                game.getUser(2).setColor(window.getColors().get(0));

                game.getUser(3).setName(window.getNames().get(1).getText());
                game.getUser(3).setColor(window.getColors().get(1));
                break;

            case 2:

                game.getUser(0).setName(window.getNames().get(0).getText());
                game.getUser(0).setColor(window.getColors().get(0));

                game.getUser(1).setName(window.getNames().get(1).getText());
                game.getUser(1).setColor(window.getColors().get(1));

                game.getUser(2).setName(window.getNames().get(0).getText());
                game.getUser(2).setColor(window.getColors().get(0).darker());

                game.getUser(3).setName(window.getNames().get(1).getText());
                game.getUser(3).setColor(window.getColors().get(1).darker());
                break;
        
            default:
                break;
        }

        }

    /**
     * Adds listeners to the buttons and pieces in the game window.
     * The listeners handle user interactions such as button clicks and piece dragging.
     * When a button is clicked, the corresponding action is performed in the game and the window is updated.
     * When a piece is dragged and dropped onto a button, the piece is played in the game and the window is updated.
     * If an invalid move is made, an error message is printed.
     */
    public void addListeners() {

        window.getPassButton().addActionListener(e -> {
            game.pass();
            window.update(game);
            checkWin();
        });

        for (int i = 0; i < 9; i++) {
            final int index = i;
            window.getButtons().get(i).setDropTarget(new DropTarget() {
                public synchronized void drop(DropTargetDropEvent evt) {
                    try {
                        evt.acceptDrop(DnDConstants.ACTION_COPY);
                        int pieceIndex = Integer.parseInt(evt.getTransferable().getTransferData(DataFlavor.stringFlavor).toString());
                        game.playPiece(game.getUser(game.getTurn().getActualPlayer()).playPiece(index / 3, index % 3, pieceIndex));
                        window.update(game);
                        checkWin();
                    } catch (InvalidMoveException ex) {
                        //InvalidMove
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }

        for (int i = 0; i < window.getPieces().size(); i++) {

            window.getPieces().get(i).addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent l) {
                    JRadioButton source = (JRadioButton) l.getSource();
                    if (source.isEnabled()) {
                        TransferHandler handler = source.getTransferHandler();
                        handler.exportAsDrag(source, l, TransferHandler.COPY);
                    }
                    
                }
            });
            window.getPieces().get(i).setTransferHandler(new TransferHandler("text"){
                @Override
                public boolean canImport(TransferSupport support) {
                    return !(support.getComponent() instanceof JRadioButton);
                }

                @Override
                protected Transferable createTransferable(JComponent c) {
                return new StringSelection(Integer.toString(window.getPieces().indexOf(c)));
                }

                @Override
                public int getSourceActions(JComponent c) {
                    return COPY;
                }
            });
        }
    }

    /**
     * Checks if the game has been won or ended in a draw.
     * If the game has been won or ended in a draw, it adds an ActionListener to the "Again" button
     * that plays a correct sound and starts a new game when clicked.
     */
    public void checkWin() {
        window.checkWin();
        if (game.getWin()||game.getDraw()) {
            window.getAgainButton().addActionListener(e -> {
                SoundManager.playCorrectSound();
                newGame();
            });
        }
    }

    /**
     * The main entry point for the game.
     *
     * @param args the command line arguments
     */
    static public void main(String[] args) {
        GameModel game = new GameModel(0, "circle");
        GameWindow window = new GameWindow(game);
        new GameController(game, window);
    }
}
