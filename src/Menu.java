
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Callum Drain
 */

public class Menu implements Level, StepListener, KeyListener{

    private LevelManager levelManager;
    private World world;
    private JFrame frame;
    private StaticBody boxBotText;
    private boolean movement = true;
    private boolean ending = false;
    private boolean ended = false;

    /**
     * Menu is used to render control all of the components that make up the main menu of the game.
     * @param levelManager Add the current levelManager instance so the menu can be invoked and dismissed.
     * @param world Add the current world instance so a StepListener can be implemented.
     * @param frame Add the current frame instance so a KeyListener can be implemented.
     */

    public Menu(LevelManager levelManager, World world, JFrame frame) {

        this.levelManager = levelManager;
        this.world = world;
        this.frame = frame;

        this.world.addStepListener(this);
        this.frame.addKeyListener(this);

        start();

    }

    /**
     * start is used to start the main menu.
     */

    @Override
    public void start() {

        boxBotText = new StaticBody(world, new BoxShape(5, 2));
        boxBotText.setPosition(new Vec2(0, 1));
        boxBotText.addImage(new BodyImage("assets/text/BoxBot.png", 7));

    }

    /**
     * end is used to stop the main menu. Callback made to levelManager to start the next level.
     */

    @Override
    public void end() {

        boxBotText.destroy();
        levelManager.stopLevel(LevelContext.MENU);
        world.removeStepListener(this);

    }

    /**
     * In this context keyPressed is used to close the main menu when the user presses any key to start.
     * @param e An object containing the KeyPressed event information.
     */

    @Override
    public void keyPressed(KeyEvent e) {

        ending = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {

        //Just a stub.//

    }

    @Override
    public void keyTyped(KeyEvent e) {

        //Just a stub.//

    }

    /**
     * preStep in this context is used to animate the game logo.
     * @param e An object containing the StepEvent information.
     */

    @Override
    public void preStep(StepEvent e){

        if (!ended) {

            if (!ending) {

                if (movement) {

                    boxBotText.setPosition(new Vec2(0, boxBotText.getPosition().y - 0.05f));
                    if (boxBotText.getPosition().y <= 0) {
                        movement = false;
                    }

                } else if (!movement) {

                    boxBotText.setPosition(new Vec2(0, boxBotText.getPosition().y + 0.05f));
                    if (boxBotText.getPosition().y >= 1) {
                        movement = true;
                    }

                }

            } else if (ending) {

                if (boxBotText.getPosition().y > 20) {
                    ended = true;
                    end();
                } else {
                    boxBotText.setPosition(new Vec2(0, boxBotText.getPosition().y + 0.75f));
                }

            }

        }

    }

    @Override
    public void postStep(StepEvent e){

        //Just a stub.//

    }

}
