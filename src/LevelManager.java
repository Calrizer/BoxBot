
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

enum LevelContext {
    MENU, ONE, TWO, THREE
}

public class LevelManager implements KeyListener {

    private LevelContext currentLevel;
    private World world;
    private JFrame frame;
    private InterfaceRenderer interfaceRenderer;

    private Menu menu;
    private LevelOne levelOne;

    private boolean paused = false;

    public LevelManager(World world, JFrame frame, InterfaceRenderer interfaceRenderer){

        this.currentLevel = LevelContext.MENU;
        this.world = world;
        this.frame = frame;
        this.interfaceRenderer = interfaceRenderer;
        this.menu = new Menu(this, world, frame);

        frame.addKeyListener(this);

    }

    public void startLevel(LevelContext level){

        switch (level){

            case ONE:
                this.levelOne = new LevelOne(this, world, frame);
                break;

        }

    }

    public void stopLevel(LevelContext level){

        switch (level){

            case MENU:
                this.menu = null;
                startLevel(LevelContext.ONE);
                break;
            case ONE:
                this.levelOne = null;
                break;

        }

    }

    public void renderInterface(int blobs, int boxes){

        this.interfaceRenderer.render(blobs, boxes);

    }

    public InterfaceRenderer getInterfaceRenderer() {
        return interfaceRenderer;
    }

    @Override
    public void keyPressed(KeyEvent e){

        if (e.getKeyCode() == 27){

            world.stop();

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
