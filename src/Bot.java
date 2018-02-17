import city.cs.engine.*;
import org.jbox2d.common.Vec2;

enum BotTexture {
    STATIC, UP, DOWN
}

public class Bot extends Walker implements CollisionListener{

    public Bot(World world, Vec2 position){

        super(world, new PolygonShape(1.5f,1.5f));
        this.addImage(new BodyImage("assets/bot/4.png", 2));
        this.setPosition(position);
        this.addCollisionListener(this);

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
    public void collide(CollisionEvent e){

        if (e.getOtherBody().getName() == "Crate"){

            System.out.println("Collided with crate.");

        }

    }

}
