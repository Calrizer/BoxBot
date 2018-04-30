
import city.cs.engine.Body;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Callum Drain
 */

enum LevelContext {
    MENU, ONE, TWO, THREE, END
}

public class LevelManager implements KeyListener {

    private LevelContext currentLevel;
    private World world;
    private JFrame frame;
    private InterfaceRenderer interfaceRenderer;

    private Menu menu;
    private LevelOne levelOne;
    private LevelTwo levelTwo;
    private LevelThree levelThree;

    private boolean paused = false;

    /**
     * LevelManager is used to control the levels in the game by staring and stopping them. Each level will carry the same LevelManager instance so the integrity of the game is maintained.
     * @param world Add the current instance of yhe world that the game is being played on.
     * @param frame Add the current instance of the frame so a KeyListener can be implemented.
     * @param interfaceRenderer Add the current instance of the interfaceRenderer so the level manager knows when to skip levels.
     */

    public LevelManager(World world, JFrame frame, InterfaceRenderer interfaceRenderer){

        this.currentLevel = LevelContext.MENU;
        this.world = world;
        this.frame = frame;
        this.interfaceRenderer = interfaceRenderer;
        this.menu = new Menu(this, world, frame);

        frame.addKeyListener(this);

    }

    /**
     * startLevel is used to start each game level and the main menu.
     * @param level Add the desired level that you wish to start.
     */

    public void startLevel(LevelContext level){

        switch (level){

            case MENU:
                this.currentLevel = LevelContext.MENU;
                this.menu = new Menu(this, world, frame);
                this.interfaceRenderer.renderMenuPanel();
                this.interfaceRenderer.resetBoxCount();
                this.interfaceRenderer.resetBlobCount();
                break;
            case ONE:
                this.currentLevel = LevelContext.ONE;
                this.levelOne = new LevelOne(this, world, frame);
                renderInterface(0,0);
                break;
            case TWO:
                this.currentLevel = LevelContext.TWO;
                this.levelTwo = new LevelTwo(this, world, frame);
                renderInterface(0,0);
                break;
            case THREE:
                this.currentLevel = LevelContext.THREE;
                this.levelThree = new LevelThree(this, world, frame);
                renderInterface(0,0);
                break;
            case END:
                this.currentLevel = LevelContext.MENU;
                this.menu = new Menu(this, world, frame);
                this.interfaceRenderer.renderHighScorePanel();
                this.interfaceRenderer.resetBoxCount();
                this.interfaceRenderer.resetBlobCount();
                break;

        }

    }

    /**
     * stopLevel is used to stop each game level and the main menu.
     * @param level Add the desired level that you wish to stop.
     */

    public void stopLevel(LevelContext level){

        switch (level){

            case MENU:
                this.menu = null;
                clearBodies();
                startLevel(LevelContext.ONE);
                break;
            case ONE:
                clearBodies();
                this.levelOne.end();
                this.levelOne = null;
                break;
            case TWO:
                clearBodies();
                this.levelTwo.end();
                this.levelTwo = null;
                break;
            case THREE:
                clearBodies();
                this.levelThree.end();
                this.levelThree = null;
                break;

        }

    }

    /**
     * renderInterface is used to render the user interface below the game showing the game stats.
     * @param blobs Add the current number of enemies that have escaped.
     * @param boxes Add the current number of boxes used.
     */

    public void renderInterface(int blobs, int boxes){

        this.interfaceRenderer.render(blobs, boxes, false);

    }

    /**
     * getInterfaceRenderer returns the current interfaceRenderer instance.
     * @return
     */

    public InterfaceRenderer getInterfaceRenderer() {

        return interfaceRenderer;

    }

    /**
     * getCurrentLevel returns the level that is currently being played.
     * @return
     */

    public LevelContext getCurrentLevel() {

        return currentLevel;

    }

    /**
     * clearBodies destroys all DynamicBodies on the world. Used when switching levels to clear the world.
     */

    public void clearBodies(){

        for (Body body: this.world.getDynamicBodies()){
            body.destroy();
        }

    }

    /**
     * keyPressed in this context is used to pause the game when the ESC key is pressed. Currently WIP.
     * @param e An object containing the KeyPressed event information.
     */

    @Override
    public void keyPressed(KeyEvent e){

        if (e.getKeyCode() == 27){

            //world.stop();

        }

    }

    @Override
    public void keyReleased(KeyEvent e){

        //Just a stub.//

    }

    @Override
    public void keyTyped(KeyEvent e){

        //Just a stub.//

    }

}
