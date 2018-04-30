
import city.cs.engine.Body;
import javax.swing.*;
import java.awt.*;

/**
 * @author Callum Drain
 */

public class InterfaceRenderer {

    private LevelManager levelManager;
    private JFrame frame;
    private MainView view;
    private JSplitPane splitPane;
    private GamePanel gamePanel;

    private int blobCount;
    private int boxCount;

    private boolean isNew;
    private int [] score;

    /**
     * InterfaceRenderer is used to render the user interface onto the main JFrame.
     * @param frame The current JFrame instance of the game.
     * @param view The game view that will be rendered along with the user interface.
     */

    public InterfaceRenderer(JFrame frame, MainView view){

        this.frame = frame;
        this.view = view;
        this.splitPane = new JSplitPane();
        this.gamePanel = new GamePanel(view, this, new GridBagLayout(), 0, 0, false);

        this.splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        this.splitPane.setDividerLocation(500);
        this.splitPane.setTopComponent(view);
        this.splitPane.setBottomComponent(new MenuPanel(new GridBagLayout()));
        this.splitPane.setEnabled(false);

        this.frame.setPreferredSize(new Dimension(1000, 566));

        this.frame.add(splitPane);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationByPlatform(true);
        this.frame.setResizable(false);
        this.frame.pack();
        this.frame.setVisible(true);
        this.frame.setLayout(new BorderLayout());
        this.frame.toFront();
        this.frame.requestFocus();

    }

    /**
     * This is used to render the main game interface.
     * @param blobs The number of blobs (enemies) that have escaped to be rendered.
     * @param boxes The number of boxes that have been used to be rendered.
     * @param paused Whether the game has been paused or resumed to render the correct buttons.
     */

    public void render(int blobs, int boxes, boolean paused){

        this.blobCount = this.blobCount + blobs;
        this.boxCount = this.boxCount + boxes;

        this.splitPane.setBottomComponent(new GamePanel(this.view, this, new GridBagLayout(), this.blobCount,this.boxCount, paused));

        this.frame.toFront();
        this.frame.requestFocus();

    }

    /**
     * This is used to render the main menu panel.
     */

    public void renderMenuPanel(){

        for (Body body: this.view.getWorld().getDynamicBodies()) {
            body.destroy();
        }

        this.splitPane.setBottomComponent(new MenuPanel(new GridBagLayout()));

        this.frame.toFront();
        this.frame.requestFocus();

        this.view.getWorld().start();

    }

    /**
     * This is used to render the highscore panel.
     */

    public void renderHighScorePanel(){

        for (Body body: this.view.getWorld().getDynamicBodies()) {
            body.destroy();
        }

        this.splitPane.setBottomComponent(new HighScorePanel(new GridBagLayout(), this.score, this.isNew));

        this.frame.toFront();
        this.frame.requestFocus();

        this.view.getWorld().start();

    }

    /**
     * This is used to change the background image of the game view.
     * @param level The current level to have the background changed.
     */

    public void changeBackground(int level){

        this.splitPane.setTopComponent(new MainView(view.getWorld(), 1000, 500, level));

    }

    /**
     * This adds the current LevelManager instance to this class.
     * @param levelManager Current level manager instance.
     */

    public void addLevelManager(LevelManager levelManager){

        this.levelManager = levelManager;

    }

    /**
     * This returns the current LevelManager instance stored in this class.
     * @return The current LevelManager instance.
     */

    public LevelManager getLevelManager() {

        return levelManager;

    }

    /**
     * This returns the amount of blobs (enemies).
     * @return The amount of blobs (enemies).
     */

    public int getBlobCount() {

        return blobCount;

    }

    /**
     * This returns the amount of boxed used.
     * @return The amount of boxes used.
     */

    public int getBoxCount() {

        return boxCount;

    }

    /**
     * Sets the blob (enemy) count to 0.
     */

    public void resetBlobCount() {

        this.blobCount = 0;

    }

    /**
     * Sets the boxes used count to 0.
     */

    public void resetBoxCount() {

        this.boxCount = 0;

    }

    /**
     * Sets if the user has set a new highscore or not.
     * @param isNew Add whether the user has a new highscore or not.
     */

    public void setIsNew(boolean isNew) {

        this.isNew = isNew;

    }

    /**
     * Sets the user's score.
     * @param score Add the user's score. [BlobsEscaped,BoxesUsed].
     */

    public void setScore(int[] score) {

        this.score = score;

    }
}
