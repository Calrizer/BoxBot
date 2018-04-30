
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardHandler extends KeyAdapter implements StepListener {

    private InterfaceRenderer interfaceRenderer;
    private World world;
    private Bot bot;

    private boolean isHeadingUp;
    private boolean isHeadingLeft;
    private boolean isHeadingRight;

    public KeyboardHandler(InterfaceRenderer interfaceRenderer, World world, Bot bot) {

        this.interfaceRenderer = interfaceRenderer;
        this.world = world;
        this.bot = bot;

        this.world.addStepListener(this);

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        switch (key){

            case 87:
                isHeadingUp = true;
                break;
            case 65:
                isHeadingLeft = true;
                break;
            case 83:
                bot.removeAllImages();
                bot.addImage(new BodyImage("assets/bot/4.png", 2));
                break;
            case 68:
                isHeadingRight = true;
                break;
            case 32:
                interfaceRenderer.render(0, 1, false);
                Crate crate = new FlyingCrate(world, new Vec2(bot.getPosition().x + 2, bot.getPosition().y));
                crate.applyImpulse(new Vec2(30, 5));

                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e){

        int key = e.getKeyCode();

        switch (key){

            case 87:
                isHeadingUp = false;
                break;
            case 65:
                isHeadingLeft = false;
                break;
            case 83:

                break;
            case 68:
                isHeadingRight = false;
                break;
        }

    }

    @Override
    public void preStep(StepEvent e){

        //System.out.println("test");

        if (isHeadingUp){
            bot.applyImpulse(new Vec2(0,25));
            bot.updateTexture(BotTexture.UP);
        }else if (isHeadingUp == false){
            bot.updateTexture(BotTexture.DOWN);
        }

        if (isHeadingLeft){
            bot.applyImpulse(new Vec2(-15,0));
            bot.updateTexture(BotTexture.DOWN);
        }

        if (isHeadingRight) {
            bot.applyImpulse(new Vec2(15, 0));
            bot.updateTexture(BotTexture.DOWN);
        }

    }

    @Override
    public void postStep(StepEvent e){

        //Just a stub.//

    }

}
