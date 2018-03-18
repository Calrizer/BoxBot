
import city.cs.engine.Body;
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
    private LevelTwo levelTwo;
    private LevelThree levelThree;

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

            case MENU:
                this.currentLevel = LevelContext.MENU;
                this.menu = new Menu(this, world, frame);
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

        }

    }

    public void stopLevel(LevelContext level){

        switch (level){

            case MENU:
                this.menu = null;
                clearBodies();
                startLevel(LevelContext.THREE);
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

    public void renderInterface(int blobs, int boxes){

        this.interfaceRenderer.render(blobs, boxes, false);

    }

    public InterfaceRenderer getInterfaceRenderer() {

        return interfaceRenderer;

    }

    public LevelContext getCurrentLevel() {

        return currentLevel;

    }

    public void clearBodies(){

        for (Body body: this.world.getDynamicBodies()){
            body.destroy();
        }

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
