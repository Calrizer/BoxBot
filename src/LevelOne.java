import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LevelOne implements Level, StepListener{

    private LevelManager levelManager;
    private World world;
    private JFrame frame;

    private int time = 0;

    public LevelOne(LevelManager levelManager, World world, JFrame frame){

        this.levelManager = levelManager;
        this.world = world;
        this.frame = frame;

        Bot bot = new Bot(world, new Vec2(-16, -4));

        world.addStepListener(this);
        frame.addKeyListener(new KeyboardHandler(world, bot));

        start();

    }

    @Override
    public void start() {



    }

    @Override
    public void end() {



    }

    @Override
    public void preStep(StepEvent e){

        time++;

        if (time % 20 == 0){
            Blob b = new Blob(this.levelManager.getInterfaceRenderer(), world, new Vec2(24, -4));
            time = 0;
            //levelManager.renderInterface(2,5);
        }

    }

    @Override
    public void postStep(StepEvent e){



    }

}
