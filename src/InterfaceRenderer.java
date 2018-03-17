import city.cs.engine.Body;
import com.sun.javaws.util.JfxHelper;

import javax.swing.*;
import java.awt.*;

public class InterfaceRenderer {

    private LevelManager levelManager;
    private JFrame frame;
    private MainView view;
    private JSplitPane splitPane;
    private GamePanel gamePanel;

    private int blobCount;
    private int boxCount;

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

    public void render(int blobs, int boxes, boolean paused){

        this.blobCount = this.blobCount + blobs;
        this.boxCount = this.boxCount + boxes;

        this.splitPane.setBottomComponent(new GamePanel(this.view, this, new GridBagLayout(), this.blobCount,this.boxCount, paused));

        this.frame.toFront();
        this.frame.requestFocus();

    }

    public void renderMenuPanel(){

        for (Body body: this.view.getWorld().getDynamicBodies()) {
            body.destroy();
        }

        this.getLevelManager().stopLevel(this.getLevelManager().getCurrentLevel());
        this.getLevelManager().startLevel(LevelContext.MENU);

        this.splitPane.setBottomComponent(new MenuPanel(new GridBagLayout()));

        this.frame.toFront();
        this.frame.requestFocus();

        this.view.getWorld().start();

    }

    public void addLevelManager(LevelManager levelManager){

        this.levelManager = levelManager;

    }

    public LevelManager getLevelManager() {

        return levelManager;

    }
}
