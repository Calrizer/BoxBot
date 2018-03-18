import city.cs.engine.*;
import org.jbox2d.common.Vec2;

enum BotTexture {
    STATIC, UP, DOWN
}

public class Bot extends Walker implements CollisionListener, StepListener {

    private World world;
    private float crateRotation = 0;

    public Bot(World world, Vec2 position){

        super(world, new PolygonShape(1.5f,1.5f));
        this.addImage(new BodyImage("assets/bot/4.png", 2));
        this.setPosition(position);
        this.addCollisionListener(this);
        this.setName("Bot");
        this.world = world;
        world.addStepListener(this);

    }

    public void updateTexture(BotTexture texture){

        this.removeAllImages();

        switch (texture){

            case STATIC:
                this.addImage(new BodyImage("assets/bot/4.png", 2));
                break;
            case UP:
                this.addImage(new BodyImage("assets/bot/2.png", 2));
                break;
            case DOWN:
                this.addImage(new BodyImage("assets/bot/1.png", 2));

        }

    }

    @Override
    public void preStep(StepEvent e){

        for (Body body : this.world.getDynamicBodies()){

            if (body.getName() == "Crate"){
                body.destroy();
            }

        }

    }

    @Override
    public void postStep(StepEvent e){

        Crate crate = new Crate(world, new Vec2(this.getPosition().x + 2, this.getPosition().y));
        crate.rotateDegrees(crateRotation);
        crateRotation = crateRotation + 10;
        if (crateRotation == 360){
            crateRotation = 0;
        }

    }

    @Override
    public void collide(CollisionEvent e){

        if (e.getOtherBody().getName() == "Crate"){

            System.out.println("Collided with crate.");

        }

    }

}
