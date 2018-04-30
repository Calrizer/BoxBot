import city.cs.engine.Body;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.KeyListener;

public class LevelTwo implements Level, StepListener{

    private LevelManager levelManager;
    private World world;
    private JFrame frame;

    private int time = 0;
    private int blobs;
    private int skulls;

    public LevelTwo(LevelManager levelManager, World world, JFrame frame){

        this.levelManager = levelManager;
        this.world = world;
        this.frame = frame;

        Bot bot = new Bot(world, new Vec2(-16, -4));

        new Pickup(this.world, new Vec2(0,0));

        world.addStepListener(this);
        frame.addKeyListener(new KeyboardHandler(levelManager.getInterfaceRenderer(), world, bot));

        start();

    }

    @Override
    public void start() {

        this.levelManager.getInterfaceRenderer().changeBackground(2);

    }

    @Override
    public void end() {

        for (KeyListener listener : frame.getKeyListeners()) {
            frame.removeKeyListener(listener);
        }

        world.removeStepListener(this);
        System.out.println("finished");

    }

    @Override
    public void preStep(StepEvent e){

        if (skulls <= 40){

            time++;

            if (time % 20 == 0){

                new Skull(this.levelManager.getInterfaceRenderer(), world, new Vec2(25, -4));
                skulls++;

            }

        }else{

            boolean found = false;

            for (Body body: this.world.getDynamicBodies()) {

                if (body.getName() == "Skull"){
                    found = true;
                }

            }

            if (!found){

                levelManager.stopLevel(LevelContext.TWO);
                levelManager.startLevel(LevelContext.THREE);

            }

        }

    }

    @Override
    public void postStep(StepEvent e){

        //Just a stub.//

    }

}
